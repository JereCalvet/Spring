package com.jeremias.oauthtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.jeremias.oauthtest.security.Authorities.*;

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
    localhost:8080/user/add          //permit all
    localhost:8080/user/all          //user admin
    localhost:8080/user/delete/id    //admin
    localhost:8080/user/update/id    //admin
    localhost:8080/user/find/id      //user admin
    localhost:8080/user/find/email   //user admin
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final var apiUserMethods = "/api/user/**";
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                .antMatchers(HttpMethod.DELETE, apiUserMethods).hasAuthority(USER_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, apiUserMethods).hasAuthority(USER_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, apiUserMethods).hasAuthority(USER_READ.getPermission())
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
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
