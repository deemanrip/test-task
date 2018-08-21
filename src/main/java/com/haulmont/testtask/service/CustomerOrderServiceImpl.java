package com.haulmont.testtask.service;

import com.haulmont.testtask.model.CustomerOrder;
import com.haulmont.testtask.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository repository;

    @Override
    public void createOrUpdateCustomerOrder(CustomerOrder order) {
        repository.save(order);
    }

    @Override
    public void deleteCustomerOrder(CustomerOrder order) {
        repository.delete(order);
    }

    @Override
    public List<CustomerOrder> findAllCustomerOrders() {
        List<CustomerOrder> result = new ArrayList<>();
        repository.findAll().forEach(result::add);

        return result;
    }
}