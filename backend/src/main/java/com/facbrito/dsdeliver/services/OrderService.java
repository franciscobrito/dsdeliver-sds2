package com.facbrito.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facbrito.dsdeliver.dto.OrderDTO;
import com.facbrito.dsdeliver.dto.ProductDTO;
import com.facbrito.dsdeliver.entities.Order;
import com.facbrito.dsdeliver.entities.OrderStatus;
import com.facbrito.dsdeliver.entities.Product;
import com.facbrito.dsdeliver.repositories.OrderRepository;
import com.facbrito.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> orders = orderRepository.findOrdersWithProducts();
		return orders.stream().map(p -> new OrderDTO(p)).collect(Collectors.toList());
	}

	@Transactional
	public OrderDTO insert(OrderDTO orderDTO) {
		Order order = new Order(null, orderDTO.getAddress(), orderDTO.getLatitude(), orderDTO.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : orderDTO.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}

		order = orderRepository.save(order);
		return new OrderDTO(order);
	}
}
