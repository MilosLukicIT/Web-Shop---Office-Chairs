package com.webshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.ArticleBrand;
import com.webshop.model.dto.article_brand.ArticleBrandCreateDto;
import com.webshop.model.dto.article_brand.ArticleBrandUpdateDto;
import com.webshop.model.dto.article_brand.ArticleBrandViewDto;
import com.webshop.service.ArticleBrandService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("articleBrand")
@RequiredArgsConstructor
public class ArticleBrandController {

	
	private final ModelMapper mapper;
	private final ArticleBrandService articleBrandService;
	
	
	@GetMapping
	public ResponseEntity<?> getAllArticleBrands() {
		

		List<ArticleBrand> articleBrands = articleBrandService.getAllArticleBrands();
		
		if (!articleBrands.isEmpty()) {
			List<ArticleBrandViewDto> articleBrandDto = articleBrands.stream()
					.map(articleBrand -> mapper.map(articleBrand, ArticleBrandViewDto.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(articleBrandDto);
		} 
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No entities in the db");
		
	}
	
	@GetMapping("/{articleBrandId}")
	public ResponseEntity<?> getArticleBrandById(@PathVariable String articleBrandId) {
		
		if(articleBrandService.existsById(articleBrandId)) {
			
			ArticleBrandViewDto articleBrandDto = mapper.map(articleBrandService.getArticleBrandById(articleBrandId), ArticleBrandViewDto.class);
			return ResponseEntity.ok(articleBrandDto);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	@GetMapping("/nameOfBrand")
	public ResponseEntity<?> getArticleBrandByName(@RequestParam String brandName) {
		List<ArticleBrand> brands = articleBrandService.getArticleBrandByName(brandName).get();
		
		if(!brands.isEmpty()) {
			return ResponseEntity.ok(brands);
		} else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no brands with that name");
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@PostMapping
	public ResponseEntity<?> createArticleBrand(@RequestBody ArticleBrandCreateDto articleBrand){
		
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ArticleBrand articleBrandModel = mapper.map(articleBrand, ArticleBrand.class);
		
		ArticleBrandViewDto createdArticleDto = mapper.map(articleBrandService.saveArticleBrand(articleBrandModel), ArticleBrandViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdArticleDto);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@DeleteMapping("/{articleBrandId}")
	public ResponseEntity<?> deleteArticle(@PathVariable String articleBrandId) {
		
		if(articleBrandService.existsById(articleBrandId)) {
			articleBrandService.deleteById(articleBrandId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Entity has been deleted"); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@PutMapping
	public ResponseEntity<?> updateArticleBrand(@RequestBody ArticleBrandUpdateDto articleBrand){
		
		if (!articleBrandService.existsById(articleBrand.getBrandId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		} else {
			
			this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			ArticleBrand updateArticleBrand = mapper.map(articleBrandService.getArticleBrandById(articleBrand.getBrandId()), ArticleBrand.class);
			mapper.map(articleBrand, updateArticleBrand);
			articleBrandService.saveArticleBrand(updateArticleBrand);
			
			return ResponseEntity.ok(updateArticleBrand);
		}
		
	}
}
