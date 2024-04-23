package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.webshop.model.Article;
import com.webshop.model.ArticleBrand;
import com.webshop.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {


	private final ArticleRepository articleRepo;
	
	public List<Article> getAllArticles(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "nameOfArticle"));
		Page<Article> articles = articleRepo.findAll(pageable);
		
		return articles.getContent();
	}
	
	public List<Article> getArticlesByName(String nameOfArticle) {
		List<Article> articles = articleRepo.findByNameOfArticle(nameOfArticle);
		
		return articles;
	}
	
	public List<Article> getArticlesByBrand(ArticleBrand articleBrand) {
		List<Article> articles = articleRepo.findByArticleBrand(articleBrand);
		
		return articles;
	}
	
	public Optional<Article> getArticlerById(String articleId){
		return articleRepo.findById(articleId);
	}
	
	public Article saveArticle(Article article) {
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
