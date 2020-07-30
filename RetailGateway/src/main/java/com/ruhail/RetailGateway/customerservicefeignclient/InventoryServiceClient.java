package com.ruhail.RetailGateway.customerservicefeignclient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ruhail.RetailGateway.domain.Category;
import com.ruhail.RetailGateway.domain.Product;
import com.ruhail.RetailGateway.domain.Stock;
import com.ruhail.RetailGateway.web.rest.errors.BadRequestAlertException;

@FeignClient("inventoryservice")
public interface InventoryServiceClient {

	// -----------Product Resource of Inventory Service-----------
	/**
	 * POST /api/products : Create a new product.
	 *
	 * @param Product the Product to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         Product, or with status 400 (Bad Request) if the product has already
	 *         an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/api/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException;

	/**
	 * PUT /api/products : Updates an existing product.
	 *
	 * @param Product the Product to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Product, or with status 400 (Bad Request) if the Product is not
	 *         valid, or with status 500 (Internal Server Error) if the Product
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/api/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws URISyntaxException;

	/**
	 * GET /api/products : get all the products.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of products in
	 *         body
	 */
	@GetMapping("/api/products")
	public ResponseEntity<List<Product>> getAllProducts();// Pageable pageable

	/**
	 * GET /api/products/:id : get the "id" product.
	 *
	 * @param id the id of the Product to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the Product, or
	 *         with status 404 (Not Found)
	 */
	@GetMapping("/api/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id);

	/**
	 * DELETE /api/products/:id : delete the "id" product.
	 *
	 * @param id the id of the Product to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/api/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id);

	// --------------Category Resource of Inventory Service---------------
	/**
	 * POST /api/categories : Create a new category.
	 *
	 * @param Category the Category to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         Category, or with status 400 (Bad Request) if the category has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/api/categories")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException;;

	/**
	 * PUT /api/categories : Updates an existing category.
	 *
	 * @param Category the Category to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Category, or with status 400 (Bad Request) if the Category is not
	 *         valid, or with status 500 (Internal Server Error) if the Category
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/api/categories")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws URISyntaxException;;

	/**
	 * GET /api/categories : get all the categories.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of categories in
	 *         body
	 */
	@GetMapping("/api/categories")
	public ResponseEntity<List<Category>> getAllCategories();// Pageable pageable

	/**
	 * GET /api/categories/:id : get the "id" category.
	 *
	 * @param id the id of the Category to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the Category,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/api/categories/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id);

	/**
	 * DELETE /api/categories/:id : delete the "id" category.
	 *
	 * @param id the id of the Category to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/api/categories/{id}")

	public ResponseEntity<Void> deleteCategory(@PathVariable Long id);

	// --------------Stock Resource of Inventory Service---------------

	/**
	 * POST /api/stocks : Create a new stock.
	 *
	 * @param stock the Stock to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         Stock, or with status 400 (Bad Request) if the stock has already an
	 *         ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/api/stocks")
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) throws URISyntaxException;

	/**
	 * PUT /api/stocks : Updates an existing stock.
	 *
	 * @param Stock the Stock to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Stock, or with status 400 (Bad Request) if the Stock is not valid, or
	 *         with status 500 (Internal Server Error) if the Stock couldn't be
	 *         updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/api/stocks")
	public ResponseEntity<Stock> updateStock(@RequestBody Stock stock) throws URISyntaxException;

	/**
	 * GET /api/stocks : get all the stocks.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of stocks in
	 *         body
	 */
	@GetMapping("/api/stocks")
	public ResponseEntity<List<Stock>> getAllStocks();// Pageable pageable

	/**
	 * GET /api/stocks/:id : get the "id" stock.
	 *
	 * @param id the id of the Stock to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the Stock, or
	 *         with status 404 (Not Found)
	 */
	@GetMapping("/api/stocks/{id}")
	public ResponseEntity<Stock> getStock(@PathVariable Long id);

	/**
	 * DELETE /api/stocks/:id : delete the "id" stock.
	 *
	 * @param id the id of the Stock to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/api/stocks/{id}")
	public ResponseEntity<Void> deleteStock(@PathVariable Long id);

}
