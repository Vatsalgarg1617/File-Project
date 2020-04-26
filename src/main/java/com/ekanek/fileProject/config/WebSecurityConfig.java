package com.ekanek.fileProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService customUserDetailService;
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
		 http
			.authorizeRequests()
			.antMatchers("/dev-test/*").permitAll()
			.anyRequest().authenticated()
			.and().formLogin()
			.and()
			.csrf().disable();
	    }

	   @Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
	    
	    @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	    	auth.parentAuthenticationManager(authenticationManagerBean())
			.userDetailsService(customUserDetailService);
		}

}
