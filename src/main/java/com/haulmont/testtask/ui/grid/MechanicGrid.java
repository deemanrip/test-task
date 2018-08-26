package com.haulmont.testtask.ui.grid;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;

@SpringComponent
@UIScope
public class MechanicGrid extends Grid {

    public void set() {
        removeColumn("id");

        getColumn("firstName").setHeaderCaption("Имя");
        getColumn("surname").setHeaderCaption("Фамилия");
        getColumn("patronymic").setHeaderCaption("Отчество");
        getColumn("hourlyPayment").setHeaderCaption("Почасовая оплата");

        setColumnOrder("firstName", "surname", "patronymic", "hourlyPayment");

        setSizeFull();
    }
}