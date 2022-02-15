package com.synisys.jdbcStart.util.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUniversity implements DataSource{
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/university";
    private static final String username = "root";
    private static final String password = "";

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class clazz = Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
