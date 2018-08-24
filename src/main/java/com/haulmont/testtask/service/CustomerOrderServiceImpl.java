package com.haulmont.testtask.service;

import com.haulmont.testtask.model.CustomerOrder;
import com.haulmont.testtask.dao.CustomerOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderDao dao;

    @Override
    public void createCustomerOrder(CustomerOrder order) {

    }

    @Override
    public void updateCustomerOrder(CustomerOrder order) {

    }

    @Override
    public void deleteCustomerOrder(Long id) {

    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        return null;
    }
}