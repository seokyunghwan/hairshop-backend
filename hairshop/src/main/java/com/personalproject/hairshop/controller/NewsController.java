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
import com.personalproject.hairshop.domain.entity.News;
import com.personalproject.hairshop.domain.entity.NewsComment;
import com.personalproject.hairshop.repository.NewsRepository;
import com.personalproject.hairshop.repository.UserRepository;

@RestController
@RequestMapping("/api/news")
public class NewsController {

	@Autowired
	NewsRepository newsRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/{id}")
	public News getNews(@PathVariable Long id) {
		return newsRepository.findById(id).get();
	}

	@PostMapping("/addComment")
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public News addComment(@RequestBody Map<String, String> commentVO) {
		Optional<News> news = newsRepository.findById(Long.parseLong(commentVO.get("postId")));
		NewsComment nc = NewsComment.builder()
				.writer(userRepository.findById(Long.parseLong(commentVO.get("userId"))).get())
				.commentCont(commentVO.get("comment"))
				.build();

		news.get().addComment(nc);

		return newsRepository.save(news.get());
	}

	@GetMapping("/getAllNews")
	public Page<News> getAllNews(
			@PageableDefault(size = 20, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable) {
		return newsRepository.findAll(pageable);
	}

	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public News setNews(@RequestBody SetPost postDto) {
		return newsRepository.save(
				News.builder()
				.writer(userRepository.findById(postDto.getId()).get())
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.build());
	}
}
