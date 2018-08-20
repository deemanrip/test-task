package com.haulmont.testtask.model;

import com.haulmont.testtask.invariants.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "work_completion_date")
    private LocalDate workCompletionDate;

    private double cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    public CustomerOrder() {
    }

    public CustomerOrder(String description, Customer customer, Mechanic mechanic,
                         LocalDate creationDate, LocalDate workCompletionDate, double cost, OrderStatus orderStatus) {
        this.description = description;
        this.customer = customer;
        this.mechanic = mechanic;
        this.creationDate = creationDate;
        this.workCompletionDate = workCompletionDate;
        this.cost = cost;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getWorkCompletionDate() {
        return workCompletionDate;
    }

    public void setWorkCompletionDate(LocalDate workCompletionDate) {
        this.workCompletionDate = workCompletionDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}