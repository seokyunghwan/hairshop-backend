package com.personalproject.hairshop.domain.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.personalproject.hairshop.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class PostDto {
	
	@Autowired
	UserRepository userRepository;
	
	@Builder
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SetPost{
		private Long id;
		private String title;
		private String content;
	}
}
