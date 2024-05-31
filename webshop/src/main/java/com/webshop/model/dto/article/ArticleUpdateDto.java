package com.webshop.model.dto.article;

import com.webshop.model.ArticleBrand;
import com.webshop.model.ArticleType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleUpdateDto {
	
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
	private String imageUrl;
	
	private ArticleBrand articleBrand;
	private ArticleType articleType;

}
