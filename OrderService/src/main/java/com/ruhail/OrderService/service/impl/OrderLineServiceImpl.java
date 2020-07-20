package com.ruhail.OrderService.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruhail.OrderService.domain.OrderLine;
import com.ruhail.OrderService.repository.OrderLineRepository;
import com.ruhail.OrderService.service.OrderLineService;

/**
 * Service Implementation for managing OrderLine.
 */
@Service
@Transactional
public class OrderLineServiceImpl implements OrderLineService {

	private final Logger log = LoggerFactory.getLogger(OrderLineServiceImpl.class);

	private final OrderLineRepository orderLineRepository;

	public OrderLineServiceImpl(OrderLineRepository orderLineRepository) {
		this.orderLineRepository = orderLineRepository;
	}

	/**
	 * Save a orderLine.
	 *
	 * @param orderLineDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public OrderLine save(OrderLine orderLine) {
		log.debug("Request to save OrderLine : {}", orderLine);

		return orderLineRepository.save(orderLine);
	}

	/**
	 * Get all the orderLines.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<OrderLine> findAll(Pageable pageable) {
		log.debug("Request to get all OrderLines");
		return orderLineRepository.findAll(pageable);
	}

	/**
	 * Get one orderLine by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<OrderLine> findOne(Long id) {
		log.debug("Request to get OrderLine : {}", id);
		return orderLineRepository.findById(id);
	}

	/**
	 * Delete the orderLine by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete OrderLine : {}", id);
		orderLineRepository.deleteById(id);
	}
}
