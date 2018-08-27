package com.haulmont.testtask.invariants;

public enum OrderStatus {

    PLANNED("Запланирован"),
    DONE("Выполнен"),
    ACCEPTED("Принят клиентом");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}