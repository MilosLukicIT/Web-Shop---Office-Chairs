package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webshop.model.ArticleType;
import com.webshop.repository.ArticleTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleTypeService {

	
	private final ArticleTypeRepository articleTypeRepo;
	
	
	
	public List<ArticleType> getAllArticleTypes() {
		
		return articleTypeRepo.findAll();
	}
	
	public Optional<ArticleType> getArticleTypeById(String articleTypeId){
		return articleTypeRepo.findById(articleTypeId);
	}
	
	public ArticleType saveArticleType(ArticleType articleType) {
		return articleTypeRepo.save(articleType);
	}
	
	public void deleteById(String articleTypeId) {
		articleTypeRepo.deleteById(articleTypeId);
	}
	
	public boolean existsById(String articleTypeId) {
		if(getArticleTypeById(articleTypeId).isPresent()) {
			return true;
		} else
			return false;
	}
}
