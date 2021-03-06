package com.ruhail.InventoryService.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.data.elasticsearch.annotations.Document;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
//@Document(indexName = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_name")
	private String categoryName;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Category categoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
		Category category = (Category) o;
		if (category.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), category.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Category{" + "id=" + getId() + ", categoryName='" + getCategoryName() + "'" + "}";
	}
}
