package com.webshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.service.ArticleTypeService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("articleType")
@RequiredArgsConstructor
public class ArticleTypeController {

	
	private final ModelMapper mapper;
	private final ArticleTypeService articleTypeService;
}