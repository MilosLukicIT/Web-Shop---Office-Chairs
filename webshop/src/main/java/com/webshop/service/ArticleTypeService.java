package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.ArticleType;
import com.webshop.repository.ArticleTypeRepository;

@Service
public class ArticleTypeService {

	
	@Autowired
	private ArticleTypeRepository articleTypeRepo;
	
	
	
	public List<ArticleType> getAllarticleTypes() {
		return articleTypeRepo.findAll();
	}
	
	public Optional<ArticleType> getarticleTypeById(String articleTypeId){
		return articleTypeRepo.findById(articleTypeId);
	}
	
	public ArticleType addarticleType(ArticleType articleType) {
		return articleTypeRepo.save(articleType);
	}
	
	public void deleteById(String articleTypeId) {
		articleTypeRepo.deleteById(articleTypeId);
	}
	
	public boolean existsById(String articleTypeId) {
		if(getarticleTypeById(articleTypeId).isPresent()) {
			return true;
		} else
			return false;
	}
}
