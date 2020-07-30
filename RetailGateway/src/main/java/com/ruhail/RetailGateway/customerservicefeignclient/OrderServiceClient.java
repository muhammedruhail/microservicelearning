package com.ruhail.RetailGateway.customerservicefeignclient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ruhail.RetailGateway.domain.Invoice;
import com.ruhail.RetailGateway.domain.OrderLine;

@FeignClient("orderservice")
public interface OrderServiceClient {

	// -----------Invoice Resource of Order Service-----------
	/**
	 * POST /api/invoices : Create a new invoice.
	 *
	 * @param invoice the invoiceDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         invoiceDTO, or with status 400 (Bad Request) if the invoice has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/api/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) throws URISyntaxException;

	/**
	 * PUT /api/invoices : Updates an existing invoice.
	 *
	 * @param invoiceDTO the invoice to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         invoiceDTO, or with status 400 (Bad Request) if the invoiceDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the invoiceDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/api/invoices")

	public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice) throws URISyntaxException;

	/**
	 * GET /api/invoices : get all the invoices.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of invoices in
	 *         body
	 */
	@GetMapping("/api/invoices")

	public ResponseEntity<List<Invoice>> getAllInvoices();// Pageable pageable
	
	/**
	 * GET /api/invoices/:id : get the "id" invoice.
	 *
	 * @param id the id of the invoice to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the invoiceDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/api/invoices/{id}")
	public ResponseEntity<Invoice> getInvoice(@PathVariable Long id);

	/**
	 * DELETE /api/invoices/:id : delete the "id" invoice.
	 *
	 * @param id the id of the invoiceDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/api/invoices/{id}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long id);

	// -----------OrderLine Resource of Order Service-----------

	/**
	 * POST /api/order-lines : Create a new orderLine.
	 *
	 * @param orderLine the OrderLine to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         OrderLine, or with status 400 (Bad Request) if the orderLine has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/api/order-lines")

	public ResponseEntity<OrderLine> createOrderLine(@RequestBody OrderLine orderLine)
			throws URISyntaxException;

	/**
	 * PUT /api/order-lines : Updates an existing orderLine.
	 *
	 * @param orderLine the OrderLine to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         OrderLine, or with status 400 (Bad Request) if the OrderLine is not
	 *         valid, or with status 500 (Internal Server Error) if the OrderLine
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/api/order-lines")

	public ResponseEntity<OrderLine> updateOrderLine(@RequestBody OrderLine orderLine)
			throws URISyntaxException;

	/**
	 * GET /api/order-lines : get all the orderLines.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of orderLines in
	 *         body
	 */
	@GetMapping("/api/order-lines")

	public ResponseEntity<List<OrderLine>> getAllOrderLines();// Pageable pageable

	/**
	 * GET /api/order-lines/:id : get the "id" orderLine.
	 *
	 * @param id the id of the OrderLine to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the OrderLine,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/api/order-lines/{id}")

	public ResponseEntity<OrderLine> getOrderLine(@PathVariable Long id);

	/**
	 * DELETE /api/order-lines/:id : delete the "id" orderLine.
	 *
	 * @param id the id of the OrderLine to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/api/order-lines/{id}")

	public ResponseEntity<Void> deleteOrderLine(@PathVariable Long id);
}
