package com.facbrito.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facbrito.dsdeliver.dto.OrderDTO;
import com.facbrito.dsdeliver.entities.Order;
import com.facbrito.dsdeliver.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> orders = orderRepository.findOrdersWithProducts();
		return orders.stream().map(p -> new OrderDTO(p)).collect(Collectors.toList());
	}
}
