package com.personalproject.hairshop.domain.dto;

import java.util.Date;

import com.personalproject.hairshop.domain.entity.Shop;
import com.personalproject.hairshop.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ReservationDto {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ReservationSet{
		private Long user;
		private Long shop;
		private Date resDate;
		private Date startDate;
		private Date endDate;
		private String serviceName;
		private Integer price;
		private String serviceContent;
		private String review;
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ReservationGet{
		private Long id;
		private User user;
		private Shop shop;
		private Date resDate;
		private Date startDate;
		private Date endDate;
		private String serviceName;
		private Integer price;
		private String serviceContent;
		private String review;
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ReviewSet{
		private Long id;
		private String review;
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ReviewGet{
		private User user;
		private Shop shop;
		private Date startDate;
		private String serviceName;
		private String review;
	}
}
