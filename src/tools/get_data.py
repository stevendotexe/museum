import requests
import json
from datetime import datetime
import random
import time # For rate limiting
import re # For regex to parse dates

# --- Configuration ---
# Name of the output SQL file
OUTPUT_SQL_FILE = 'museum_insert_queries.sql'
# Base URL for The Met Museum API
MET_API_BASE_URL = "https://collectionapi.metmuseum.org/public/collection/v1" 
# Number of items to fetch and generate queries for
NUM_ITEMS_TO_GENERATE = 1000

# --- Helper function to escape single quotes for SQL strings ---
def escape_sql_string(s):
    if s is None:
        return 'NULL'
    # Escape single quotes by doubling them
    return "'" + str(s).replace("'", "''") + "'"

# --- Helper function to format date_discovered for TIMESTAMP ---
def format_date_discovered(date_str):
    if not date_str:
        # Default to 1000-01-01 00:00:00 if string is empty
        return escape_sql_string(f"1000-01-01 00:00:00")
    
    # Try to parse a year (e.g., "1900", "ca. 1850-1900", "18th century", "500 BCE", "30 BCE")
    # This regex attempts to find specific year patterns or century references.
    # It prioritizes specific years, then centuries.
    
    # Handle BCE/BC dates first (set to default as SQL TIMESTAMP typically doesn't handle negative years)
    if re.search(r'\bBCE\b|\bBC\b', date_str, re.IGNORECASE):
        print(f"  Warning: BCE/BC date '{date_str}' found. Setting date_discovered to default: 1000-01-01 00:00:00.")
        return escape_sql_string("1000-01-01 00:00:00")

    # Try to find a direct four-digit year
    year_match = re.search(r'\b(\d{4})\b', date_str)
    if year_match:
        year = year_match.group(1)
        return escape_sql_string(f"{year}-01-01 00:00:00")
    
    # Try to find a century (e.g., "18th century", "14th-15th century")
    century_match = re.search(r'(\d{1,2})(?:st|nd|rd|th)?(?:-|\s*to\s*)?(\d{1,2})?\s*century', date_str, re.IGNORECASE)
    if century_match:
        start_century = int(century_match.group(1))
        # Calculate the starting year of the century
        year = (start_century - 1) * 100
        return escape_sql_string(f"{year}-01-01 00:00:00")

    # If no year or recognizable century pattern is found, set to the default date
    print(f"  Warning: Could not reliably parse '{date_str}' into a specific year. Setting date_discovered to default: 1000-01-01 00:00:00.")
    return escape_sql_string("1000-01-01 00:00:00")

# --- Main function to fetch data and generate SQL queries ---
def generate_museum_sql_inserts():
    print(f"Fetching up to {NUM_ITEMS_TO_GENERATE} items from The Met API...")
    object_ids = []
    
    try:
        # Step 1: Get a list of all available object IDs from the Met API
        response = requests.get(f"{MET_API_BASE_URL}/objects")
        response.raise_for_status() # Raise an HTTPError for bad responses (4xx or 5xx)
        all_object_ids = response.json().get('objectIDs', [])
        print(f"Found {len(all_object_ids)} total object IDs. Selecting a random subset...")

        # Step 2: Select a random subset of IDs to work with
        if len(all_object_ids) > NUM_ITEMS_TO_GENERATE:
            object_ids = random.sample(all_object_ids, NUM_ITEMS_TO_GENERATE)
        else:
            object_ids = all_object_ids

    except requests.exceptions.RequestException as e:
        print(f"Error fetching object IDs from Met API: {e}")
        return
    except json.JSONDecodeError as e:
        print(f"Error decoding JSON response for object IDs: {e}")
        return

    generated_count = 0
    sql_queries = []

    # Step 3: Iterate through selected object IDs and fetch detailed data
    # Added 'enumerate' to get the current loop index
    for i, obj_id in enumerate(object_ids): 
        try:
            detail_url = f"{MET_API_BASE_URL}/objects/{obj_id}"
            response = requests.get(detail_url)
            response.raise_for_status()
            item_data = response.json()

            # --- Map Met API data to your 'items' table schema ---
            # item_name: Use 'title' or 'objectName'
            item_name = item_data.get('title') or item_data.get('objectName', 'Untitled Item')
            
            # location_found: Infer from 'culture' and 'geography', or use a placeholder
            location_found_parts = []
            if item_data.get('culture'): 
                location_found_parts.append(item_data['culture'])
            if item_data.get('geography'): 
                location_found_parts.append(item_data['geography'])
            location_found = ", ".join(location_found_parts) if location_found_parts else "Unknown Origin"

            # category: Use 'objectName' or 'classification'
            category = item_data.get('objectName') or item_data.get('classification') or 'General Art'
            
            # item_description: Combine relevant fields for a comprehensive description
            description_parts = []
            if item_data.get('objectWikidataDescription'): 
                description_parts.append(item_data['objectWikidataDescription'])
            elif item_data.get('repository'): 
                description_parts.append(f"From the repository: {item_data['repository']}.")
            else: 
                description_parts.append("A unique piece from the collection.")
            
            if item_data.get('dimensions'): 
                description_parts.append(f"Dimensions: {item_data['dimensions']}.")
            if item_data.get('medium'): 
                description_parts.append(f"Medium: {item_data['medium']}.")
            
            item_description = " ".join(description_parts).strip()
            if not item_description: 
                item_description = "No specific description available."

            # date_discovered: Formatted for TIMESTAMP or default to 1000-01-01 00:00:00
            date_discovered_formatted = format_date_discovered(item_data.get('objectDate'))

            # created_at & modified_at: Formatted for TIMESTAMP
            current_timestamp = datetime.now().strftime('%Y-%m-%d %H:%M:%S.%f')[:-3] # YYYY-MM-DD HH:MM:SS.ms
            
            # is_exhibited: 1 for True, 0 for False (compatible with BOOL/TINYINT(1))
            is_exhibited = 1 if random.choice([True, False]) else 0

            # image_url: Ensure length constraint and handle NULL if missing
            image_url = item_data.get('primaryImage') or item_data.get('webImage')
            if image_url:
                image_url = image_url[:255] # Truncate if longer than 255 chars
            else:
                image_url = f"https://placehold.co/400x300/CCCCCC/000000?text=No+Image" # Placeholder if no image
            
            # --- Construct the SQL INSERT statement ---
            sql = f"""INSERT INTO items (
    item_name, location_found, category, item_description,
    date_discovered, created_at, modified_at, is_exhibited, image_url
) VALUES (
    {escape_sql_string(item_name)}, {escape_sql_string(location_found)}, {escape_sql_string(category)}, {escape_sql_string(item_description)},
    {date_discovered_formatted}, {escape_sql_string(current_timestamp)}, {escape_sql_string(current_timestamp)}, {is_exhibited}, {escape_sql_string(image_url)}
);"""
            sql_queries.append(sql)
            generated_count += 1
            # Print the current collection number
            print(f"({i + 1}/{len(object_ids)}) Generated query for: {item_name} (Met ID: {obj_id})")

            # Be polite to the API - add a small delay
            time.sleep(0.01) # 100ms delay between requests

        except requests.exceptions.RequestException as e:
            print(f"Error fetching details for object ID {obj_id}: {e}")
        except json.JSONDecodeError as e:
            print(f"Error decoding JSON for object ID {obj_id}: {e}")
        
        # Stop if we've generated the requested number of items
        if generated_count >= NUM_ITEMS_TO_GENERATE:
            break 

    # Step 4: Write all generated SQL queries to the output file
    try:
        with open(OUTPUT_SQL_FILE, 'w', encoding='utf-8') as f:
            for query in sql_queries:
                f.write(query + '\n\n') # Add newline for readability
        print(f"\nSuccessfully generated {generated_count} SQL INSERT queries in '{OUTPUT_SQL_FILE}'")
        print(f"Please review '{OUTPUT_SQL_FILE}' before executing in your database.")
    except IOError as e:
        print(f"Error writing to file '{OUTPUT_SQL_FILE}': {e}")

# --- Run the script ---
if __name__ == "__main__":
    generate_museum_sql_inserts()
