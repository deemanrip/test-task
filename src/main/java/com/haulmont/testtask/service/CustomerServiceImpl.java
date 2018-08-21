package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void createOrUpdateCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        repository.delete(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> result = new ArrayList<>();
        repository.findAll().forEach(result::add);

        return result;
    }
}