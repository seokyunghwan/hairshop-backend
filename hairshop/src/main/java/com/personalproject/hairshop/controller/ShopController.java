package com.personalproject.hairshop.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.personalproject.hairshop.domain.dto.ShopDto;
import com.personalproject.hairshop.domain.dto.ShopSearchPageDto;
import com.personalproject.hairshop.domain.entity.Shop;
import com.personalproject.hairshop.domain.entity.User;
import com.personalproject.hairshop.paging.CustomPagedResourceAssembler;
import com.personalproject.hairshop.paging.PagedModelUtil;
import com.personalproject.hairshop.service.ShopService;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
	@Autowired
	private ShopService shopService;

	@Autowired
	CustomPagedResourceAssembler<Shop> assembler;

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Shop>>> searchShop(
			@PageableDefault(size = 5, sort = "shopName", direction = Sort.Direction.ASC) Pageable pageable,
			ShopSearchPageDto shopSearchPageDto) {
		Page<Shop> shops = shopService.getAllShopByShopName(shopSearchPageDto.getShopName(), pageable);
		if (shops.getContent().isEmpty()) {
			return null;
		}
		PagedModel<EntityModel<Shop>> entityModels = PagedModelUtil.getEntityModels(assembler, shops,
				linkTo(methodOn(this.getClass()).searchShop(null, null)), Shop::getId);
		return ResponseEntity.ok(entityModels);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ShopDto> getShop(@PathVariable Long id) {
		return ResponseEntity.ok(shopService.getShopById(id));
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('MANAGER')")
	public ResponseEntity<ShopDto> registerShop(@ModelAttribute ShopDto shopDto, @RequestParam("files") List<MultipartFile> files,
			@RequestParam("serviceInfoList") List<String> serviceInfoList) throws IOException {
		return ResponseEntity.ok(shopService.setShop(shopDto, files, serviceInfoList));
	}


}
