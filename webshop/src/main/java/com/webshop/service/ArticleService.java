package com.webshop.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.webshop.exceptions.EntityNotFoundException;
import com.webshop.model.Article;
import com.webshop.model.ArticleBrand;
import com.webshop.model.dto.article.ArticleCreationDto;
import com.webshop.model.dto.article.ArticleUpdateDto;
import com.webshop.model.dto.article.ArticleViewDto;
import com.webshop.repository.ArticleBrandRepository;
import com.webshop.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {


	private final ArticleRepository articleRepo;
	private final ArticleBrandRepository articleBrandRepository;
	private final ModelMapper mapper;
	
	public List<ArticleViewDto> getAllArticles(int page, int size, int order, String orderByValue) {
		
		Pageable pageable;
		
		
		if(order == 1) {
			pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, orderByValue));
		}
		else
			pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, orderByValue));
		Page<Article> articles = articleRepo.findAll(pageable);
		
		if (articles.getContent().isEmpty()) {
			
			log.error("No articles found");
			
			throw new EntityNotFoundException("No articles found");
		}
		List<ArticleViewDto> articleDto = articles.getContent().stream()
				.map(article -> mapper.map(article, ArticleViewDto.class))
				.collect(Collectors.toList());
		
		log.info("Found and returned articles");
		
		return articleDto;
	}
	
	public List<ArticleViewDto> getArticlesByName(String nameOfArticle) {
		
		List<Article> articles = articleRepo.findByNameOfArticle(nameOfArticle);
		
		if(articles.isEmpty()) {
			log.error("No articles found");
			
			throw new EntityNotFoundException("No articles found");
		}
		List<ArticleViewDto> articleDto = articles.stream()
				.map(article -> mapper.map(article, ArticleViewDto.class))
				.collect(Collectors.toList());
		
		return articleDto;
	}
	
	public List<ArticleViewDto> getArticlesByBrand(String articleBrandId) {
		
		ArticleBrand brand = articleBrandRepository.findById(articleBrandId).orElse(null);
		
		if(Objects.isNull(brand)) {
			log.error("There are no brands with id: " + articleBrandId);
			
			throw new EntityNotFoundException("Brand doesn't exist");
		}
		
		List<Article> articles = articleRepo.findByArticleBrand(brand);
		
		if(articles.isEmpty()) {
			log.error("No articles found");
			throw new EntityNotFoundException("No articles found");
		}
		
		List<ArticleViewDto> articleDto = articles.stream()
				.map(article -> mapper.map(article, ArticleViewDto.class))
				.collect(Collectors.toList());
		
		log.info("Found articles");
		
		return articleDto;
	}
	
	public ArticleViewDto getArticlerById(String articleId){
		
		
		Article article = articleRepo.findById(articleId).orElse(null);
		if(Objects.isNull(article)) {
			
			log.info("Article not found!");
			throw new EntityNotFoundException("Article not found!");
			
		}
		ArticleViewDto articleDto = mapper.map(article, ArticleViewDto.class);
		log.info("Found and returned article");
		
		return articleDto;
	}
	
	public ArticleViewDto saveArticle(ArticleCreationDto articleDto) {
		
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Article articleModel = mapper.map(articleDto, Article.class);
		ArticleViewDto createdArticleDto = mapper.map(articleRepo.save(articleModel), ArticleViewDto.class);
		
		log.info("Added a new article");
		return createdArticleDto;
	}
	
	public ArticleViewDto updateArticle(ArticleUpdateDto articleDto) {
		
		Article article = articleRepo.findById(articleDto.getArticleId()).orElse(null);
		
		
		if (Objects.isNull(article)) {
			log.error("Article not found!");
			throw new EntityNotFoundException("Article doesn't exist!");
		}
			
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		mapper.map(articleDto, article);
		ArticleViewDto returnArticle = mapper.map(articleRepo.save(article), ArticleViewDto.class); 
		
		log.info("Article updated");
		
		return returnArticle;
	}
	
	public void deleteById(String articleId) {
		
		if(!existsById(articleId)) {
			log.error("Article not found!");
			throw new EntityNotFoundException("Article doesnt exist!");
		}
			
		log.info("Deleted an article");
		articleRepo.deleteById(articleId);
	}
	
	
	public boolean existsById(String articleId) {
		if(Objects.isNull(getArticlerById(articleId))) {
			return true;
		} else
			return false;
	}
}
