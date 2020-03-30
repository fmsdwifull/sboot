package com.liu.sboot.controller;

import com.liu.sboot.mapper.RoleMapper;
import com.liu.sboot.mapper.UserMapper;
import com.liu.sboot.model.Role;
import com.liu.sboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
public class UserController {
    //为什么bean不需要注入，mappler service 需要注入？
    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id")  Integer id)
    {
        User user = userMapper.getUserById(id);
        return user;
    }

    //@GetMapping("/user")
    @PostMapping("/user")
    //这里为什么不用pathvariable呢？
    public User addUser(User user)
    {
        System.out.print("=======================>"+user);
        //userMapper.insert(user);
        return user;
    }

    @GetMapping("/role/{id}")
    public List<Role> getRoleById(@PathVariable("id") Integer id)
    {
        return roleMapper.getRoleById(id);
    }


    @GetMapping("/fm")
    public  String myTest()
    {
        return "form";
    }

    @PostMapping("/fm")
    @ResponseBody
    public User doUser(User user)
    {
        System.out.print("------------controler------------------"+user);
        return user;
    }
}
