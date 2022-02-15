package com.synisys.jdbcStart.util.repository;

import com.synisys.jdbcStart.util.model.Student;
import com.synisys.jdbcStart.util.util.DataSource;
import com.synisys.jdbcStart.util.util.DataSourceUniversity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private DataSource dataSource;

    public StudentRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Student getById(int id) throws SQLException {
        Student student = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM student WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            student = fromResultSet(resultSet);
        }
        resultSet.close();
        preparedStatement.close();

        return student;
    }

    public List<Student> findByNameAndSurname(String name, String surname) throws SQLException {
        List<Student> list = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE name = ? AND surname = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Student student = fromResultSet(resultSet);
            list.add(student);
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }

    public List<Student> getAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Student student = fromResultSet(resultSet);
            studentList.add(student);
        }

        resultSet.close();
        preparedStatement.close();

        return studentList;
    }

    public void add(Student student) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student VALUES (0, ?, ?, ?)");
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getSurname());
        preparedStatement.setInt(3, 1);

        int result = preparedStatement.executeUpdate();
        // boolean result = preparedStatement.execute();
        System.out.println(result);
    }

    public void update(Student student) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET name = ? WHERE ID = ?");
        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getId());

        int result = preparedStatement.executeUpdate();
        // boolean result = preparedStatement.execute();
        System.out.println(result);
    }

    public void printStudents() throws SQLException {

        Connection connection = dataSource.getConnection();

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

    public void delete(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id = ?");
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private Student fromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setSurname(resultSet.getString("surname"));

        return student;
    }
}
