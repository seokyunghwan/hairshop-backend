package com.personalproject.hairshop.domain.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shops")
@SequenceGenerator(sequenceName = "shop_seq", name = "SHOP_SEQ_GEN", allocationSize = 1)
@Embeddable
public class Shop {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOP_SEQ_GEN")
	private Long id;
	@OneToOne
	@JoinColumn(name = "owner")
	private User owner;
	private String shopName;
	private String shopAddress;
	private String shopTel;
	private String shopInfo;
	private Date openTime;
	private Date closeTime;
	
	@ElementCollection
//	@Column(name="prop_value")
	@Embedded
	private List<ShopProduct> product;
	
	@ElementCollection
	@Embedded
	private List<Image> image;
	
}
