package com.ruhail.InventoryService.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ruhail.InventoryService.domain.Product;
import com.ruhail.InventoryService.repository.ProductRepository;
import com.ruhail.InventoryService.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Product saveProduct(Product product) {
		product.setCreatedDateTime(LocalDateTime.now());
		product.setLastModifiedDateTime(LocalDateTime.now());
		return productRepository.save(product);
	}

}
