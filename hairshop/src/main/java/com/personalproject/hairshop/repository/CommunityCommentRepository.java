package com.personalproject.hairshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalproject.hairshop.domain.entity.CommunityComment;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long>{

}
