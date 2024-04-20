package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webshop.model.ArticleBrand;
import com.webshop.repository.ArticleBrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleBrandService {

	private final ArticleBrandRepository articleBrandRepo;
	
	public List<ArticleBrand> getAllArticleBrands() {
		
		return articleBrandRepo.findAll();
	}
	
	public Optional<ArticleBrand> getArticleBrandById(String articleBrandId){
		return articleBrandRepo.findById(articleBrandId);
	}
	
	public ArticleBrand saveArticleBrand(ArticleBrand articleBrand) {
		return articleBrandRepo.save(articleBrand);
	}
	
	public void deleteById(String articleBrandId) {
		articleBrandRepo.deleteById(articleBrandId);
	}
	
	public Optional<List<ArticleBrand>> getArticleBrandByName(String nameofBrand) {
		
		Optional<List<ArticleBrand>> brands = Optional.of(articleBrandRepo.findByNameOfBrandContainingIgnoreCase(nameofBrand));
		return brands;
	}
	
	public boolean existsById(String articleBrandId) {
		if(getArticleBrandById(articleBrandId).isPresent()) {
			return true;
		} else
			return false;
	}
}
