package com.liu.sboot.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.liu.sboot.model.User;
import com.liu.sboot.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String admin(@PathVariable("path") String path, HttpServletRequest request)
    //public String admin(@PathVariable("path") String path, Model model)
    {
//        HttpSession session=request.getSession();//获取session并将userName存入session对象
//        session.setAttribute("name", session.getAttribute("username"));
//        System.out.print("----1-->"+session.getAttribute("username"));
//        System.out.print("---2--->"+ session.getAttributeNames());
       // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

         //model.addAttribute("name",principal.toString());
        //SecurityContextImpl
//1.从HttpServletRequest中获取SecurityContextImpl对象
      //  SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//2.从SecurityContextImpl中获取Authentication对象
       // Authentication authentication = securityContextImpl.getAuthentication();
      //  authentication.getCredentials();
//3.初始化UsernamePasswordAuthenticationToken实例 ，这里的参数user就是我们要更新的用户信息
       // UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials());
       // auth.setDetails(authentication.getDetails());
//4.重新设置SecurityContextImpl对象的Authentication
       // securityContextImpl.setAuthentication(auth);
/*
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if("anonymousUser".equals(principal)) {
            model.addAttribute("name","anonymous");
        }else {
            User user = (User)principal;
            //model.addAttribute("name",user.getUsername());
            System.out.print(user);
        }
        */
        Object obj=request.getSession().getAttribute("username");
        return "admin/"+path;
    }


    @GetMapping("/user/{path}")
    public String user(@PathVariable("path") String path)
    {
        return "user/"+path;
    }
}
