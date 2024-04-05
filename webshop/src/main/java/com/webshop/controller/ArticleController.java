package com.webshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.Article;
import com.webshop.model.dto.article.ArticleCreationDto;
import com.webshop.model.dto.article.ArticleUpdateDto;
import com.webshop.model.dto.article.ArticleViewDto;
import com.webshop.service.ArticleService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {

	
	private final ArticleService articleService;
	private final ModelMapper mapper;
	
	
	
	@GetMapping
	public ResponseEntity<?> getAllArticle() {
		

		List<Article> articles = articleService.getAllArticles();
		
		if (!articles.isEmpty()) {
			List<ArticleViewDto> articleDto = articles.stream()
					.map(article -> mapper.map(article, ArticleViewDto.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(articleDto);
		} 
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No entities in the db");
		
	}
	
	@GetMapping("/{articleId}")
	public ResponseEntity<?> getArticleById(@PathVariable String articleId) {
		
		if(articleService.existsById(articleId)) {
			
			ArticleViewDto articleDto = mapper.map(articleService.getArticlerById(articleId), ArticleViewDto.class);
			return ResponseEntity.ok(articleDto);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createArticle(@RequestBody ArticleCreationDto article){
		
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Article articleModel = mapper.map(article, Article.class);
		System.out.println(articleModel.getArticleBrand());
		System.out.println(articleModel.getArticleType());
		
		ArticleViewDto createdArticleDto = mapper.map(articleService.saveArticle(articleModel), ArticleViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdArticleDto);
	}
	
	@DeleteMapping("/{articleId}")
	public ResponseEntity<?> deleteArticle(@PathVariable String articleId) {
		
		if(articleService.existsById(articleId)) {
			articleService.deleteById(articleId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Entity has been deleted"); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	
	@PutMapping
	public ResponseEntity<?> updateArticle(@RequestBody ArticleUpdateDto article){
		
		if (!articleService.existsById(article.getArticleId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		} else {
			
			this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Article updateArticle = mapper.map(articleService.getArticlerById(article.getArticleId()), Article.class);
			mapper.map(article, updateArticle);
			articleService.saveArticle(updateArticle);
			
			return ResponseEntity.ok(updateArticle);
		}
		
	}
}
