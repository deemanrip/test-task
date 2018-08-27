package com.haulmont.testtask.service;

import com.haulmont.testtask.dao.CustomerOrderDao;
import com.haulmont.testtask.invariants.OrderStatus;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.model.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderDao dao;

    @Override
    public void createOrder(CustomerOrder order) {
        dao.save(order);
    }

    @Override
    public void updateOrder(CustomerOrder order) {
        dao.update(order);
    }

    @Override
    public void deleteOrder(CustomerOrder order) {
        dao.delete(order);
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        return dao.getAll();
    }

    @Override
    public CustomerOrder getOrderById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<CustomerOrder> getFilteredOrders(Customer customer, OrderStatus status, String description) {
        Long customerId = customer != null ? customer.getId() : null;
                
        return dao.getByFields(customerId, status, description);
    }
}