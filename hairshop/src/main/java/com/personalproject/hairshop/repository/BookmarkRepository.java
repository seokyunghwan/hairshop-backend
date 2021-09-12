package com.personalproject.hairshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.personalproject.hairshop.domain.entity.Bookmark;
import com.personalproject.hairshop.domain.entity.User;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	@Transactional
	void deleteByUserIdAndShopId(Long userId, Long shopId);

	List<Bookmark> findAllByUser(User byId);

}
