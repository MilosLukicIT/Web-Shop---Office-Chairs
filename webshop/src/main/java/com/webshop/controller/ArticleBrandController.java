package com.webshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.service.ArticleBrandService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("articleBrand")
@RequiredArgsConstructor
public class ArticleBrandController {

	
	private final ModelMapper mapper;
	private final ArticleBrandService articleBrandService;
}
