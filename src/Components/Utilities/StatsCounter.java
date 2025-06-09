package Components.Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatsCounter {
    
    public static int[] getSuggestedItemStats() {
        int[] stats = new int[3]; // [pending, accepted, rejected]
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(
                "SELECT status, COUNT(*) as count FROM suggested_item GROUP BY status"
            );
            
            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt("count");
                
                switch (status.toLowerCase()) {
                    case "pending":
                        stats[0] = count;
                        break;
                    case "accepted":
                        stats[1] = count;
                        break;
                    case "rejected":
                        stats[2] = count;
                        break;
                }
            }
            
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error getting suggested item stats: " + e.getMessage());
            e.printStackTrace();
        }
        return stats;
    }
    
    public static int[] getInventoryStats() {
        int[] stats = new int[2]; // [total, exhibited]
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(
                "SELECT COUNT(*) as total, SUM(is_exhibited) as displayed FROM item"
            );
            
            if (rs.next()) {
                stats[0] = rs.getInt("total");
                stats[1] = rs.getInt("displayed");
            }
            
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error getting inventory stats: " + e.getMessage());
            e.printStackTrace();
        }
        return stats;
    }
} 