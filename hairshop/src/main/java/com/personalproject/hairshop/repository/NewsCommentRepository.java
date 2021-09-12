package com.personalproject.hairshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalproject.hairshop.domain.entity.NewsComment;

public interface NewsCommentRepository extends JpaRepository<NewsComment, Long>{

}
