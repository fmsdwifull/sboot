package com.liu.sboot.componet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//@Component
public class AuthenticationExample {
    //@Autowired
    //private static  SimpleAuthenticationManager am;
    private  static  AuthenticationManager am = new SampleAuthenticationManager();
    public static void main(String[] args) throws Exception {
        String name = "test";
        String password = "test";
        try {
            // request就是第一步，使用name和password封装成为的token
            Authentication request = new UsernamePasswordAuthenticationToken(name, password);
            // 将token传递给Authentication进行验证
            Authentication result =  am.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            //break;
        } catch (AuthenticationException e) {
            System.out.println("认证失败：" + e.getMessage());
        }
        System.out.println("认证成功，Security context 包含：" + SecurityContextHolder.getContext().getAuthentication());
    }
}
