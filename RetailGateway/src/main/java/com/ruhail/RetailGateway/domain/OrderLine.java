package com.ruhail.RetailGateway.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OrderLine.
 */
public class OrderLine implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Instant orderDate;

	private Boolean orderStatus;

	private Long productId;

	private Long customerId;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getOrderDate() {
		return orderDate;
	}

	public OrderLine orderDate(Instant orderDate) {
		this.orderDate = orderDate;
		return this;
	}

	public void setOrderDate(Instant orderDate) {
		this.orderDate = orderDate;
	}

	public Boolean isOrderStatus() {
		return orderStatus;
	}

	public OrderLine orderStatus(Boolean orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}

	public void setOrderStatus(Boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getProductId() {
		return productId;
	}

	public OrderLine productId(Long productId) {
		this.productId = productId;
		return this;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public OrderLine customerId(Long customerId) {
		this.customerId = customerId;
		return this;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
		OrderLine orderLine = (OrderLine) o;
		if (orderLine.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), orderLine.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "OrderLine{" + "id=" + getId() + ", orderDate='" + getOrderDate() + "'" + ", orderStatus='"
				+ isOrderStatus() + "'" + ", productId=" + getProductId() + ", customerId=" + getCustomerId() + "}";
	}
}