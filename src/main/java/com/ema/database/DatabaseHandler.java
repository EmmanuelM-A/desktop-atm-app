package com.ema.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.*;

/**
 * This class handles connections to the database.
 */
public class DatabaseHandler {
    /**
     * Creates a connection to the database.
     * @return The connection establsihed.
     */
    public static Connection getConnection() {
        // Load the enivronment variables from the .env file
        Dotenv dotenv = Dotenv.configure().load();

        // Get databse credentials
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        // Create a connection
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
