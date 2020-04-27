package com.nsdl.ura.config.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nsdl.ura.entity.UserInfo;

@Controller
public class GoogleController {

	public static String  GoogleClientId="552134073241-9562plmbf50n568kd3v71mccrfbtqfeb.apps.googleusercontent.com";
	public static String GoolgeClientSecret="hwpc3rpH3smq0awqmyxav94T";
	
	GoogleConnectionFactory googleFactory = new GoogleConnectionFactory(GoogleClientId,GoolgeClientSecret);
	
//	@Autowired
//	public ConnectionRepository connectionRepository;
	
	ConnectController coneContr;
	
	@GetMapping("/googlelogin1")
	public String googlelogin1(Principal user) {
		OAuth2Operations operations = googleFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("https://localhost:8080/URAPortal/googleforwardLogin");
		params.setScope("https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile");
		String url = operations.buildAuthenticateUrl(params);
		System.out.println("The URL is" + url);
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/googleforwardLogin1")
	public String  forwardLogin1(@RequestParam("code") String authorizationCode,Model model) {
		
		OAuth2Operations operations = googleFactory.getOAuthOperations();
		AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "https://localhost:8080/URAPortal/googleforwardLogin",null);
		System.out.println("The accessToken :"+accessToken);
//		System.out.println("The Connection repository in Google Controller ::"+connectionRepository);
//		
//		List<Connection<Google>> listConnections=connectionRepository.findConnections(Google.class);
//		System.out.println(listConnections.size());
//		
//		Connection<Google> connection=connectionRepository.findPrimaryConnection(Google.class);
		Connection<Google> connection = googleFactory.createConnection(accessToken); 
		System.out.println("connection ::"+connection);
		Google google = connection.getApi(); 
		Person user = google.plusOperations().getGoogleProfile();
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(user.getGivenName());
		userInfo.setLastName(user.getFamilyName());
		userInfo.setEmail(user.getGivenName());
		System.out.println("Google The First name and Last name and email are :"+userInfo.getFirstName()+"  "+userInfo.getLastName()+"  "+userInfo.getEmail());
		model.addAttribute("user", userInfo);
		return "facebooksignup";
	}
	
	@GetMapping("/googlelogin")
	public String googlelogin(Principal user) {
		OAuth2Operations operations = googleFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8080/URAPortal/googleforwardLogin");
		params.setScope("profile");
		params.setScope("openid");
        params.setScope("email");
        
        String authorizeUrl = operations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
		//String url = operations.buildAuthenticateUrl(params);
		System.out.println("The URL is" + authorizeUrl);
		return "redirect:" + authorizeUrl;
	}
	
	@RequestMapping(value = "/googleforwardLogin")
	public String  forwardLogin(@RequestParam("code") String authorizationCode,Model model) {
		
		OAuth2Operations operations = googleFactory.getOAuthOperations();
		AccessGrant accessGrant = operations.exchangeForAccess(authorizationCode, "http://localhost:8080/URAPortal/googleforwardLogin",null);
		System.out.println("The accessToken :"+accessGrant);
		Connection<Google> connection = googleFactory.createConnection(accessGrant);
		
		System.out.println("connection ::"+connection);
		Google google = connection.getApi(); 
		Person user = google.plusOperations().getGoogleProfile();
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(user.getGivenName());
		userInfo.setLastName(user.getFamilyName());
		userInfo.setEmail(user.getGivenName());
		System.out.println("Google The First name and Last name and email are :"+userInfo.getFirstName()+"  "+userInfo.getLastName()+"  "+userInfo.getEmail());
		model.addAttribute("user", userInfo);
		return "facebooksignup";
	}
	
	
	
	
}
