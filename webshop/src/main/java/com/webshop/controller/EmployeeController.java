package com.webshop.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final ModelMapper mapper;
	private final EmployeeService employeeService;
}
