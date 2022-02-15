package com.synisys.jdbcStart.util;

import com.synisys.jdbcStart.util.model.User;
import com.synisys.jdbcStart.util.repository.UserRepository;
import com.synisys.jdbcStart.util.util.DataSource;
import com.synisys.jdbcStart.util.util.DataSourceJDBC;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*DataSource dataSource = new DataSource();
        StudentRepository studentRepository = new StudentRepository(dataSource);

        List<Student> list = studentRepository.getAll();
        System.out.println(list);*/

        DataSource dataSource = new DataSourceJDBC();
        UserRepository userRepository = new UserRepository(dataSource);

       // userRepository.add(new User("Vahe", "Aghajanyan", "vah.aghajanyan@gmail.com", "password", "code", 1));
        User user = userRepository.getById(1);
        System.out.println(user);
    }
}
