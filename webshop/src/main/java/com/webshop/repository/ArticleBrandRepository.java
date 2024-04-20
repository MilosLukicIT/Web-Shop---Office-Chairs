package com.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.ArticleBrand;

public interface ArticleBrandRepository extends JpaRepository<ArticleBrand, String> {
	
	public abstract List<ArticleBrand> findByNameOfBrandContainingIgnoreCase(String nameOfBrand);

}
