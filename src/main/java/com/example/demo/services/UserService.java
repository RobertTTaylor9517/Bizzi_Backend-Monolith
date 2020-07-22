package com.example.demo.services;

import java.util.Date;
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
	
	@Value("${JWT_TOKEN}")
	private String SECRET;
	
	public String signup(String email, String password) {
		User user = new User();
		
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		
		if(userRepository.save(user) != null) {
			String token = getJWTToken(email);
			
			return token;
		}else {
			return "Email Error";
		}
	}
	
	public String login(String email, String password) {
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			return "Login Error";
		}else {
			if(passwordEncoder.matches(password, user.getPassword())) {
				String token = getJWTToken(email);
				return token;
			}else {
				return "Login Error";
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
