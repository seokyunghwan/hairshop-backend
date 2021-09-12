package com.personalproject.hairshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalproject.hairshop.domain.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{

	News getByTitle(String title);
	List<News> findAll();
}
