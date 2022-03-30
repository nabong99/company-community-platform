package com.devcommunity.nabong.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors()
                .and()
                    .csrf().disable();

        http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/api/user/**").permitAll()
    //                .antMatchers("/manager/**").authenticated()
                    .antMatchers("/manage/insttManageList").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .and();
//                    .formLogin()
//                    .loginPage("http://localhost:8080/user/Login")

    }
}
