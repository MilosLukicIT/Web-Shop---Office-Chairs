package com.webshop.model.dto.article_brand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleBrandUpdateDto {

	private String brandId;
	private String nameOfBrand;
	private String countryOfBrand;
}
