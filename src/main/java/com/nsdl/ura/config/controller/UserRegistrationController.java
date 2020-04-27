package com.nsdl.ura.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRegistrationController {

	/*
	 * @GetMapping("/") public String getIndexPage() { return "index"; }
	 */
	
	/*
	@Autowired
	UserService userService; */
	
	/*@Autowired
	SecurityService securityService; */
	
	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
	
	/*
	@PostMapping("/register")
	public String userRegistration(@ModelAttribute UserInfo userInfo,HttpServletRequest request,Model model) {
		System.out.println("#######  registration ############");
		String password=userInfo.getPassword();
		UserInfo dbUser=userService.save(userInfo);
		model.addAttribute("user",dbUser);
		//securityService.autoLogin(dbUser.getEmail(), password, dbUser.getRole(), request);
		System.out.println("######## ADMIN  ##############");
		return "userprofile";
	}
	*/
}
