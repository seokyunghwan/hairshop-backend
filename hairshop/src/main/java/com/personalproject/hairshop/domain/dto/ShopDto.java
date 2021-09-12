package com.personalproject.hairshop.domain.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.personalproject.hairshop.domain.entity.Image;
import com.personalproject.hairshop.domain.entity.ShopProduct;
import com.personalproject.hairshop.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ShopDto {
	
	private Long id;
	private User owner;
	private String shopName;
	private String shopAddress;
	private String shopTel;
	private String shopInfo;
	private Date openTime;
	private Date closeTime;
	private List<ShopProduct> product;
	private List<Image> image;
/*	public Shop toEntity() {
		return Shop.builder()
				.owner(owner)
				.shopName(shopName)
				.shopAddress(shopAddress)
				.shopTel(shopTel)
				.shopInfo(shopInfo)
				.openTime(openTime)
				.closeTime(closeTime)
				.product(product)
				.build();
	}*/
}
