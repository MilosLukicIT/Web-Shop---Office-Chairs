package com.webshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.service.CustomerOrderService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("customerOrder")
@RequiredArgsConstructor
public class CustomerOrderController {

	private final ModelMapper mapper;
	private final CustomerOrderService customerOrderService;
}
