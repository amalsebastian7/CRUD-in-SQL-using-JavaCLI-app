package crudapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    // Create a Properties object to hold our secure data
    private static final Properties properties = new Properties();
 
    static {
        try (FileInputStream fileInput = new FileInputStream("database.properties")) {
            // Load the passwords from the text file into Java's memory
            properties.load(fileInput);
        } catch (IOException e) {
            System.out.println("CRITICAL ERROR: Could not find or read the database.properties file.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            // Grab the credentials we loaded from the file
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password"); 

            // Open the tunnel using the secure credentials
            return DriverManager.getConnection(url, user, password);
            
        } catch (SQLException e) {
            System.out.println("CRITICAL ERROR: Could not connect to the database.");
            e.printStackTrace();
            return null;
        }
    }
}