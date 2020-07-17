package com.ruhail.InventoryService.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.ResponseUtil;
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

import com.ruhail.InventoryService.domain.Category;
import com.ruhail.InventoryService.rest.utils.HeaderUtil;
import com.ruhail.InventoryService.rest.utils.PaginationUtil;
import com.ruhail.InventoryService.service.CategoryService;
import com.ruhail.InventoryService.web.rest.errors.BadRequestAlertException;


/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

	private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

	private static final String ENTITY_NAME = "inventoryserviceCategory";

	private final CategoryService categoryService;

	public CategoryResource(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * POST /categories : Create a new category.
	 *
	 * @param Category the Category to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         Category, or with status 400 (Bad Request) if the category has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/categories")

	public ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException {
		log.debug("REST request to save Category : {}", category);
		if (category.getId() != null) {
			throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Category result = categoryService.save(category);
		return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /categories : Updates an existing category.
	 *
	 * @param Category the Category to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Category, or with status 400 (Bad Request) if the Category is not
	 *         valid, or with status 500 (Internal Server Error) if the Category
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/categories")

	public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws URISyntaxException {
		log.debug("REST request to update Category : {}", category);
		if (category.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Category result = categoryService.save(category);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, category.getId().toString())).body(result);
	}

	/**
	 * GET /categories : get all the categories.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of categories in
	 *         body
	 */
	@GetMapping("/categories")

	public ResponseEntity<List<Category>> getAllCategories(Pageable pageable) {
		log.debug("REST request to get a page of Categories");
		Page<Category> page = categoryService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/categories");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /categories/:id : get the "id" category.
	 *
	 * @param id the id of the Category to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the Category,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/categories/{id}")

	public ResponseEntity<Category> getCategory(@PathVariable Long id) {
		log.debug("REST request to get Category : {}", id);
		Optional<Category> category = categoryService.findOne(id);
		return ResponseEntity.of(category);
	}

	/**
	 * DELETE /categories/:id : delete the "id" category.
	 *
	 * @param id the id of the Category to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/categories/{id}")

	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		log.debug("REST request to delete Category : {}", id);
		categoryService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
