package com.webshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.service.CustomerOrderArticleService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("customerOrderArticle")
@RequiredArgsConstructor
public class CustomerOrderArticleController {

	
	private final ModelMapper mapper;
	private final CustomerOrderArticleService customerOrderArticleService;
}
