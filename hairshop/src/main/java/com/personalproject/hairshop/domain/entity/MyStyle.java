package com.personalproject.hairshop.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyStyle {
	@Id @GeneratedValue
	@Column(name="style_id")
	private Long id;
	
	@Column(name="style_comment")
	private String comment;
	
	private Date date;
}
