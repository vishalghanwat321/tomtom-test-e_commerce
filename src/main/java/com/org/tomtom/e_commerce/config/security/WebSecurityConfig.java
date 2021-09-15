package com.org.tomtom.e_commerce.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // TODO Keeping this simple so API's will be invoked easily, later we can
					// enhance the rules
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable().and().authorizeRequests().antMatchers("/**", "/swagger**")
				.authenticated().antMatchers("/v2**", "/status/**", "/h2-console/**").permitAll().and().httpBasic()
				.and().csrf().disable();
	}
}
