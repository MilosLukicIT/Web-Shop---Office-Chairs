package com.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.Article;
import com.webshop.model.ArticleBrand;

public interface ArticleRepository extends JpaRepository<Article, String> {

	public abstract List<Article> findByNameOfArticle(String nameOfArticle);
	public abstract List<Article> findByArticleBrand(ArticleBrand articleBrand);
}
