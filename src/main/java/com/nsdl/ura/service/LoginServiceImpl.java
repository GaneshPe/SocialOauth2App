/*package com.nsdl.ura.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nsdl.ura.entity.UserInfo;
import com.nsdl.ura.repository.UserRepository;
@Service
public class LoginServiceImpl implements LoginService{

	
	@Autowired
	UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo user=userRepository.findByEmail(email);
		if(user==null){
			throw new UsernameNotFoundException("User Not Found for :"+email);
		}
		GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+user.getRole());
		UserDetails userDetails=new User(user.getEmail(),user.getPassword(),Arrays.asList(authority));
		return userDetails;
	}

}
*/