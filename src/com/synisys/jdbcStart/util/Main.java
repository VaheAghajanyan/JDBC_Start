package com.synisys.jdbcStart.util;

import com.synisys.jdbcStart.util.util.DataSource;

import javax.xml.crypto.Data;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

    /*prepareStatementGetStudentById();
    insertStudent();*/

        updateStudent();
    }

    public static void prepareStatementGetStudentById() throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM student WHERE id = ?");
        preparedStatement.setInt(1, 3);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");

            System.out.println(id + " " + name + " " + surname);
        }
        resultSet.close();
        preparedStatement.close();
    }

    public static void insertStudent() throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student VALUES (0, ?, ?, ?)");
        preparedStatement.setString(1, "New Name");
        preparedStatement.setString(2, "New Surname");
        preparedStatement.setInt(3, 1);

        int result = preparedStatement.executeUpdate();
        // boolean result = preparedStatement.execute();
        System.out.println(result);
    }

    public static void updateStudent() throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET name = ? WHERE ID = ?");
        preparedStatement.setString(1, "Updated Name");
        preparedStatement.setInt(2, 5);

        int result = preparedStatement.executeUpdate();
        // boolean result = preparedStatement.execute();
        System.out.println(result);
    }

    public static void printStudents() throws SQLException {

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
