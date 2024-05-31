package com.webshop.model;


import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@Column(nullable = false, unique = false, columnDefinition = "NUMERIC")
	private Float priceOfArticle;
	@Column(length = 40, nullable = false, unique = false)
	private String manufacturerOfArticle;
	@Column(nullable = false, unique = false, columnDefinition = "NUMERIC")
	private Float carryingCapacity;
	@Column(length = 30, nullable = false, unique = false)
	private String colorOfArticle;
	
	private int availableAmountOfArticle;
	@Column(length = 30, nullable = false, unique = false)
	private String warrantyLength;
	@Column(nullable = false, unique = false, columnDefinition = "NUMERIC")
	private Float heightOfArticle;
	@Column(nullable = false, unique = false, columnDefinition = "NUMERIC")
	private Float widthOfArticle;
	@Column(nullable = false, unique = false, columnDefinition = "NUMERIC")
	private Float lengthOfArticle;
	@Column(length = 200, nullable = false, unique = false)
	private String descriptionOfArticle;
	@Column(length = 200, nullable = true, unique = false)
	private String imageUrl;
	
	@Column(unique = false, columnDefinition = "NUMERIC")
	private Float discount;
	
	@ManyToOne
	@JoinColumn(name = "brandId")
	private ArticleBrand articleBrand;
	
	
	@ManyToOne
	@JoinColumn(name = "typeId")
	private ArticleType articleType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "article")
	List<CustomerOrderArticle> customerOrderArticle;

	
	

}
