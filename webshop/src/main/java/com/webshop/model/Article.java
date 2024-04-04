package com.webshop.model;


import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	
	@Id
	@UuidGenerator
	private String articleId;
	@Column(length = 30, nullable = false, unique = false)
	private String nameOfArticle;
	@Column(nullable = false, unique = false)
	private Float priceOfArticle;
	@Column(length = 40, nullable = false, unique = false)
	private String manufacturerOfArticle;
	private Float carryingCapacity;
	@Column(length = 30, nullable = false, unique = false)
	private String colorOfArticle;
	private int availableAmountOfArticle;
	@Column(length = 30, nullable = false, unique = false)
	private String warrantyLength;
	private Float heightOfArticle;
	private Float widthOfArticle;
	private Float lengthOfArticle;
	@Column(length = 200, nullable = false, unique = false)
	private String descriptionOfArticle;
	private Float discount;
	
	@ManyToOne
	@JoinColumn(name = "articleBrand")
	private ArticleBrand articleBrand;
	
	@ManyToOne
	@JoinColumn(name = "articleType")
	private ArticleType articleType;

	
	

}
