package com.nsdl.ura.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SocialNetworkSecurityConfig extends WebSecurityConfigurerAdapter{

	/*@Autowired
	LoginService loginService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGobal(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception {
		authenticationManagerBuilder.userDetailsService(loginService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()throws Exception  {
		return super.authenticationManagerBean();
	} */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest()
//		.authenticated().and().oauth2Login();
		http.csrf().disable();
	}
	
	
}
