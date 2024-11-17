package com.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class to manage database connections.
 */
public class DBConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/IndyWinners";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Chiefrocka27$";

    private static Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {}

    /**
     * Provides a singleton database connection.
     *
     * @return The database connection.
     * @throws SQLException If a connection cannot be established.
     */
    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Explicitly load the JDBC driver
                connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC Driver not found.", e);
            }
        }
        return connection;
    }
}

