package com.webshop.utilSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.webshop.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserService userService;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;

	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
		throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
	        return authenticationManagerBuilder.build();
		}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf((csrf) -> csrf.disable())
       .authorizeHttpRequests(req -> req
    		   .anyRequest().permitAll()
    		   .requestMatchers(HttpMethod.GET, "/user/{userId}").authenticated()
    		   .requestMatchers(HttpMethod.DELETE, "/user/{userId}").authenticated()
    		   .requestMatchers(HttpMethod.PUT, "/user").authenticated()
    		   .requestMatchers("/customerOrderArticle").authenticated()
    		   .requestMatchers("/customerOrder").authenticated());
       http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
    }
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
