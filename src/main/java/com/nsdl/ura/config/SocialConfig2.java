//package com.nsdl.ura.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.core.env.Environment;
//import org.springframework.social.UserIdSource;
//import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.config.annotation.SocialConfigurerAdapter;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionRepository;
//import org.springframework.social.google.api.Google;
//import org.springframework.social.google.connect.GoogleConnectionFactory;
//import org.springframework.social.security.AuthenticationNameUserIdSource;
//
//@Configuration
//@EnableSocial
//public class SocialConfig2 extends SocialConfigurerAdapter{
//	
//	String  GoogleClientId="552134073241-9562plmbf50n568kd3v71mccrfbtqfeb.apps.googleusercontent.com";
//	String GoolgeClientSecret="hwpc3rpH3smq0awqmyxav94T";
//	
//	@Autowired
//	private DataSource dataSource;
//	
//	@Override
//	public UserIdSource getUserIdSource() {
//		return new AuthenticationNameUserIdSource();
//	}
//
//	@Override
//	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
//	    GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory("552134073241-9562plmbf50n568kd3v71mccrfbtqfeb.apps.googleusercontent.com", "hwpc3rpH3smq0awqmyxav94T");
//	    googleConnectionFactory.setScope("https://www.googleapis.com/auth/plus.login");
//	    connectionFactoryConfigurer.addConnectionFactory(googleConnectionFactory);
//	}
//	@Bean
//	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
//	public Google google(ConnectionRepository repository) {
//	    Connection<Google> connection = repository.findPrimaryConnection(Google.class);
//	    return connection != null ? connection.getApi() : null;
//	}
//}
