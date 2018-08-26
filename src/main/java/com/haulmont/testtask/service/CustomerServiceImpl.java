package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    public void createCustomer(Customer customer) {
        dao.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        dao.update(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        dao.delete(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return dao.getAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return dao.getById(id);
    }
}