package com.nsdl.ura;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialNetworkLoginBootApplication extends SpringBootServletInitializer{	

	
	@Value("${tomcat.ajp.port}")
	private int ajpPort;
	
	@Value("${tomcat.ajp.port.scheme}")
	private String ajpScheme;
	
	@Value("${tomcat.ajp.port.connector}")
	private String ajpConnector;
	
	@Value("${tomcat.ajp.port.secure}")
	private boolean ajpSecure;
	
	@Value("${tomcat.ajp.port.allowTrace}")
	private boolean ajpAloowTrace;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkLoginBootApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SocialNetworkLoginBootApplication.class);
	}

	
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory>  servletContainer() {
		return server ->{
			if(server instanceof TomcatServletWebServerFactory) {
				((TomcatServletWebServerFactory)server).addAdditionalTomcatConnectors(redirectConnector());
			}
		};
	}
	
	private Connector redirectConnector() {
		Connector connector=new Connector(this.ajpConnector);
		connector.setScheme(this.ajpScheme);
		connector.setPort(this.ajpPort);
		connector.setSecure(this.ajpSecure);
		connector.setAllowTrace(this.ajpAloowTrace);
		return connector;
	} 
}
