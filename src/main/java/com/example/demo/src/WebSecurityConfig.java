package com.example.demo.src;

import com.example.demo.src.user.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//                .authorizeRequests()
//                .antMatchers("/restaurants/**", "/services", "/categories/**", "/menu/**", "/reviews/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutUrl("/users/logout")
//                .invalidateHttpSession(true)
//                .permitAll();
    }
}
