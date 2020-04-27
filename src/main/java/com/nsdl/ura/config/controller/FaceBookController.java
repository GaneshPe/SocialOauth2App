package com.nsdl.ura.config.controller;

import org.springframework.social.facebook.api.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nsdl.ura.entity.UserInfo;


@Controller
public class FaceBookController {

	private FacebookConnectionFactory factory = new FacebookConnectionFactory("521737381850048","d70d2ceecfde05282be619eaff6ecc52");
	
	@GetMapping("/facebooklogin")
	public String facebooklogin() {
		
		OAuth2Operations operations = factory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("https://localhost:8080/URAPortal/forwardLogin");
		params.setScope("email,public_profile");
		String url = operations.buildAuthenticateUrl(params);
		System.out.println("The URL is" + url);
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/forwardLogin")
	public String  forwardLogin(@RequestParam("code") String authorizationCode,Model model) {
		
		OAuth2Operations operations = factory.getOAuthOperations();
		AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "https://localhost:8080/URAPortal/forwardLogin",null);
		
		System.out.println("The Accesstoken is :"+accessToken);
		Connection<Facebook> connection = factory.createConnection(accessToken);
		Facebook facebook = connection.getApi();
		String[] fields1 = {"first_name", "last_name" ,"email"};
		User userProfile1 = facebook.fetchObject("me", User.class, fields1);
		
		String[] fields2 = {"email", "last_name" ,"first_name"};
		User userProfile2 = facebook.fetchObject("me", User.class, fields2);
		
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(userProfile1.getFirstName());
		userInfo.setLastName(userProfile1.getLastName());
		userInfo.setEmail(userProfile2.getEmail());
		System.out.println("The First name and Last name and email are :"+userInfo.getFirstName()+"  "+userInfo.getLastName()+"  "+userInfo.getEmail());
		model.addAttribute("user", userInfo);
		return "userprofile";
	}
}
