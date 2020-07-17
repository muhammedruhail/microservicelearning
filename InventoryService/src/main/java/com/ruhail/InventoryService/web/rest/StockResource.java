package com.ruhail.InventoryService.web.rest;

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
import org.springframework.web.bind.annotation.RestController;

import com.ruhail.InventoryService.domain.Stock;
import com.ruhail.InventoryService.rest.utils.HeaderUtil;
import com.ruhail.InventoryService.rest.utils.PaginationUtil;
import com.ruhail.InventoryService.service.StockService;
import com.ruhail.InventoryService.web.rest.errors.BadRequestAlertException;


/**
 * REST controller for managing Stock.
 */
@RestController
@RequestMapping("/api")
public class StockResource {

	private final Logger log = LoggerFactory.getLogger(StockResource.class);

	private static final String ENTITY_NAME = "inventoryserviceStock";

	private final StockService stockService;

	public StockResource(StockService stockService) {
		this.stockService = stockService;
	}

	/**
	 * POST /stocks : Create a new stock.
	 *
	 * @param stock the Stock to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         Stock, or with status 400 (Bad Request) if the stock has already an
	 *         ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/stocks")

	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) throws URISyntaxException {
		log.debug("REST request to save Stock : {}", stock);
		if (stock.getId() != null) {
			throw new BadRequestAlertException("A new stock cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Stock result = stockService.save(stock);
		return ResponseEntity.created(new URI("/api/stocks/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /stocks : Updates an existing stock.
	 *
	 * @param Stock the Stock to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         Stock, or with status 400 (Bad Request) if the Stock is not valid, or
	 *         with status 500 (Internal Server Error) if the Stock couldn't be
	 *         updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/stocks")

	public ResponseEntity<Stock> updateStock(@RequestBody Stock stock) throws URISyntaxException {
		log.debug("REST request to update Stock : {}", stock);
		if (stock.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Stock result = stockService.save(stock);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stock.getId().toString()))
				.body(result);
	}

	/**
	 * GET /stocks : get all the stocks.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of stocks in
	 *         body
	 */
	@GetMapping("/stocks")

	public ResponseEntity<List<Stock>> getAllStocks(Pageable pageable) {
		log.debug("REST request to get a page of Stocks");
		Page<Stock> page = stockService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stocks");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /stocks/:id : get the "id" stock.
	 *
	 * @param id the id of the Stock to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the Stock, or
	 *         with status 404 (Not Found)
	 */
	@GetMapping("/stocks/{id}")

	public ResponseEntity<Stock> getStock(@PathVariable Long id) {
		log.debug("REST request to get Stock : {}", id);
		Optional<Stock> stock = stockService.findOne(id);
		return ResponseEntity.of(stock);
	}

	/**
	 * DELETE /stocks/:id : delete the "id" stock.
	 *
	 * @param id the id of the Stock to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/stocks/{id}")

	public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
		log.debug("REST request to delete Stock : {}", id);
		stockService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

}
