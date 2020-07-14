package com.ruhail.InventoryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruhail.InventoryService.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
