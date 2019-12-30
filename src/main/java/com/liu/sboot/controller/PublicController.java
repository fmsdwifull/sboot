package com.liu.sboot.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.liu.sboot.model.User;
import com.liu.sboot.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/public")
public class PublicController {
    @Autowired
    PublicService publicService;

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @ResponseBody
    @PostMapping("doLogin")
    public Map doLogin(User user)
    {
        Map<String,Object> result = new HashMap<>();
        if(publicService.login(user))
        {
            result.put("msg","登陆成功");
            result.put("resultCode","100");
            result.put("data",user);
            return result;
        }else{
            result.put("msg","用户名或密码错误");
            result.put("resultCode","200");
            result.put("data",user);
            return result;
        }
    }

    @GetMapping("/home")
    public String home()
    {
        return "home/list";
    }

    @GetMapping("/admin/{path}")
    public String admin(@PathVariable("path") String path)
    {
        return "admin/"+path;
    }

    @GetMapping("/user/{path}")
    public String user(@PathVariable("path") String path)
    {
        return "user/"+path;
    }
}
