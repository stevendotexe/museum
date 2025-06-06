package Components.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            Config.getJDBCUrl(),
            Config.getDBUser(),
            Config.getDBPassword()
        );
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed successfully.");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void testConnection() {
        Connection connection = null;

        try {
            // Get database connection
            System.out.println("Attempting to connect to database...");
            connection = getConnection();
            System.out.println("Successfully connected to the database!");

            // Your database operations go here

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure mysql-connector-j-9.2.0.jar is in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the connection
            closeConnection(connection);
        }
    }
}
