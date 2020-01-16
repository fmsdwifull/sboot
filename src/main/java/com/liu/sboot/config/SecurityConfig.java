package com.liu.sboot.config;

import com.liu.sboot.service.CustomUserService;
import com.liu.sboot.service.MyFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserService customUserService;

    @Autowired
    //FilterSecurityInterceptor myFilterSecurityInterceptor;
    MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //http.csrf().disable();
/*       为什么用这段代码js会出现异常，？？？
          http.authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                //.loginPage("/public/login").permitAll()
                .usernameParameter("userName").passwordParameter("passWord").loginPage("/public/login")
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll(); //注销行为任意访问
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .csrf().disable();*/

       http.csrf().ignoringAntMatchers("/druid/*");
        http.authorizeRequests().antMatchers("/").permitAll();
                  //               .antMatchers("/public/user/index").hasRole("user")
                  //               .antMatchers("/public/admin/index").hasRole("admin");
        http.formLogin().defaultSuccessUrl("/public/admin/index",true).usernameParameter("userName").passwordParameter("passWord").loginPage("/public/login");
        http.csrf().disable();
       //http.logout();
        //http.logout().logoutSuccessUrl("/url");
       http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());

        /*
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
         */
                //.withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("admin")
//         auth.inMemoryAuthentication().
//                passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("admin")
//                        .and()
//                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("user");
    }
}
