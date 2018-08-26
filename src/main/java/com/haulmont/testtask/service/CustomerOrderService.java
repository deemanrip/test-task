package com.haulmont.testtask.service;

import com.haulmont.testtask.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {

    void createOrder(CustomerOrder order);
    void updateOrder(CustomerOrder order);
    void deleteOrder(CustomerOrder order);

    List<CustomerOrder> getAllOrders();
    CustomerOrder getOrderById(Long id);
}