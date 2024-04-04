package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.ArticleBrand;
import com.webshop.repository.ArticleBrandRepository;

@Service
public class ArticleBrandService {

	@Autowired
	private ArticleBrandRepository articleBrandRepo;
	
	public List<ArticleBrand> getAllarticleBrands() {
		return articleBrandRepo.findAll();
	}
	
	public Optional<ArticleBrand> getarticleBrandById(String articleBrandId){
		return articleBrandRepo.findById(articleBrandId);
	}
	
	public ArticleBrand addarticleBrand(ArticleBrand articleBrand) {
		return articleBrandRepo.save(articleBrand);
	}
	
	public void deleteById(String articleBrandId) {
		articleBrandRepo.deleteById(articleBrandId);
	}
	
	public boolean existsById(String articleBrandId) {
		if(getarticleBrandById(articleBrandId).isPresent()) {
			return true;
		} else
			return false;
	}
}