package com.jeremias.oauthtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.jeremias.oauthtest.security.Authorities.*;
import static com.jeremias.oauthtest.security.Roles.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /*
    endpoints
    localhost:8080/user/all
    localhost:8080/user/add
    localhost:8080/user/delete/id
    localhost:8080/user/update/id
    localhost:8080/user/find/id
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").hasAnyRole(USER.getRole(), ADMIN.getRole())
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasAuthority(USER_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/user/**").hasAuthority(USER_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/api/user/**").hasAuthority(USER_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/user/**").hasAuthority(USER_READ.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
