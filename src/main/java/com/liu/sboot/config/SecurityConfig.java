package com.liu.sboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //http.csrf(csrf -> csrf.disable());
        http.csrf().ignoringAntMatchers("/druid/*");
        http.authorizeRequests().antMatchers("/").permitAll()
                                 .antMatchers("/public/user/index").hasRole("user")
                                 .antMatchers("/public/admin/index").hasRole("admin");
        http.formLogin().usernameParameter("userName").passwordParameter("passWord").loginPage("/public/login");
       //http.logout();
        //http.logout().logoutSuccessUrl("/url");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        /*
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
         */
                //.withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("admin")
          auth.inMemoryAuthentication().
                passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("admin")
                        .and()
                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("user");
    }
}
