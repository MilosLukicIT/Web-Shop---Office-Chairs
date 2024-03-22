package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.ArticleType;

public interface ArticleTypeRepository extends JpaRepository<ArticleType, String> {

}
