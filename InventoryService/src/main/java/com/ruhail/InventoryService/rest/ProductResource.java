package com.ruhail.InventoryService.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruhail.InventoryService.domain.Product;
import com.ruhail.InventoryService.rest.errors.BadRequestAlertException;
import com.ruhail.InventoryService.rest.utils.HeaderUtil;
import com.ruhail.InventoryService.service.ProductService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/api")
public class ProductResource {

	private ProductService productService;

	private final Logger logger = LoggerFactory.getLogger(ProductResource.class);

	private static final String ENTITY_NAME = "product";

	public ProductResource(ProductService productService) {
		super();
		this.productService = productService;
	}

	/**
	 * POST /products : Create a new product.
	 *
	 * @param productDTO the productDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         productDTO, or with status 400 (Bad Request) if the product has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/create-product")
	@Timed
	public ResponseEntity<Product> createProduct(@Validated @RequestBody Product product)
			throws URISyntaxException, BadRequestAlertException {
		logger.debug("REST request to save Product : {}", product);

		if (product.getId() != null) {
			throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
		}

		Product result = productService.saveProduct(product);
		return ResponseEntity.created(new URI("/api/products/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

}
