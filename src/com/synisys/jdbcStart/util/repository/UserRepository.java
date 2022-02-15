package com.synisys.jdbcStart.util.repository;

import com.synisys.jdbcStart.util.model.User;
import com.synisys.jdbcStart.util.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (0, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getCode());
        preparedStatement.setInt(6, user.getStatus());

        int result = preparedStatement.executeUpdate();
        System.out.println(result);
    }

    public User getById(int id) throws SQLException {
        User user = null;
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            user = fromResultSet(resultSet);
        }
        resultSet.close();
        preparedStatement.close();
        return user;
    }

    public void updateUser(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET name = ?, surname = ? WHERE id = ?");
        preparedStatement.setString(1, "Updated Name");
        preparedStatement.setString(2, "Updated Surname");
        preparedStatement.setInt(3, 5);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteUser(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
        preparedStatement.setInt(1, user.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private User fromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setCode(resultSet.getString("code"));
        user.setStatus(resultSet.getInt("status"));

        return user;
    }
}
