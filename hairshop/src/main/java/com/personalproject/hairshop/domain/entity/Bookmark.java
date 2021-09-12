package com.personalproject.hairshop.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

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
@SequenceGenerator(sequenceName = "bookmark_seq", name = "BOOKMARK_SEQ_GEN", allocationSize = 1)
public class Bookmark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKMARK_SEQ_GEN")
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Shop shop;

}
