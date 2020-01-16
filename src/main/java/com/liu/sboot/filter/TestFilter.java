package com.liu.sboot.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Configuration
public class TestFilter {
    @Bean
    public FilterRegistrationBean testFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Myfilter());
        List<String> a = new ArrayList<>();
        a.add("/*");
        registrationBean.setUrlPatterns(a);
        Map<String, String> map = new HashMap<>();
        map.put("111", "222");
        registrationBean.setInitParameters(map);
        registrationBean.setName("filter");
        return registrationBean;
    }
}
