// package com.cognizant.customer_detail_system.config;

// import java.util.ArrayList;
// import java.util.List;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.*;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration{
    
//     @Bean
//     protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//         http.csrf().disable();
//         http.authorizeRequests().        
//         antMatchers(HttpMethod.POST, "/customers/").hasRole("ADMIN").
//         antMatchers(HttpMethod.GET, "/customers/").hasAnyRole("CUSTOMER", "ADMIN").
//         anyRequest().
//         authenticated().
//         and().
//         formLogin();
//         return http.build();
//     }

//     @Bean
//     protected InMemoryUserDetailsManager configureAuthentication(){

//         List<UserDetails> userDetails = new ArrayList<>();
//         List<GrantedAuthority> customerRoles = new ArrayList<>();
//         customerRoles.add(new SimpleGrantedAuthority("CUSTOMER"));
//         List<GrantedAuthority> adminRoles = new ArrayList<>();
//         adminRoles.add(new SimpleGrantedAuthority("ADMIN"));
        
        

//         userDetails.add(new User("user_customer", this.PasswordEncoder().encode("password"), customerRoles));
//         userDetails.add(new User("user_admin", this.PasswordEncoder().encode("password"), adminRoles));
//         return new InMemoryUserDetailsManager(userDetails);
//     }

//     @Bean
//     public PasswordEncoder PasswordEncoder(){
//         return new BCryptPasswordEncoder(10);
//     }
// }
