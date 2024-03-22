package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.CustomerOrderArticle;

public interface CustomerOrderArticleRepository extends JpaRepository<CustomerOrderArticle, String> {

}
