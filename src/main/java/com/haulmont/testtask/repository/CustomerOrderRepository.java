package com.haulmont.testtask.repository;

import com.haulmont.testtask.model.CustomerOrder;
import org.springframework.data.repository.CrudRepository;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {
}
