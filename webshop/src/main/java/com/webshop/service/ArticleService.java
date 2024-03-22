package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.Article;
import com.webshop.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepo;
	
	public List<Article> getAllArticles() {
		return articleRepo.findAll();
	}
	
	public Optional<Article> getArticlerById(String articleId){
		return articleRepo.findById(articleId);
	}
	
	public Article addArticle(Article article) {
		return articleRepo.save(article);
	}
	
	public void deleteById(String articleId) {
		articleRepo.deleteById(articleId);
	}
	
	public boolean existsById(String articleId) {
		if(getArticlerById(articleId).isPresent()) {
			return true;
		} else
			return false;
	}
}
