package com.ruhail.RetailGateway.customerservicefeignclient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ruhail.RetailGateway.domain.Customer;

@FeignClient("customerservice")
public interface CustomerServiceClient {

	/**
	 * POST /api/customers : Create a new customer.
	 *
	 * @param Customer the Customer to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         Customer, or with status 400 (Bad Request) if the customer has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/api/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws URISyntaxException;

	/**
	 * PUT /api/customers : Updates an existing customer.
	 *
	 * @param Customer the Customer to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Customer, or with status 400 (Bad Request) if the Customer is not
	 *         valid, or with status 500 (Internal Server Error) if the Customer
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/api/customers")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws URISyntaxException;

	/**
	 * GET /api/customers : get all the customers.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of customers in
	 *         body
	 */
	@GetMapping("/api/customers")
	public ResponseEntity<List<Customer>> getAllCustomers();

	/**
	 * GET /api/customers/:id : get the "id" customer.
	 *
	 * @param id the id of the Customer to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the Customer,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/api/customers/{id}")

	public ResponseEntity<Customer> getCustomer(@PathVariable Long id);

	/**
	 * DELETE /api/customers/:id : delete the "id" customer.
	 *
	 * @param id the id of the Customer to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/api/customers/{id}")

	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id);

}
