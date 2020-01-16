package com.liu.sboot.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Myfilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter初始化");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
    HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println(request.getRequestURI()+"-----------------------aaaaa");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {}
}
