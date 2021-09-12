package com.personalproject.hairshop.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personalproject.hairshop.domain.dto.UserDto;
import com.personalproject.hairshop.domain.dto.UserDto.SignupDto;
import com.personalproject.hairshop.domain.entity.Authority;
import com.personalproject.hairshop.domain.entity.User;
import com.personalproject.hairshop.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	//유저 등록
	public User signup(SignupDto userDto) {
		Authority authority;
		if (userDto.getManager()) {
			authority = Authority.builder()
					.authorityName("ROLE_MANAGER")
					.build();
		} else {
			authority = Authority.builder()
					.authorityName("ROLE_USER")
					.build();
		}

		User user = User.builder()
				.userId(userDto.getUserId())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.name(userDto.getName())
				.email(userDto.getEmail())
				.phoneNum(userDto.getPhoneNum())
				.authorities(Collections.singleton(authority))
				.build();

		return userRepository.save(user);
	}

	//아이디 체크
	public boolean idCheck(UserDto userDto) {
		if (userRepository.findOneWithAuthoritiesByUserId(userDto.getUserId()).orElse(null) != null) {
			return false;
		}
		return true;
	}

	//유저 검색
	public User getById(Long ownerId) {
		return userRepository.findById(ownerId).get();
	}
}
