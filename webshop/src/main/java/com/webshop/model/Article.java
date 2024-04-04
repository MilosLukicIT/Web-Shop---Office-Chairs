package com.webshop.model;


import org.hibernate.annotations.UuidGenerator;

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
	private String nameOfArticle;
	private Float priceOfArticle;
	private String manufacturerOfArticle;
	private Float carryingCapacity;
	private String colorOfArticle;
	private int availableAmountOfArticle;
	private String warrantyLength;
	private Float heightOfArticle;
	private Float widthOfArticle;
	private Float lengthOfArticle;
	private String descriptionOfArticle;
	private Float discount;
	
	@ManyToOne
	@JoinColumn(name = "articleBrand")
	private ArticleBrand articleBrand;
	
	@ManyToOne
	@JoinColumn(name = "articleType")
	private ArticleType articleType;

	
	

}
