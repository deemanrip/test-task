package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    public void createCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public List<Customer> getAllCustomers() {

        return null;
    }
}