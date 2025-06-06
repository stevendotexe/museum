package Components.Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemQueries {
    
    public static ResultSet getRecentlyAddedItems(Connection conn) throws SQLException {
        String query = "SELECT item_id, item_name, image_url, item_description, location_found, " +
                      "category, date_discovered, created_at, modified_at, is_exhibited " +
                      "FROM item ORDER BY created_at DESC LIMIT 8";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
    
    public static ResultSet searchItems(Connection conn, String searchText) throws SQLException {
        String searchQuery = "SELECT item_id, item_name, image_url, item_description, location_found, " +
                           "category, date_discovered, created_at, modified_at, is_exhibited " +
                           "FROM item WHERE item_name LIKE '%" + searchText + "%' " +
                           "OR item_description LIKE '%" + searchText + "%' " +
                           "OR location_found LIKE '%" + searchText + "%' " +
                           "OR category LIKE '%" + searchText + "%' " +
                           "ORDER BY created_at DESC LIMIT 8";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(searchQuery);
    }
} 