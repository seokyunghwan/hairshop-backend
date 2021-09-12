package com.personalproject.hairshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personalproject.hairshop.domain.dto.UserDto;
import com.personalproject.hairshop.domain.dto.UserDto.SignupDto;
import com.personalproject.hairshop.domain.entity.Bookmark;
import com.personalproject.hairshop.domain.entity.Shop;
import com.personalproject.hairshop.domain.entity.User;
import com.personalproject.hairshop.repository.BookmarkRepository;
import com.personalproject.hairshop.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private BookmarkRepository bookmarkRepository;

	//회원가입
	@PostMapping
	public ResponseEntity<User> userRegister(@Valid @RequestBody SignupDto userDto) {
		return ResponseEntity.ok(userService.signup(userDto));
	}

	@PostMapping("/idCheck")
	public boolean userIdCheck(@RequestBody UserDto userDto) {
		return userService.idCheck(userDto);
	}

	//로그인 유저 받아오기
	@GetMapping("/me")
	@PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
	public ResponseEntity<UserDto> getMyUserInfo(@AuthenticationPrincipal UserDto userDto) {
		return ResponseEntity.ok(userDto);
	}

	//즐겨찾기 설정
	@PostMapping("/bookmark")
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public boolean setBookmark(@RequestParam User userId, @RequestParam Shop shopId) {
		Bookmark bm = Bookmark.builder()
				.user(userId)
				.shop(shopId)
				.build();

		bookmarkRepository.save(bm);
		return true;
	}

	//즐겨찾기 해제
	@DeleteMapping("/bookmark")
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public boolean deleteBookmark(@RequestParam User userId, @RequestParam Shop shopId) {
		bookmarkRepository.deleteByUserIdAndShopId(userId.getId(), shopId.getId());
		return false;
	}

	//즐겨찾기 받아오기
	@GetMapping("/getAllBookmark")
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public List<Bookmark> getAllBookmark(@RequestParam Long id) {
		return bookmarkRepository.findAllByUser(userService.getById(id));
	}

}
