package com.haulmont.testtask.repository;

import com.haulmont.testtask.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
