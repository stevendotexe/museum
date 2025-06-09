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
    
    public static ResultSet searchItems(Connection conn, String searchText, String category, int page, int itemsPerPage) throws SQLException {
        int offset = (page - 1) * itemsPerPage;
        String searchQuery = "SELECT item_id, item_name, image_url, item_description, location_found, " +
                           "category, date_discovered, created_at, modified_at, is_exhibited " +
                           "FROM item WHERE (item_name LIKE ? " +
                           "OR item_description LIKE ? " +
                           "OR location_found LIKE ? " +
                           "OR category LIKE ?) ";
        
        if (!category.equals("All")) {
            searchQuery += "AND category = ? ";
        }
        
        searchQuery += "ORDER BY created_at DESC LIMIT ? OFFSET ?";
        
        System.out.println("Search Query: " + searchQuery); // Debug print
        
        java.sql.PreparedStatement pstmt = conn.prepareStatement(searchQuery);
        String searchPattern = "%" + searchText + "%";
        pstmt.setString(1, searchPattern);
        pstmt.setString(2, searchPattern);
        pstmt.setString(3, searchPattern);
        pstmt.setString(4, searchPattern);
        
        int paramIndex = 5;
        if (!category.equals("All")) {
            pstmt.setString(paramIndex++, category);
        }
        
        pstmt.setInt(paramIndex++, itemsPerPage);
        pstmt.setInt(paramIndex, offset);
        
        return pstmt.executeQuery();
    }

    public static int getTotalSearchResults(Connection conn, String searchText, String category) throws SQLException {
        String countQuery = "SELECT COUNT(*) as total FROM item WHERE (item_name LIKE ? " +
                          "OR item_description LIKE ? " +
                          "OR location_found LIKE ? " +
                          "OR category LIKE ?) ";
        
        if (!category.equals("All")) {
            countQuery += "AND category = ?";
        }
        
        System.out.println("Count Query: " + countQuery); // Debug print
        
        java.sql.PreparedStatement pstmt = conn.prepareStatement(countQuery);
        String searchPattern = "%" + searchText + "%";
        pstmt.setString(1, searchPattern);
        pstmt.setString(2, searchPattern);
        pstmt.setString(3, searchPattern);
        pstmt.setString(4, searchPattern);
        
        if (!category.equals("All")) {
            pstmt.setString(5, category);
        }
        
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }
} 