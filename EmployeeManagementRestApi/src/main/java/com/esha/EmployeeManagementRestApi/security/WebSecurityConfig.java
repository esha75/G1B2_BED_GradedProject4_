package com.esha.EmployeeManagementRestApi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.esha.EmployeeManagementRestApi.serviceimpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().requestMatchers("/login").permitAll()
		.requestMatchers(HttpMethod.POST, "/api/user", "/api/role").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET, "/api/employees/employeesList", "/api/employees/{employee_id}").hasAnyAuthority("USER", "ADMIN")
		.requestMatchers(HttpMethod.POST, "/api/employees/add").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.PUT, "/api/employees/update").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/api/employees/delete/*").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET,  "/api//user/usersList","/api/user/search/*").hasAnyAuthority( "ADMIN")
		.requestMatchers(HttpMethod.GET, "/api/employees/search/*", "/api/employees/sort/*").hasAnyAuthority("USER", "ADMIN")
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().formLogin()
		.and().logout().logoutSuccessUrl("/login").permitAll()
		.and().cors().and().csrf().disable();
		return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider myAuthenticationProvider() {
		DaoAuthenticationProvider  daoAuthenticationProvider= new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(myUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(myPasswordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public PasswordEncoder myPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService myUserDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder getPassWordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
