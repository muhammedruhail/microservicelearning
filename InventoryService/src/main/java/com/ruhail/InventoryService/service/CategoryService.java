package com.ruhail.InventoryService.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ruhail.InventoryService.domain.Category;

/**
 * Service Interface for managing Category.
 */
public interface CategoryService {

	/**
	 * Save a category.
	 *
	 * @param Category the entity to save
	 * @return the persisted entity
	 */
	Category save(Category category);

	/**
	 * Get all the categories.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<Category> findAll(Pageable pageable);

	/**
	 * Get the "id" category.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	Optional<Category> findOne(Long id);

	/**
	 * Delete the "id" category.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);

}