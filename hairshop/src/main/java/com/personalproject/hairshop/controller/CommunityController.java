package com.personalproject.hairshop.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalproject.hairshop.domain.dto.PostDto.SetPost;
import com.personalproject.hairshop.domain.entity.Community;
import com.personalproject.hairshop.domain.entity.CommunityComment;
import com.personalproject.hairshop.domain.entity.News;
import com.personalproject.hairshop.repository.CommunityRepository;
import com.personalproject.hairshop.repository.UserRepository;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

	@Autowired
	CommunityRepository communityRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/getAllCommunity")
	public Page<Community> getAllCommunity(@PageableDefault(size = 20, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){
		return communityRepository.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public Community getCommunity(@PathVariable Long id) {
		return communityRepository.findById(id).get();
	}
	
	@PostMapping("/addComment")
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public Community addComment(@RequestBody Map<String, String> commentVO) {
		Optional<Community> community = communityRepository.findById(Long.parseLong(commentVO.get("postId")));
		CommunityComment cc = CommunityComment.builder()
				.writer(userRepository.findById(Long.parseLong(commentVO.get("userId"))).get())
				.commentCont(commentVO.get("comment"))
				.build();
		
		community.get().addComment(cc);
		
		return communityRepository.save(community.get());
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public Community setCommunity(@RequestBody SetPost postDto) {
		return communityRepository.save(
				Community.builder()
				.writer(userRepository.findById(postDto.getId()).get())
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.build());
	}
}
