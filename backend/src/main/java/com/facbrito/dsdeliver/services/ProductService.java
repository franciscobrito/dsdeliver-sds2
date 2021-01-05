package com.facbrito.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facbrito.dsdeliver.dto.ProductDTO;
import com.facbrito.dsdeliver.entities.Product;
import com.facbrito.dsdeliver.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> products = productRepository.findAllByOrderByNameAsc();
		return products.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}
}
