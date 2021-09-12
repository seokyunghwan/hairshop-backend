package com.personalproject.hairshop.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(sequenceName = "news_seq", name = "NEWS_SEQ_GEN", allocationSize = 1)
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_SEQ_GEN")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "writer")
	private User writer;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdTime;
	private String title;
	private String content;

	@Builder.Default
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
	@OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
	private List<NewsComment> comments = new ArrayList<>();

	public void addComment(NewsComment comment) {
		this.getComments().add(comment);
		comment.setNews(this);
	}

}
