package com.ruhail.CustomerService.web.rest;

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

import com.ruhail.CustomerService.domain.Customer;
import com.ruhail.CustomerService.rest.utils.HeaderUtil;
import com.ruhail.CustomerService.rest.utils.PaginationUtil;
import com.ruhail.CustomerService.service.CustomerService;
import com.ruhail.CustomerService.web.rest.errors.BadRequestAlertException;



/**
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

	private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

	private static final String ENTITY_NAME = "customerserviceCustomer";

	private final CustomerService customerService;

	public CustomerResource(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * POST /customers : Create a new customer.
	 *
	 * @param Customer the Customer to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         Customer, or with status 400 (Bad Request) if the customer has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws URISyntaxException {
		log.debug("REST request to save Customer : {}", customer);
		if (customer.getId() != null) {
			throw new BadRequestAlertException("A new customer cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Customer result = customerService.save(customer);
		return ResponseEntity.created(new URI("/api/customers/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /customers : Updates an existing customer.
	 *
	 * @param Customer the Customer to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Customer, or with status 400 (Bad Request) if the Customer is not
	 *         valid, or with status 500 (Internal Server Error) if the Customer
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/customers")

	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws URISyntaxException {
		log.debug("REST request to update Customer : {}", customer);
		if (customer.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Customer result = customerService.save(customer);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customer.getId().toString())).body(result);
	}

	/**
	 * GET /customers : get all the customers.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of customers in
	 *         body
	 */
	@GetMapping("/customers")

	public ResponseEntity<List<Customer>> getAllCustomers(Pageable pageable) {
		log.debug("REST request to get a page of Customers");
		Page<Customer> page = customerService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customers");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /customers/:id : get the "id" customer.
	 *
	 * @param id the id of the Customer to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the Customer,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/customers/{id}")

	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		log.debug("REST request to get Customer : {}", id);
		Optional<Customer> customer = customerService.findOne(id);
		return ResponseEntity.of(customer);
	}

	/**
	 * DELETE /customers/:id : delete the "id" customer.
	 *
	 * @param id the id of the Customer to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/customers/{id}")

	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		log.debug("REST request to delete Customer : {}", id);
		customerService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}



}
