package com.example.demo.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repo.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${jwt.secret}")
	private String SECRET;
	
	public HashMap<String, Object> signup(String email, String password) {
		User user = new User();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		
		if(userRepository.save(user) != null) {
			String token = getJWTToken(email);
			map.put("token", token);
			map.put("user_id", user.getId());
			
			return map;
			
			
		}else {
			map.put("Error", "Error submitting user");
			return map;
		}
	}
	
	public HashMap<String, Object> login(String email, String password) {
		User user = userRepository.findByEmail(email);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if(user == null) {
			map.put("Error", "Login Error");
			return map;
		}else {
			if(passwordEncoder.matches(password, user.getPassword())) {
				String token = getJWTToken(email);
				map.put("token", token);
				map.put("user_id", user.getId());
				
				return map;
			}else {
				map.put("Error", "Login Error");
				return map;
			}
		}
	}
	
	private String getJWTToken(String email) {
		
		String secretKey = SECRET;
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("Bizzi Token")
				.setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream()
							.map(GrantedAuthority::getAuthority)
							.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		
		return "Biz " + token;
	}
	
}
