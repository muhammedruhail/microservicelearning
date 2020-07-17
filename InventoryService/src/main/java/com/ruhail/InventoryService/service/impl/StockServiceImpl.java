package com.ruhail.InventoryService.service.impl;



import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruhail.InventoryService.domain.Stock;
import com.ruhail.InventoryService.repository.StockRepository;
import com.ruhail.InventoryService.service.StockService;


/**
 * Service Implementation for managing Stock.
 */
@Service
@Transactional
public class StockServiceImpl implements StockService {

	private final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

	private final StockRepository stockRepository;


	public StockServiceImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	/**
	 * Save a stock.
	 *
	 * @param Stock the entity to save
	 * @return the persisted entity
	 */
	@Override
	public Stock save(Stock stock) {
		log.debug("Request to save Stock : {}", stock);

		Stock result = stockRepository.save(stock);

		return result;
	}

	/**
	 * Get all the stocks.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Stock> findAll(Pageable pageable) {
		log.debug("Request to get all Stocks");
		return stockRepository.findAll(pageable);
	}

	/**
	 * Get one stock by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Stock> findOne(Long id) {
		log.debug("Request to get Stock : {}", id);
		return stockRepository.findById(id);
	}

	/**
	 * Delete the stock by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Stock : {}", id);
		stockRepository.deleteById(id);

	}

}
