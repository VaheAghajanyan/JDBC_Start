package com.synisys.jdbcStart.util.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceJDBC implements DataSource{
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/jdbc";
    private static final String username = "root";
    private static final String password = "";

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}