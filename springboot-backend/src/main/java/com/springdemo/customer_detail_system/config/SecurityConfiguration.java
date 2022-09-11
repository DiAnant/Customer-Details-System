package com.springdemo.customer_detail_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;


/*

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.cognizant.customer_detail_system.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // disabling the csrf isn't really the best way of dealing with this, need to fix !!!
        http.csrf().disable();
        http.authorizeRequests().        
        antMatchers(HttpMethod.POST, "/api/v1/customers/", "/api/v1/customers").hasAuthority("ADMIN").
        antMatchers(HttpMethod.GET, "/api/v1/customers/", "/api/v1/customers").hasAnyAuthority("CUSTOMER", "ADMIN").
        anyRequest().
        authenticated().
        and().
        httpBasic();
        return http.build();
    }

    @Bean
    protected InMemoryUserDetailsManager configureAuthentication(){
        
        List<UserDetails> userDetails = new ArrayList<>();
        // List<GrantedAuthority> customerRoles = new ArrayList<>();
        // customerRoles.add(new SimpleGrantedAuthority("CUSTOMER"));
        // List<GrantedAuthority> adminRoles = new ArrayList<>();
        // adminRoles.add(new SimpleGrantedAuthority("ADMIN"));
        
        
        // userDetails.add(new User("user_customer", this.PasswordEncoder().encode("password"), customerRoles));
        // userDetails.add(new User("user_admin", this.PasswordEncoder().encode("password"), adminRoles));

        userDetails.add(new User("user_customer", this.PasswordEncoder().encode("password"), "CUSTOMER"));
        userDetails.add(new User("user_admin", this.PasswordEncoder().encode("password"), "ADMIN"));
        return new InMemoryUserDetailsManager(userDetails);

        
    }

    @Bean
    public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
 */

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
        http.csrf().disable().
        authorizeRequests().        
        antMatchers(HttpMethod.POST, "/api/v1/customers/", "/api/v1/customers").hasAuthority("ADMIN").
        antMatchers(HttpMethod.POST, "/api/v2/user/", "/api/v2/user").hasAnyAuthority("CUSTOMER", "ADMIN").
        antMatchers(HttpMethod.GET, "/api/v2/user/", "/api/v2/user").hasAnyAuthority("CUSTOMER", "ADMIN").
        antMatchers(HttpMethod.GET, "/api/v1/customers/", "/api/v1/customers").hasAnyAuthority("CUSTOMER", "ADMIN").
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