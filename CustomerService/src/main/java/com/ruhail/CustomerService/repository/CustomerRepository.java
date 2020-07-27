package com.ruhail.CustomerService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruhail.CustomerService.domain.Customer;

/**
 * Spring Data repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}