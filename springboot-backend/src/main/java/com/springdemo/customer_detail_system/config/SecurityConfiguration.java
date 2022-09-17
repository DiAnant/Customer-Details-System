package com.springdemo.customer_detail_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;

 @EnableWebSecurity
 public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    UserDetailsService userDetailsService;

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // writing this requires correct credentials for each request
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable().
        authorizeRequests().        
        antMatchers(HttpMethod.POST, "/api/v2/user/login/**").permitAll().
        antMatchers(HttpMethod.POST, "/api/v2/user/", "/api/v2/user", "/api/v2/user/change-role/**").hasAuthority("ROOT").
        antMatchers(HttpMethod.GET, "/api/v2/user/", "/api/v2/user").hasAuthority("ROOT").
        antMatchers(HttpMethod.POST, "/api/v1/customers/", "/api/v1/customers").hasAnyAuthority("ADMIN", "ROOT").
        antMatchers(HttpMethod.GET, "/api/v1/customers/", "/api/v1/customers").hasAnyAuthority("EMPLOYEE", "ADMIN", "ROOT").
        anyRequest().
        authenticated().
        and().
        httpBasic();
	}

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
 }