package com.example.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() throws Exception{

        UserDetails admin = User.builder()
                .username("ajmal")
                .password(passwordEncoder().encode("123"))
                .roles("admin")
                .build();

        UserDetails user =User.builder()
                .username("ashish")
                .password(passwordEncoder().encode("123"))
                .roles("user")
                .build();

        UserDetails user1 =User.builder()
                .username("sam")
                .password(passwordEncoder().encode("123"))
                .roles("user1")
                .build();




        return new  InMemoryUserDetailsManager(admin,user,user1);

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/",true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

