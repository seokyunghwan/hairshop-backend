package com.personalproject.hairshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personalproject.hairshop.domain.dto.ReservationDto;
import com.personalproject.hairshop.domain.dto.ReservationDto.ReservationGet;
import com.personalproject.hairshop.domain.dto.ReservationDto.ReviewGet;
import com.personalproject.hairshop.domain.dto.ReservationDto.ReviewSet;
import com.personalproject.hairshop.domain.entity.Reservation;
import com.personalproject.hairshop.domain.entity.Shop;
import com.personalproject.hairshop.repository.ReservationRepository;
import com.personalproject.hairshop.repository.ShopRepository;
import com.personalproject.hairshop.repository.UserRepository;

@Service
@Transactional
public class ReservationService {
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ShopRepository shopRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	//예약 등록
	public ReservationGet setReservation(ReservationDto.ReservationSet reservationDto) {
		Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
		reservation.setUser(userRepository.findById(reservationDto.getUser()).get());
		reservation.setShop(shopRepository.findById(reservationDto.getShop()).get());
		return modelMapper.map(reservationRepository.save(reservation), ReservationDto.ReservationGet.class);
	}

	//내 예약 가져오기
	public Page<ReservationDto.ReservationGet> getMyReservation(Long id, Pageable pageable) {
		Page<Reservation> reservationList = reservationRepository.findAllByUser(userRepository.findById(id).get(), pageable);
		Page<ReservationDto.ReservationGet> dtoPage = reservationList.map(new Function<Reservation, ReservationDto.ReservationGet>() {
			@Override
			public ReservationDto.ReservationGet apply(Reservation t) {
				return modelMapper.map(t, ReservationDto.ReservationGet.class);
			}
			
		});
		return dtoPage;
	}
	
	//매장id로 예약 받아오기
	public List<ReservationDto.ReservationGet> getReservationsByShop(Date date, Long id){
		Date startOfWeek = null;
		Date endTime = null;
		List<ReservationDto.ReservationGet> reservation = new ArrayList<>();
		for(int i=0 ; i<7 ; i++) {
			startOfWeek  = new Date(date.getYear(), date.getMonth(), date.getDate()-date.getDay()+i); 
			endTime = new Date(date.getYear(), date.getMonth(), date.getDate()-date.getDay()+i , 23, 59, 59);
			for(Reservation r : reservationRepository.findAllByShopAndStartDateBetween(shopRepository.findById(id).get(), startOfWeek, endTime)) {
				ReservationDto.ReservationGet resrvationDto = ReservationDto.ReservationGet.builder()
						.id(r.getId())
						.user(r.getUser())
						.shop(r.getShop())
						.resDate(r.getResDate())
						.startDate(r.getStartDate())
						.endDate(r.getEndDate())
						.serviceName(r.getServiceName())
						.serviceContent(r.getServiceContent())
						.price(r.getPrice())
						.build();
				
				reservation.add(resrvationDto);
			}
		}
		return reservation;
	}
	
	
	//예약 취소
	public void deleteReservation(Long id) {
		reservationRepository.deleteById(id);
	}

	//시술 내역 기록
	public void addStyle(ReservationGet reservationDto) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationDto.getId());
		
		reservation.ifPresent(res-> { 
			reservationRepository.save(modelMapper.map(reservationDto, Reservation.class));
		});
	}

	//리뷰 등록
	public ReservationGet setReview(ReviewSet reviewDto) {
		Optional<Reservation> reservation = reservationRepository.findById(reviewDto.getId());
		reservation.get().setReview(reviewDto.getReview());
		return modelMapper.map(reservationRepository.save(reservation.get()), ReservationGet.class);
	}
	
	//리뷰 받아오기
	public Page<ReviewGet> getReview(Pageable pageable, Long id) {
		Page<Reservation> reservationList = reservationRepository.findAllByShopAndReviewNotNull(pageable, shopRepository.findById(id).get());
		Page<ReviewGet> dtoPage = reservationList.map(new Function<Reservation, ReviewGet>(){
			@Override
			public ReviewGet apply(Reservation t) {
				return modelMapper.map(t, ReviewGet.class);
			}
		});
		return dtoPage;
	}

	public Map<String, List<Reservation>> getMainForManager(Long id) {
		Shop shop = shopRepository.findById(id).get();
		List<Reservation> notStyle = reservationRepository.findAllByShopAndServiceContentNull(shop);
		Date date = new Date();
		Date startDate = new Date(date.getYear(), date.getMonth(), date.getDate(), 00, 00, 00);
		Date endDate = new Date(date.getYear(), date.getMonth(), date.getDate(), 23, 59, 59);
		List<Reservation> todayRes = reservationRepository.findAllByShopAndStartDateBetween(shop, startDate, endDate);
		
		Map<String, List<Reservation>> response = new HashMap<>();
		response.put("notStyle", notStyle);
		response.put("todayRes", todayRes);
		
		return response;
	}
}
