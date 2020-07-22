package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.JWTAuthFilter;

@SpringBootApplication
public class BizziApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizziApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.csrf().disable()
				.addFilterAfter(new JWTAuthFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.anyRequest().authenticated();
		}
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
