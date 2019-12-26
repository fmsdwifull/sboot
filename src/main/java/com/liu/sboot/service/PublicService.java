package com.liu.sboot.service;

import com.liu.sboot.mapper.UserMapper;
import com.liu.sboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicService {
    @Autowired
    UserMapper userMapper;

    public boolean login(User user) {
        //List<User> ret = userMapper.getUserAll();
        List<User> ret = userMapper.getUserByPwd(user);
        if(ret.size()>0)
        {
            return true;
        }else{
            return  false;
        }
    }
}
