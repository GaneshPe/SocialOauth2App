/*package com.nsdl.ura.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter{

	static String  GoogleClientId="552134073241-9562plmbf50n568kd3v71mccrfbtqfeb.apps.googleusercontent.com";
	static String GoolgeClientSecret="hwpc3rpH3smq0awqmyxav94T";
	
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}
	
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
		connectionFactoryConfigurer.addConnectionFactory(new GoogleConnectionFactory(GoogleClientId,GoolgeClientSecret));
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		if(connectionFactoryLocator != null) {
			System.out.println("######## connectionFactoryLocator  ########" );
		}
		JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator,
																							Encryptors.noOpText());
		if(usersConnectionRepository != null) {
			System.out.println("######## JdbcUsersConnectionRepository  ########" );
		}
		usersConnectionRepository.setConnectionSignUp(null);
		return usersConnectionRepository;
	}
	
	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		if(connectionRepository != null) {
			System.out.println("################## connectionRepository ###########  ");
		}
	      return new ConnectController(connectionFactoryLocator, connectionRepository);
	}
	//java.lang.IllegalAccessError: class org.springframework.social.connect.jdbc.JdbcConnectionRepository$$EnhancerBySpringCGLIB$$8fe70ebd cannot access its superclass org.springframework.social.connect.jdbc.JdbcConnectionRepository
	
	
	 @Bean
	 @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	 public Google google(ConnectionRepository repository) {
		 Connection<Google> connection = repository.findPrimaryConnection(Google.class);
		 if(connection != null) {
			 System.out.println("ajsdhajf#############zdnfs,zfb");
		 }else {
			 System.out.println("############ null ###########");
		 }
		 
		 return connection != null ? connection.getApi() : null;
	 }
	
	/*@Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new GoogleConnectionFactory(GoogleClientId,GoolgeClientSecret));
		return registry;
	} 
}*/
