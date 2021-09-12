package com.personalproject.hairshop.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users") 
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(sequenceName = "user_seq", name = "USER_SEQ_GEN", allocationSize = 1)
public class User{
	@Id
	@Column(name = "id") //자동 증가되는 PK키
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
	private Long id;

	@Column(name = "user_id", length = 50, unique = true)
	private String userId;

	@JsonIgnore
	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "email", length = 100)
	String email;
	
	@Column(name = "phone_num", length = 100)
	String phoneNum;

	
/*	@JsonIgnore
	@Column(name = "activated") //활성화 여부
	private boolean activated;*/

	@ManyToMany	//user객체와 권한객체의 다대다 관계를 1:다 다:1 관계의 조인테이블로 정의했다
	@JoinTable(name = "user_authority", 
	joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, 
	inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "authority_name") })
	private Set<Authority> authorities;

}
