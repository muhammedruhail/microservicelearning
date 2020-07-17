package com.ruhail.InventoryService.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruhail.InventoryService.domain.Category;
import com.ruhail.InventoryService.repository.CategoryRepository;
import com.ruhail.InventoryService.service.CategoryService;

/**
 * Service Implementation for managing Category.
 */
@Service

public class CategoryServiceImpl implements CategoryService {

	private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Save a category.
	 *
	 * @param Category the entity to save
	 * @return the persisted entity
	 */
	@Override
	public Category save(Category category) {
		log.debug("Request to save Category : {}", category);

		Category
		result = categoryRepository.save(category);

		return result;
	}

	/**
	 * Get all the categories.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Category> findAll(Pageable pageable) {
		log.debug("Request to get all Categories");
		return categoryRepository.findAll(pageable);
	}

	/**
	 * Get one category by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Category> findOne(Long id) {
		log.debug("Request to get Category : {}", id);
		return categoryRepository.findById(id);
	}

	/**
	 * Delete the category by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Category : {}", id);
		categoryRepository.deleteById(id);
	}

}
