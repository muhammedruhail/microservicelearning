package com.ruhail.CustomerService.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.data.elasticsearch.annotations.Document;

/**
 * A Product.
 */
@Entity
@Table(name = "customer")
//@Document(indexName = "product")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_created_date")
	private Instant accountCreatedDate;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getAccountCreatedDate() {
		return accountCreatedDate;
	}

	public void setAccountCreatedDate(Instant accountCreatedDate) {
		this.accountCreatedDate = accountCreatedDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Customer customer = (Customer) o;
		if (customer.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), customer.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", accountCreatedDate=" + accountCreatedDate + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", password=" + password + "]";
	}


}
