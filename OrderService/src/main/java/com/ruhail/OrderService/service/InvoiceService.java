package com.ruhail.OrderService.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ruhail.OrderService.domain.Invoice;

/**
 * Service Interface for managing Invoice.
 */
public interface InvoiceService {

	/**
	 * Save a invoice.
	 *
	 * @param invoiceDTO the entity to save
	 * @return the persisted entity
	 */
	Invoice save(Invoice invoiceDTO);

	/**
	 * Get all the invoices.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<Invoice> findAll(Pageable pageable);


	/**
	 * Get the "id" invoice.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	Optional<Invoice> findOne(Long id);

	/**
	 * Delete the "id" invoice.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);
}
