package com.liu.sboot.config;
import com.liu.sboot.service.CustomUsernamePasswordAuthentionProvider;
import com.liu.sboot.service.MyInvocationSecurityMetadataSourceService;
import com.liu.sboot.service.MyAccessDecisionManager;
import com.liu.sboot.service.CustomUserService;
//import com.liu.sboot.service.MyFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserService customUserService;
    @Autowired
    private CustomUsernamePasswordAuthentionProvider customUsernamePasswordAuthentionProvider;
    //@Autowired
    //FilterSecurityInterceptor myFilterSecurityInterceptor;
    //MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    //MyInvocationSecurityMetadataSourceService securityMetadataSource = new MyInvocationSecurityMetadataSourceService();

    @Autowired
    private MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

       http.csrf().ignoringAntMatchers("/druid/*");

    //    http.authorizeRequests()
    //            .antMatchers("/public/user/index").hasRole("user")
    //            .and()
    //            .formLogin();

        http.authorizeRequests().antMatchers("/").permitAll();
                  //               .antMatchers("/public/user/index").hasRole("user")
                  //               .antMatchers("/public/admin/index").hasRole("admin");
        http.formLogin().defaultSuccessUrl("/public/admin/index",true).usernameParameter("userName").passwordParameter("passWord").loginPage("/public/login");
        http.csrf().disable();

       //http.logout();
        //http.logout().logoutSuccessUrl("/url");
       ///http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }
    */



    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
               // .antMatchers( "/public/login").permitAll()
                .antMatchers( "/assets/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //.loginPage("/public/login").defaultSuccessUrl("/public/admin/index").usernameParameter("userName").passwordParameter("passWord").permitAll().and().logout().permitAll()
                .formLogin().loginPage("/public/login")//.defaultSuccessUrl("/public/admin/index",true).usernameParameter("userName").passwordParameter("passWord")
               .permitAll()
                //.formLogin().loginPage("/public/login").defaultSuccessUrl("/public/admin/index").permitAll().and().logout().permitAll()
                .and()
                .authorizeRequests()
                //.anyRequest()
                //.authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setAccessDecisionManager(myAccessDecisionManager);
                        fsi.setSecurityMetadataSource(myInvocationSecurityMetadataSourceService);
                        return fsi;
                    }
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
        auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(customUsernamePasswordAuthentionProvider);
    }



//    @Bean
//    public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
//        //MyInvocationSecurityMetadataSourceService securityMetadataSource = new MyInvocationSecurityMetadataSourceService();
//        return securityMetadataSource;
//    }

    //@Bean //AccessDecisionManager
    //public AccessDecisionManager myAccessDecisionManager() {
    //    return new MyAccessDecisionManager();
    //}

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
