package com.haulmont.testtask.ui.grid;

import com.vaadin.ui.Grid;

public class MechanicOrderAmountGrid extends Grid {

    public void configureColumns() {
        getColumn("mechanic").setHeaderCaption("Механик");
        getColumn("orderAmount").setHeaderCaption("Количество заказов");

        setColumnOrder("mechanic", "orderAmount");

        setSizeFull();
    }
}