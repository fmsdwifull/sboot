package com.liu.sboot;

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

    @Test
    void contextLoads() throws SQLException {
        System.out.print("---->"+dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.print(connection);
        connection.close();
    }

}
