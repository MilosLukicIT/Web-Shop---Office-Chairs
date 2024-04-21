package com.webshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.webshop.model.dto.customer_order.CustomerOrderCreationDto;
import com.webshop.model.dto.customer_order.CustomerOrderUpdateDto;
import com.webshop.model.dto.customer_order.CustomerOrderViewDto;
import com.webshop.service.CustomerOrderService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("customerOrder")
@RequiredArgsConstructor
public class CustomerOrderController {

	private final ModelMapper mapper;
	private final CustomerOrderService customerOrderService;
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'WORKER')")
	@GetMapping
	public ResponseEntity<?> getAllCustomerOrdersPage() {
		
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		List<CustomerOrder> customerOrders = customerOrderService.getAllCustomerOrders();
		
		if (!customerOrders.isEmpty()) {
			List<CustomerOrderViewDto> customerOrderDto = customerOrders.stream()
					.map(customerOrder -> mapper.map(customerOrder, CustomerOrderViewDto.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(customerOrderDto);
		} 
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No entities in the db");
		
	}
	
	@GetMapping("/{customerOrderId}")
	public ResponseEntity<?> getCustomerOrderById(@PathVariable String customerOrderId) {
		
		if(customerOrderService.existsById(customerOrderId)) {
			
			CustomerOrderViewDto customerOrderDto = mapper.map(customerOrderService.getCustomerOrderById(customerOrderId), CustomerOrderViewDto.class);
			return ResponseEntity.ok(customerOrderDto);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createCustomerOrder(@RequestBody CustomerOrderCreationDto customerOrder){
		
		this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		CustomerOrder customerOrderModel = mapper.map(customerOrder, CustomerOrder.class);
		
		CustomerOrderViewDto createdArticleDto = mapper.map(customerOrderService.addCustomerOrder(customerOrderModel), CustomerOrderViewDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdArticleDto);
	}
	
	@DeleteMapping("/{customerOrderId}")
	public ResponseEntity<?> deleteArticle(@PathVariable String customerOrderId) {
		
		if(customerOrderService.existsById(customerOrderId)) {
			customerOrderService.deleteById(customerOrderId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Entity has been deleted"); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		}
	}
	
	
	@PutMapping
	public ResponseEntity<?> updateCustomerOrder(@RequestBody CustomerOrderUpdateDto customerOrder){
		
		if (!customerOrderService.existsById(customerOrder.getOrderId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity doesn't exist");
		} else {
			
			this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			CustomerOrder updateCustomerOrder = mapper.map(customerOrderService.getCustomerOrderById(customerOrder.getOrderId()), CustomerOrder.class);
			mapper.map(customerOrder, updateCustomerOrder);
			customerOrderService.addCustomerOrder(updateCustomerOrder);
			
			return ResponseEntity.ok(updateCustomerOrder);
		}
		
	}
}
