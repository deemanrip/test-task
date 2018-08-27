package com.haulmont.testtask.model;

public class MechanicOrderAmount {

    private Mechanic mechanic;
    private Long orderAmount;

    public MechanicOrderAmount(Mechanic mechanic, Long orderAmount) {
        this.mechanic = mechanic;
        this.orderAmount = orderAmount;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }
}