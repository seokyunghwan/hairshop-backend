package com.personalproject.hairshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalproject.hairshop.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{	
	//User entity에 매핑되는 UserRepository
	@EntityGraph(attributePaths = "authorities")
	Optional<User> findOneWithAuthoritiesByUserId(String id);
	//findOneWithAuthoritiesByUsername 메소드는 username을 기준으로
	//User정보를 가져올 때 권한 정보도 같이 가져오는 메소드
	
	User getById(Long id);
}
