package com.liu.sboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Permission;
import java.util.*;

@Service
public class MyInvocationSecurityMetadataSourceService  implements
        FilterInvocationSecurityMetadataSource {
    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    /*
     * 这个例子放在构造方法里初始化url权限数据，我们只要保证在 getAttributes()之前初始好数据就可以了
     */
    public MyInvocationSecurityMetadataSourceService() {
        Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<>();
        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/public/admin/index");
        SecurityConfig config = new SecurityConfig("admin");
        ArrayList<ConfigAttribute> configs = new ArrayList<>();
        configs.add(config);
        map.put(matcher,configs);

        AntPathRequestMatcher matcher2 = new AntPathRequestMatcher("/public/user/index");
        SecurityConfig config2 = new SecurityConfig("user");
        ArrayList<ConfigAttribute> configs2 = new ArrayList<>();
        configs2.add(config2);
        map.put(matcher2,configs2);

        this.requestMap = map;
    }


    //此方法用于鉴权过程中获取当前的请求URL需要哪种权限
    public List<ConfigAttribute> getAttributes(Object object) {
      FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

        // Lookup your database (or other source) using this information and populate the
        // list of attributes
        attributes.add(new SecurityConfig("ROLE_admin"));
        attributes.add(new SecurityConfig("ROLE_user"));
        attributes.add(new SecurityConfig("ROLE_ANONYMOUS"));

        return attributes;
        //return null;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}