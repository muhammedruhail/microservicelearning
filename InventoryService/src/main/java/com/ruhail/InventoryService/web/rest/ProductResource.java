package com.ruhail.InventoryService.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruhail.InventoryService.domain.Product;
import com.ruhail.InventoryService.rest.utils.HeaderUtil;
import com.ruhail.InventoryService.rest.utils.PaginationUtil;
import com.ruhail.InventoryService.service.ProductService;
import com.ruhail.InventoryService.web.rest.errors.BadRequestAlertException;



/**
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

	private final Logger log = LoggerFactory.getLogger(ProductResource.class);

	private static final String ENTITY_NAME = "inventoryserviceProduct";

	private final ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * POST /products : Create a new product.
	 *
	 * @param Product the Product to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         Product, or with status 400 (Bad Request) if the product has already
	 *         an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException {
		log.debug("REST request to save Product : {}", product);
		if (product.getId() != null) {
			throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Product result = productService.save(product);
		return ResponseEntity.created(new URI("/api/products/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /products : Updates an existing product.
	 *
	 * @param Product the Product to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Product, or with status 400 (Bad Request) if the Product is not
	 *         valid, or with status 500 (Internal Server Error) if the Product
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/products")

	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws URISyntaxException {
		log.debug("REST request to update Product : {}", product);
		if (product.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Product result = productService.save(product);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, product.getId().toString())).body(result);
	}

	/**
	 * GET /products : get all the products.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of products in
	 *         body
	 */
	@GetMapping("/products")

	public ResponseEntity<List<Product>> getAllProducts(Pageable pageable) {
		log.debug("REST request to get a page of Products");
		Page<Product> page = productService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /products/:id : get the "id" product.
	 *
	 * @param id the id of the Product to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the Product, or
	 *         with status 404 (Not Found)
	 */
	@GetMapping("/products/{id}")

	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		log.debug("REST request to get Product : {}", id);
		Optional<Product> product = productService.findOne(id);
		return ResponseEntity.of(product);
	}

	/**
	 * DELETE /products/:id : delete the "id" product.
	 *
	 * @param id the id of the Product to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/products/{id}")

	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		log.debug("REST request to delete Product : {}", id);
		productService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}



}
