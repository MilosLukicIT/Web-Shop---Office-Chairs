package com.webshop.model.dto.customer_order_article;

import com.webshop.model.Article;
import com.webshop.model.CustomerOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrderArticleViewDto {

	private String orderArticleId;
	private int amountOfArticle;
	
	private CustomerOrder orderId;
	
	private Article articleId;
}
