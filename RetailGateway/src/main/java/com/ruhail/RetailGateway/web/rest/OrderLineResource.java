package com.ruhail.RetailGateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ruhail.RetailGateway.domain.OrderLine;
import com.ruhail.RetailGateway.web.rest.errors.BadRequestAlertException;
import com.ruhail.RetailGateway.web.rest.utils.HeaderUtil;


/**
 * REST controller for managing OrderLine.
 */
@RestController
@RequestMapping("/api")
public class OrderLineResource {

	private final Logger log = LoggerFactory.getLogger(OrderLineResource.class);

	private static final String ENTITY_NAME = "orderServiceOrderLine";

	public OrderLineResource() {
	}

	/**
	 * POST /order-lines : Create a new orderLine.
	 *
	 * @param orderLine the OrderLine to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         OrderLine, or with status 400 (Bad Request) if the orderLine has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/order-lines")

	public ResponseEntity<OrderLine> createOrderLine(@RequestBody OrderLine orderLine)
			throws URISyntaxException {
		log.debug("REST request to save OrderLine : {}", orderLine);
		if (orderLine.getId() != null) {
			throw new BadRequestAlertException("A new orderLine cannot already have an ID", ENTITY_NAME, "idexists");
		}
		/*
		 * OrderLine result = orderLineService.save(orderLine); return
		 * ResponseEntity.created(new URI("/api/order-lines/" + result.getId()))
		 * .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
		 * result.getId().toString())).body(result);
		 */
		return null;
	}

	/**
	 * PUT /order-lines : Updates an existing orderLine.
	 *
	 * @param orderLine the OrderLine to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         OrderLine, or with status 400 (Bad Request) if the OrderLine is not
	 *         valid, or with status 500 (Internal Server Error) if the OrderLine
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/order-lines")

	public ResponseEntity<OrderLine> updateOrderLine(@RequestBody OrderLine orderLine)
			throws URISyntaxException {
		log.debug("REST request to update OrderLine : {}", orderLine);
		if (orderLine.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		/*
		 * OrderLine result = orderLineService.save(orderLine); return
		 * ResponseEntity.ok() .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
		 * orderLine.getId().toString())).body(result);
		 */
		return null;
	}

	/**
	 * GET /order-lines : get all the orderLines.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of orderLines in
	 *         body
	 */
	@GetMapping("/order-lines")

	public ResponseEntity<List<OrderLine>> getAllOrderLines() {
		log.debug("REST request to get a page of OrderLines");
		/*
		 * Page<OrderLine> page = orderLineService.findAll(pageable); HttpHeaders
		 * headers = PaginationUtil.generatePaginationHttpHeaders(page,
		 * "/api/order-lines"); return
		 * ResponseEntity.ok().headers(headers).body(page.getContent());
		 */
		return null;
	}

	/**
	 * GET /order-lines/:id : get the "id" orderLine.
	 *
	 * @param id the id of the OrderLine to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the OrderLine,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/order-lines/{id}")

	public ResponseEntity<OrderLine> getOrderLine(@PathVariable Long id) {
		log.debug("REST request to get OrderLine : {}", id);
		/*
		 * Optional<OrderLine> orderLine = orderLineService.findOne(id); return
		 * ResponseEntity.of(orderLine);
		 */
		return null;
	}

	/**
	 * DELETE /order-lines/:id : delete the "id" orderLine.
	 *
	 * @param id the id of the OrderLine to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/order-lines/{id}")

	public ResponseEntity<Void> deleteOrderLine(@PathVariable Long id) {
		log.debug("REST request to delete OrderLine : {}", id);
		/*
		 * orderLineService.delete(id); return
		 * ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
		 * id.toString())).build();
		 */
		return null;
	}
}

