package com.liu.sboot;

import com.liu.sboot.mapper.UserMapper;
import com.liu.sboot.service.CustomUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SbootApplicationTests {
    @Autowired
    DataSource dataSource;
    //DataSource dataSource;
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() throws SQLException {

 /*
        Connection connection = dataSource.getConnection();
        System.out.print(connection);
        connection.close();*/
        System.out.print("--iiiiiiiiiiiiiiiiiiiiiiiiiiiiii-->"+userMapper.getUserByName("admin"));
    }

}
