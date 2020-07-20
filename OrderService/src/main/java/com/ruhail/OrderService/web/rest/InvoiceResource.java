package com.ruhail.OrderService.web.rest;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruhail.OrderService.domain.Invoice;
import com.ruhail.OrderService.service.InvoiceService;
import com.ruhail.OrderService.web.rest.errors.BadRequestAlertException;
import com.ruhail.OrderService.web.rest.utils.HeaderUtil;
import com.ruhail.OrderService.web.rest.utils.PaginationUtil;


/**
 * REST controller for managing Invoice.
 */
@RestController
@RequestMapping("/api")
public class InvoiceResource {

	private final Logger log = LoggerFactory.getLogger(InvoiceResource.class);

	private static final String ENTITY_NAME = "orderServiceInvoice";

	private final InvoiceService invoiceService;

	public InvoiceResource(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	/**
	 * POST /invoices : Create a new invoice.
	 *
	 * @param invoice the invoiceDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         invoiceDTO, or with status 400 (Bad Request) if the invoice has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) throws URISyntaxException {
		log.debug("REST request to save Invoice : {}", invoice);
		if (invoice.getId() != null) {
			throw new BadRequestAlertException("A new invoice cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Invoice result = invoiceService.save(invoice);
		return ResponseEntity.created(new URI("/api/invoices/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
						result.getId().toString()))
				.body(result);
	}

	/**
	 * PUT /invoices : Updates an existing invoice.
	 *
	 * @param invoiceDTO the invoice to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         invoiceDTO, or with status 400 (Bad Request) if the invoiceDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the invoiceDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/invoices")

	public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice) throws URISyntaxException {
		log.debug("REST request to update Invoice : {}", invoice);
		if (invoice.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Invoice result = invoiceService.save(invoice);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, invoice.getId().toString())).body(result);
	}

	/**
	 * GET /invoices : get all the invoices.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of invoices in
	 *         body
	 */
	@GetMapping("/invoices")

	public ResponseEntity<List<Invoice>> getAllInvoices(Pageable pageable) {
		log.debug("REST request to get a page of Invoices");
		Page<Invoice> page = invoiceService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/invoices");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /invoices/:id : get the "id" invoice.
	 *
	 * @param id the id of the invoice to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the invoiceDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/invoices/{id}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable Long id) {
		log.debug("REST request to get Invoice : {}", id);
		Optional<Invoice> invoice = invoiceService.findOne(id);
		return ResponseEntity.of(invoice);
	}

	/**
	 * DELETE /invoices/:id : delete the "id" invoice.
	 *
	 * @param id the id of the invoiceDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
		log.debug("REST request to delete Invoice : {}", id);
		invoiceService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
