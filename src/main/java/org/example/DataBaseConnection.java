package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String url = "jdbc:postgresql://localhost:4747/mydatabase";
    private static final String user = "postgres";
    private static final String password = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
