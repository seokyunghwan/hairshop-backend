package com.personalproject.hairshop.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalproject.hairshop.domain.entity.Reservation;
import com.personalproject.hairshop.domain.entity.Shop;
import com.personalproject.hairshop.domain.entity.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	List<Reservation> findAllByShopAndStartDateBetween(Shop shop, Date startOfWeek, Date endTime);

	Page<Reservation> findAllByUser(User user, Pageable pageable);
	
	Page<Reservation> findAllByUserAndServiceContentNotNull(User user, Pageable pageable);

	Page<Reservation> findAllByShopAndReviewNotNull(Pageable pageable, Shop shop);

	List<Reservation> findAllByShopAndServiceContentNull(Shop shop);
}
