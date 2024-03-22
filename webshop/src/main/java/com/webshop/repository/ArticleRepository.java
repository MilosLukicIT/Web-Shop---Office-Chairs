package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {

}
