package com.webshop.controller;

import java.util.List;

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

	
	@GetMapping
	public ResponseEntity<List<ArticleViewDto>> getAllArticle(@RequestParam(name = "page", defaultValue = "0") int page, 
											@RequestParam(name = "size", defaultValue = "5")int size,
											@RequestParam(name = "order", defaultValue = "1") int order,
											@RequestParam(name = "orderByValue", defaultValue = "nameOfArticle") String orderByValue) {
	
	
		return ResponseEntity.ok(articleService.getAllArticles(page, size, order, orderByValue));
	}
	
	@GetMapping("/{articleId}")
	public ResponseEntity<ArticleViewDto> getArticleById(@PathVariable String articleId) {
		
		return ResponseEntity.ok(articleService.getArticlerById(articleId));
	}
	
	@GetMapping("/nameOfArticle")
	public ResponseEntity<List<ArticleViewDto>> getArticleBrandByName(@RequestParam(name = "name") String articleName) {
		
		return ResponseEntity.ok(articleService.getArticlesByName(articleName));
	}
	
	@GetMapping("/articleBrand/{brandId}")
	public ResponseEntity<?> getArticleBrandByBrand(@PathVariable String brandId) {
		return ResponseEntity.ok(articleService.getArticlesByBrand(brandId));
	}
	
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@PostMapping
	public ResponseEntity<?> createArticle(@RequestBody ArticleCreationDto article){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(articleService.saveArticle(article));
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@DeleteMapping("/{articleId}")
	public ResponseEntity<String> deleteArticle(@PathVariable String articleId) {
		
		articleService.deleteById(articleId);
		return ResponseEntity.ok("Article has been deleted!");
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@PutMapping
	public ResponseEntity<ArticleViewDto> updateArticle(@RequestBody ArticleUpdateDto article){
	
		return ResponseEntity.ok(articleService.updateArticle(article));
		
	}
}
