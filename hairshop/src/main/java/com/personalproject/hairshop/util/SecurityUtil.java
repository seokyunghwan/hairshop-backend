package com.personalproject.hairshop.util;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
	public SecurityUtil() {
	}
	
	public static Optional<String> getCurrentUsername() {
		System.out.println(15);
		//security context에서 authentication 객체를 꺼내와서 username을 꺼내주는 유틸성 메소드
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null) {
			logger.debug("Security Context에 인증 정보가 없습니다.");
			return Optional.empty();
		}
		
		String username = null;
		if(authentication.getPrincipal() instanceof UserDetails) {
			UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
			username = springSecurityUser.getUsername();
		} else if (authentication.getPrincipal() instanceof String) {
			username = (String) authentication.getPrincipal();
		}
		
		return Optional.ofNullable(username);
	}
}
