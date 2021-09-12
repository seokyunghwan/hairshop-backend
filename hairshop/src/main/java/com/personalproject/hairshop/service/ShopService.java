package com.personalproject.hairshop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalproject.hairshop.domain.dto.ShopDto;
import com.personalproject.hairshop.domain.entity.Image;
import com.personalproject.hairshop.domain.entity.Shop;
import com.personalproject.hairshop.domain.entity.ShopProduct;
import com.personalproject.hairshop.repository.ShopRepository;
import com.personalproject.hairshop.repository.UserRepository;

@Service
@Transactional
public class ShopService {
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private Environment env;

	//매장 검색
	public Page<Shop> getAllShopByShopName(String shopName, Pageable pageable) {
		return shopRepository.findAllByShopNameContainsIgnoreCase(shopName, pageable);
	}

	//매장 정보 받아오기
	public ShopDto getShopById(Long id) {
		return modelMapper.map(shopRepository.findById(id).get(), ShopDto.class);
	}


	// 매장 등록
	public ShopDto setShop(ShopDto shopDto, List<MultipartFile> files, List<String> serviceInfoList)
			throws IOException {
		ObjectMapper om = new ObjectMapper();
		List<ShopProduct> shopProductList = new ArrayList<>();

		for (String serviceInfo : serviceInfoList) {
			ShopProduct sp = om.readValue(serviceInfo, ShopProduct.class);
			if (!sp.getServiceName().isEmpty()) {
				shopProductList.add(sp);
			}
		}
		Collections.sort(shopProductList, new Comparator<ShopProduct>() {
			@Override
			public int compare(ShopProduct o1, ShopProduct o2) {
				
				return o1.getPrice().compareTo(o2.getPrice());
			}
		});
		shopDto.setProduct(shopProductList);
		Shop shop = shopRepository.save(modelMapper.map(shopDto, Shop.class));
		List<Image> imageList = new ArrayList<>();
		String routePath = env.getRequiredProperty("user.file.upload");
		int count = 0;
		for (MultipartFile file : files) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String reFileName = shop.getId() + "_" + count++ +"." + extension;
			String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/shopImg/")
					.path(reFileName)
					.toUriString();
			Path targetLocation = Paths.get(routePath).toAbsolutePath().normalize().resolve(reFileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			imageList.add(Image.builder()
					.name(reFileName)
					.origName(fileName)
					.uri(fileUri)
					.build());
			file.getInputStream().close();
		}
		shopDto.setId(shop.getId());
		shopDto.setImage(imageList);
		return modelMapper.map(shopRepository.save(modelMapper.map(shopDto, Shop.class)), ShopDto.class);
	}
	

}
