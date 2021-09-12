package com.personalproject.hairshop.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalproject.hairshop.domain.dto.ReservationDto;
import com.personalproject.hairshop.domain.dto.ReservationDto.ReservationGet;
import com.personalproject.hairshop.domain.dto.ReservationDto.ReservationSet;
import com.personalproject.hairshop.domain.dto.ReservationDto.ReviewGet;
import com.personalproject.hairshop.domain.dto.ReservationDto.ReviewSet;
import com.personalproject.hairshop.domain.entity.Reservation;
import com.personalproject.hairshop.paging.CustomPagedResourceAssembler;
import com.personalproject.hairshop.repository.ShopRepository;
import com.personalproject.hairshop.repository.UserRepository;
import com.personalproject.hairshop.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	@Autowired
	ReservationService reservationService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ShopRepository shopRepository;
	@Autowired
	CustomPagedResourceAssembler<Reservation> assembler;

	@GetMapping
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public PagedModel<EntityModel<ReservationGet>> getMyReservation(
			Long id, @PageableDefault(size = 5, sort = "endDate", direction = Sort.Direction.DESC) Pageable pageable,
			PagedResourcesAssembler<ReservationGet> assembler) {
		
		return assembler.toModel(reservationService.getMyReservation(id, pageable));
	}
	@PostMapping
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public ResponseEntity<ReservationGet> setReservation(@RequestBody ReservationSet reservationDto) {
		return ResponseEntity.ok(reservationService.setReservation(reservationDto));
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('MANAGER')")
	public void addStyle(@RequestBody ReservationGet reservationDto) {
		System.out.println(reservationDto.getId() + " " + reservationDto.getServiceContent());
		reservationService.addStyle(reservationDto);
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public void deleteReservation(Long id) {
		reservationService.deleteReservation(id);
	}
	
	@GetMapping("/getAllReservation")
	public List<ReservationDto.ReservationGet> getReservationList(@DateTimeFormat(iso = ISO.DATE_TIME) Date date,
			Long shopId) {
		List<ReservationDto.ReservationGet> reservations = reservationService.getReservationsByShop(date, shopId);
		return reservations;
	}
	
	@GetMapping("/review")
	public Page<ReviewGet> getReview(Long id, @PageableDefault(size = 5, sort = "endDate", direction = Sort.Direction.DESC) Pageable pageable){
		return reservationService.getReview(pageable, id);
	}
	
	@PostMapping("/review")
	@PreAuthorize("hasAnyRole('USER', 'MANAGER')")
	public ResponseEntity<ReservationGet> setReview(@RequestBody ReviewSet reviewDto){
		return ResponseEntity.ok(reservationService.setReview(reviewDto));
	}
	
	@GetMapping("/managerMain/{id}")
	@PreAuthorize("hasAnyRole('MANAGER')")
	public ResponseEntity<Map<String, List<Reservation>>> getMainForManager(@PathVariable Long id) {
		
		return ResponseEntity.ok(reservationService.getMainForManager(id));
	}
}
