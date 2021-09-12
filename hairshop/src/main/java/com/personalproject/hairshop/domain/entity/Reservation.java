package com.personalproject.hairshop.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(sequenceName = "res_seq", name = "RES_SEQ_GEN", allocationSize = 1)
public class Reservation {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RES_SEQ_GEN")
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Shop shop;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date resDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	private String serviceName;
	
	private Integer price;
	
	private String serviceContent;
	
	private String review;
}
