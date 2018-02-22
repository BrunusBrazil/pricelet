package com.wealth.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wealth.common.exception.BusinessException;
import com.wealthy.common.user.UserDTO;
import com.wealthy.common.user.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthController{
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService service;
	

	/**
	 * @param username
	 * @param password
	 * @param response
	 * @return JSON contains token and user after success authentication.
	 * @throws IOException
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
			HttpServletResponse response) throws IOException, BusinessException {
		String token = null;
		UserDTO appUser =  service.searchByUserName(username);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		
		if (appUser != null && appUser.getPassword().equals(password)) {
			token = Jwts.builder().setSubject(username)
					.claim("roles", appUser.getRoles())
					.setIssuedAt(new java.util.Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			
			appUser.setPassword(null);
			tokenMap.put("token", token);
			tokenMap.put("user", appUser);
		
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
	
		} else {
			tokenMap.put("token", null);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		}
 	}

}
