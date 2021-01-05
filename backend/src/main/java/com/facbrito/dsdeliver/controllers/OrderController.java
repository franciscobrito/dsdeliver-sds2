package com.facbrito.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facbrito.dsdeliver.dto.OrderDTO;
import com.facbrito.dsdeliver.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<OrderDTO> orderDTOS = orderService.findAll();
		return ResponseEntity.ok().body(orderDTOS);
	}

}
