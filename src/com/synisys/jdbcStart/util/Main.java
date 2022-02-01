package com.synisys.jdbcStart.util;

import com.synisys.jdbcStart.util.util.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DataSource.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery("SELECT * from student");

        while (resultset.next()) {
            int id = resultset.getInt("id");
            String name = resultset.getString("name");
            String surname = resultset.getString("surname");

            System.out.println(id + " " + name + " " + surname);
        }
        resultset.close();
        statement.close();
    }
}
