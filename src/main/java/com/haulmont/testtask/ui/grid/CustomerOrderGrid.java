package com.haulmont.testtask.ui.grid;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;

@SpringComponent
@UIScope
public class CustomerOrderGrid extends Grid {

    public void set() {
        removeColumn("id");

        getColumn("description").setHeaderCaption("Описание");
        getColumn("customer").setHeaderCaption("Клиент");
        getColumn("mechanic").setHeaderCaption("Механик");
        getColumn("creationDate").setHeaderCaption("Дата создания");
        getColumn("workCompletionDate").setHeaderCaption("Дата окончания работ");
        getColumn("cost").setHeaderCaption("Стоимость");
        getColumn("orderStatus").setHeaderCaption("Статус");

        setColumnOrder("description", "customer", "mechanic", "creationDate", "workCompletionDate", "cost", "orderStatus");

        setSizeFull();
    }
}