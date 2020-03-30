package com.liu.sboot.mapper;

import com.liu.sboot.model.User;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest{
    @Autowired
    UserMapper userMapper;
    //private UserMapper userMapper = new UserMapper();
    @org.junit.Test
    public void test()
    {
        System.out.print(userMapper+"33333333333333333");
        //User   user = userMapper.getUserByName("admin");
        User user = userMapper.getUserByPermiss("zhangsan","1234");
        System.out.print(user);
    }
}
