package com.example.bishe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@EnableWebSecurity  //开启MVC security安全支持
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码需要设置编码器
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

        //2.使用JDBC进行身份认证
        String userSQL="select username,password,role from bs_user "+
                "where username =?";
        String roleSQL="select username,role from bs_user where" +
                " username =?";
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder)
                .usersByUsernameQuery(userSQL)
                .authoritiesByUsernameQuery(roleSQL);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/","/zhuce","/userLogin","/index","/introduce").permitAll()
                .mvcMatchers("/img/**").permitAll()
                //静态文件放行
                .antMatchers("/login/**").permitAll().anyRequest()
                .authenticated()
                .and().formLogin();

        //自定义用户登录控制
        http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("user").passwordParameter("pwd")
                .defaultSuccessUrl("/index");

        //自定义用户退出
        http.logout()
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/userLogin");
        //记住我token持久化
        http.rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(200)
                .tokenRepository(tokenRepository());
    }

//持久化token存储
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl jr=new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }


}
