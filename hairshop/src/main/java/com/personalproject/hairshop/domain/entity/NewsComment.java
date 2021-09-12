package com.personalproject.hairshop.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(sequenceName = "news_comment_seq", name = "NEWS_COMMENT_SEQ_GEN", allocationSize = 1)
public class NewsComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_COMMENT_SEQ_GEN")
	private Long id;
	@ManyToOne
	private User writer;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private News news;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date commentTime;
	private String commentCont;
}
