package com.personalproject.hairshop.domain.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ShopProduct {
	private String serviceName;
	private Integer price;
	private String requiredTime;
	private String productInfo;
}
