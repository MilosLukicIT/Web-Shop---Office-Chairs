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
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderArticle {


	@Id
	@UuidGenerator
	private String orderArticleId;
	private int amountOfArticle;
	
	@ManyToOne
	@JoinColumn(name = "customerOrder")
	private CustomerOrder customerOrder;
	
	
	@ManyToOne
	@JoinColumn(name = "article")
	private Article article;
}
