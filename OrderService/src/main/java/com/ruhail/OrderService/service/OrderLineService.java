package com.ruhail.OrderService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ruhail.OrderService.domain.OrderLine;

import java.util.Optional;

/**
 * Service Interface for managing OrderLine.
 */
public interface OrderLineService {

	/**
	 * Save a orderLine.
	 *
	 * @param orderLine the entity to save
	 * @return the persisted entity
	 */
	OrderLine save(OrderLine orderLine);

	/**
	 * Get all the orderLines.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<OrderLine> findAll(Pageable pageable);

	/**
	 * Get the "id" orderLine.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	Optional<OrderLine> findOne(Long id);

	/**
	 * Delete the "id" orderLine.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);
}
