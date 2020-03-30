package com.liu.sboot.mapper;

import com.liu.sboot.model.Role;
import com.liu.sboot.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest{
    @Autowired
    RoleMapper roleMapper;
    //private UserMapper userMapper = new UserMapper();
    @Test
    public void test()
    {
        //List<Role> role = roleMapper.getAllRole();
        List<Role> role = roleMapper.getRoleById(2);
        System.out.print(role);
    }
}
