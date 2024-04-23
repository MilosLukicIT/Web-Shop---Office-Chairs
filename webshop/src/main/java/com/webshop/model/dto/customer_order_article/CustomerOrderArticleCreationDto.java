package com.webshop.model.dto.customer_order_article;

import com.webshop.model.Article;
import com.webshop.model.CustomerOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrderArticleCreationDto {

	
	private int amountOfArticle;
	
	private CustomerOrder customerOrderArticle;
	
	private Article article;
}
