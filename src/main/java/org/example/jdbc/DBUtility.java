package org.example.jdbc; // Declares the package name for the class.

import java.sql.Connection; // Imports the Connection class from JDBC.
import java.sql.DriverManager; // Imports the DriverManager class from JDBC.
import java.sql.SQLException; // Imports the SQLException class from JDBC.

public class DBUtility { // Declares the public class DBUtility.
    private static final String URL = "jdbc:mysql://localhost:3306/ethnic_groups_nepal"; // Declares a constant for the database URL.
    private static final String USER = "root"; // Declares a constant for the database user.
    private static final String PASSWORD = "1234"; // Declares a constant for the database password.

    public static Connection getConnection() throws SQLException { // Defines a public static method to get a database connection.
        return DriverManager.getConnection(URL, USER, PASSWORD); // Uses DriverManager to establish and return a connection to the database.
    }
}
