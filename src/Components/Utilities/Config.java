package Components.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    private static boolean isLoaded = false;

    static {
        try {
            // Try to load from .env file
            try (FileInputStream fis = new FileInputStream(".env")) {
                properties.load(fis);
                isLoaded = true;
            } catch (IOException e) {
                System.out.println("No .env file found, using default configuration");
            }
        } catch (Exception e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    public static String get(String key, String defaultValue) {
        if (isLoaded) {
            return properties.getProperty(key, defaultValue);
        }
        return defaultValue;
    }

    public static String getDBHost() {
        return get("DB_HOST", null);
    }

    public static String getDBPort() {
        return get("DB_PORT", null);
    }

    public static String getDBName() {
        return get("DB_NAME", null);
    }

    public static String getDBUser() {
        return get("DB_USER", null);
    }

    public static String getDBPassword() {
        return get("DB_PASSWORD", null);
    }

    public static String getJDBCUrl() {
        return "jdbc:mysql://" + getDBHost() + ":" + getDBPort() + "/" + getDBName() + "?sslmode=require";
    }
} 