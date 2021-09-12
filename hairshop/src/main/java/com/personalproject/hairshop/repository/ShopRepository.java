package com.personalproject.hairshop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalproject.hairshop.domain.entity.Shop;
import com.personalproject.hairshop.domain.entity.User;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{
	Page<Shop> findAllByShopNameContainsIgnoreCase(String shopName, Pageable pageable);
	
	Optional<Shop> findById(Long id);
	
	Optional<Shop> findByOwner(User user);
}
