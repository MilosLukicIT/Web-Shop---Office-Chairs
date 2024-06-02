package com.webshop.controller;

import java.util.List;
import java.util.Optional;
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

import com.webshop.model.Article;
import com.webshop.model.ArticleBrand;
import com.webshop.model.dto.article.ArticleCreationDto;
import com.webshop.model.dto.article.ArticleUpdateDto;
import com.webshop.model.dto.article.ArticleViewDto;
import com.webshop.service.ArticleBrandService;
import com.webshop.service.ArticleService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {

	
	private final ArticleService articleService;
	private final ArticleBrandService articleBrandService;
	private final ModelMapper mapper;

	
	@GetMapping
	public ResponseEntity<?> getAllArticle(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5")int size) {
		

		List<Article> articles = articleService.getAllArticles(page, size);
		
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
	
	@GetMapping("/nameOfArticle")
	public ResponseEntity<?> getArticleBrandByName(@RequestParam(name = "name") String articleName) {
		List<Article> brands = articleService.getArticlesByName(articleName);
		
		if(!brands.isEmpty()) {
			return ResponseEntity.ok(brands);
		} else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no articles wtih that name!");
	}
	
	@GetMapping("/articleBrand/{brandId}")
	public ResponseEntity<?> getArticleBrandByBrand(@PathVariable String brandId) {
		Optional<ArticleBrand> articleBrand =  articleBrandService.getArticleBrandById(brandId);
		if(!articleBrand.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand doesn't exist!");
		}
		List<Article> articles = articleService.getArticlesByBrand(articleBrand.get());
		
		if(!articles.isEmpty()) {
			return ResponseEntity.ok(articles);
		} else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no articles of that brand!");
	}
	
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@PostMapping
	public ResponseEntity<?> createArticle(@RequestBody ArticleCreationDto article){
		
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Article articleModel = mapper.map(article, Article.class);
		
		ArticleViewDto createdArticleDto = mapper.map(articleService.saveArticle(articleModel), ArticleViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdArticleDto);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@DeleteMapping("/{articleId}")
	public ResponseEntity<?> deleteArticle(@PathVariable String articleId) {
		
		if(articleService.existsById(articleId)) {
			articleService.deleteById(articleId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Entity has been deleted"); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
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
