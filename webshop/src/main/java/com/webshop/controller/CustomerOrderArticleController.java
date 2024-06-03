package com.webshop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.CustomerOrder;
import com.webshop.model.CustomerOrderArticle;
import com.webshop.model.dto.customer_order_article.CustomerOrderArticleCreationDto;
import com.webshop.model.dto.customer_order_article.CustomerOrderArticleUpdateDto;
import com.webshop.model.dto.customer_order_article.CustomerOrderArticleViewDto;
import com.webshop.service.CustomerOrderArticleService;
import com.webshop.service.CustomerOrderService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("customerOrderArticle")
@RequiredArgsConstructor
public class CustomerOrderArticleController {

	
	private final ModelMapper mapper;
	private final CustomerOrderArticleService customerOrderArticleService;
	private final CustomerOrderService customerOrderService;
	
	@GetMapping
	public ResponseEntity<?> getAllCustomerOrderArticlePage() {
		

		List<CustomerOrderArticle> customerOrders = customerOrderArticleService.getAllCustomerOrderArticles();
		
		if (!customerOrders.isEmpty()) {
			List<CustomerOrderArticleViewDto> customerOrderDto = customerOrders.stream()
					.map(customerOrder -> mapper.map(customerOrder, CustomerOrderArticleViewDto.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(customerOrderDto);
		} 
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No entities in the db");
		
	}
	
	@GetMapping("/{customerOrderArticleId}")
	public ResponseEntity<?> getCustomerOrderArticleById(@PathVariable String customerOrderArticleId) {
		
		if(customerOrderArticleService.existsById(customerOrderArticleId)) {
			
			CustomerOrderArticleViewDto customerOrderDto = mapper.map(customerOrderArticleService.getCustomerOrderArticleById(customerOrderArticleId), CustomerOrderArticleViewDto.class);
			return ResponseEntity.ok(customerOrderDto);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<?> getCustomerOrderArticleByCustomerOrder(@PathVariable String orderId) {
		Optional<CustomerOrder> customerOrder =  customerOrderService.getCustomerOrderById(orderId);
		if(!customerOrder.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order doesn't exist!");
		}
		List<CustomerOrderArticle> orderArticles = customerOrderArticleService.getCustomerOrderArticleByCustomerOrder(customerOrder.get());
		
		if(!orderArticles.isEmpty()) {
			return ResponseEntity.ok(orderArticles);
		} else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no articles for this order!");
	}
	
	@PostMapping
	public ResponseEntity<?> createCustomerOrderArticle(@RequestBody CustomerOrderArticleCreationDto customerOrder){
		
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		CustomerOrderArticle customerOrderModel = mapper.map(customerOrder, CustomerOrderArticle.class);
		
		CustomerOrderArticleViewDto createdArticleDto = mapper.map(customerOrderArticleService.addCustomerOrderArticle(customerOrderModel), CustomerOrderArticleViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdArticleDto);
	}
	
	@DeleteMapping("/{customerOrderArticleId}")
	public ResponseEntity<?> deleteArticle(@PathVariable String customerOrderArticleId) {
		
		if(customerOrderArticleService.existsById(customerOrderArticleId)) {
			customerOrderArticleService.deleteById(customerOrderArticleId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Entity has been deleted"); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	
	@PutMapping
	public ResponseEntity<?> updateCustomerOrderArticle(@RequestBody CustomerOrderArticleUpdateDto customerOrderArticle){
		
		if (!customerOrderArticleService.existsById(customerOrderArticle.getOrderArticleId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		} else {
			
			this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			CustomerOrderArticle updateCustomerOrderArticle = mapper.map(customerOrderArticleService.getCustomerOrderArticleById(customerOrderArticle.getOrderArticleId()), CustomerOrderArticle.class);
			mapper.map(customerOrderArticle, updateCustomerOrderArticle);
			customerOrderArticleService.addCustomerOrderArticle(updateCustomerOrderArticle);
			
			return ResponseEntity.ok(updateCustomerOrderArticle);
		}
		
	}
}
