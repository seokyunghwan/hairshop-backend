package com.personalproject.hairshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personalproject.hairshop.jwt.JwtAccessDeniedHandler;
import com.personalproject.hairshop.jwt.JwtAuthenticationEntryPoint;
import com.personalproject.hairshop.jwt.JwtSecurityConfig;
import com.personalproject.hairshop.jwt.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired TokenProvider tokenProvider;
	@Autowired JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web
				.ignoring()
				.antMatchers("/h2-console/**", "/favicon.ico")
				;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
				.csrf().disable()
				
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(jwtAccessDeniedHandler)
				
//		.and()	//h2-console을 위한 설정
//				.headers()
//				.frameOptions()
//				.sameOrigin()
		
		.and()	//session 사용x
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
				.authorizeRequests()
				/*.antMatchers("/api/reservation/getAllReservation*").permitAll()
				.antMatchers().hasAnyRole("USER")
				.antMatchers().hasAnyRole("MANAGER")
				.antMatchers().hasAnyRole("USER", "MANAGER", "ADMIN")
				.antMatchers().hasAnyRole("ADMIN")
				.antMatchers("/api/authenticate/*", "/api/user/idCheck", "/api/user", "/api/user/me",  "/api/reservation/*", "/api/reservation/getAllReservation*").permitAll()
				.antMatchers("/api/authenticate", "/api/user/getAllBookmark*", "/api/reservation/mystyle*", "/api/reservation/review/*", "/api/reservation/review*").permitAll()
				.antMatchers("/api/signup", "/api/news*", "/api/news/*", "/api/community*", "/api/community/*", "/managerMain/*").permitAll()
				.antMatchers("/api/shop*", "/api/shop/*", "/api/shop/shopImg/*", "/shopImg/*", "/api/shop/myshop/*", "/api/user/bookmark*", "/api/user/bookmark/*", "/api/user/test*", "/api/news/addComment", "/api/community/getAllCommunity").permitAll()
				.antMatchers().hasAnyRole("ROLE_USER")*/
				.anyRequest().permitAll()
				
		.and()
				.formLogin()
//				.loginPage("/index.html")
				.permitAll()
				
		.and()
				.apply(new JwtSecurityConfig(tokenProvider))
				
		
		;
	}
	
	
}
