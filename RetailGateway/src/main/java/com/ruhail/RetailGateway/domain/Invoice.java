package com.ruhail.RetailGateway.domain;

import java.io.Serializable;
import java.util.Objects;



/**
 * A Invoice.
 */
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;


	private String customerName;


	private Double grossTotal;

	private OrderLine orderLine;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Invoice customerName(String customerName) {
		this.customerName = customerName;
		return this;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getGrossTotal() {
		return grossTotal;
	}

	public Invoice grossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
		return this;
	}

	public void setGrossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
	}

	public OrderLine getOrderLine() {
		return orderLine;
	}

	public Invoice orderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
		return this;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Invoice invoice = (Invoice) o;
		if (invoice.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), invoice.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Invoice{" + "id=" + getId() + ", customerName='" + getCustomerName() + "'" + ", grossTotal="
				+ getGrossTotal() + "}";
	}
}
