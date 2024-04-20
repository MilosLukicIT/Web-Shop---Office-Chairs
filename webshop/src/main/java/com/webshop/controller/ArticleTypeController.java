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

import com.webshop.model.ArticleType;
import com.webshop.model.dto.article_type.ArticleTypeCreateDto;
import com.webshop.model.dto.article_type.ArticleTypeUpdateDto;
import com.webshop.model.dto.article_type.ArticleTypeViewDto;
import com.webshop.service.ArticleTypeService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("articleType")
@RequiredArgsConstructor
public class ArticleTypeController {

	
	private final ModelMapper mapper;
	private final ArticleTypeService articleTypeService;
	
	
	@GetMapping
	public ResponseEntity<?> getAllArticleTypesPage() {
		

		List<ArticleType> articleTypes = articleTypeService.getAllArticleTypes();
		
		if (!articleTypes.isEmpty()) {
			List<ArticleTypeViewDto> articleTypeDto = articleTypes.stream()
					.map(articleType -> mapper.map(articleType, ArticleTypeViewDto.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(articleTypeDto);
		} 
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No entities in the db");
		
	}
	
	@GetMapping("/{articleTypeId}")
	public ResponseEntity<?> getArticleTypeById(@PathVariable String articleTypeId) {
		
		if(articleTypeService.existsById(articleTypeId)) {
			
			ArticleTypeViewDto articleTypeDto = mapper.map(articleTypeService.getArticleTypeById(articleTypeId), ArticleTypeViewDto.class);
			return ResponseEntity.ok(articleTypeDto);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createArticleType(@RequestBody ArticleTypeCreateDto articleType){
		
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ArticleType articleTypeModel = mapper.map(articleType, ArticleType.class);
		
		ArticleTypeViewDto createdArticleDto = mapper.map(articleTypeService.saveArticleType(articleTypeModel), ArticleTypeViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdArticleDto);
	}
	
	@DeleteMapping("/{articleTypeId}")
	public ResponseEntity<?> deleteArticle(@PathVariable String articleTypeId) {
		
		if(articleTypeService.existsById(articleTypeId)) {
			articleTypeService.deleteById(articleTypeId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Entity has been deleted"); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	
	@PutMapping
	public ResponseEntity<?> updateArticleType(@RequestBody ArticleTypeUpdateDto articleType){
		
		if (!articleTypeService.existsById(articleType.getTypeId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		} else {
			
			this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			ArticleType updateArticleType = mapper.map(articleTypeService.getArticleTypeById(articleType.getTypeId()), ArticleType.class);
			mapper.map(articleType, updateArticleType);
			articleTypeService.saveArticleType(updateArticleType);
			
			return ResponseEntity.ok(updateArticleType);
		}
		
	}
}
