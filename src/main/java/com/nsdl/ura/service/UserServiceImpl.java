/*package com.nsdl.ura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nsdl.ura.entity.UserInfo;
import com.nsdl.ura.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	private static   BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	public static String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public UserInfo save(UserInfo userInfo) {
		userInfo.setRole("ADMIN");
		userInfo.setPassword(getEncodedPassword(userInfo.getPassword()));
		return userRepository.save(userInfo);
	}
}*/
