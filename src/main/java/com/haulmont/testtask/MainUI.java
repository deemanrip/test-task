package com.haulmont.testtask;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.CustomerService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Theme(ValoTheme.THEME_NAME)
@SpringUI
public class MainUI extends UI {

    @Autowired
    private CustomerService customerService;

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        BeanItemContainer<Customer> itemContainer =
                new BeanItemContainer<>(Customer.class, customerService.findAllCustomers());

        Grid grid = new Grid(itemContainer);
        grid.setColumnOrder("first name", "surname");

        layout.addComponent(new Label("Main UI"));
        layout.addComponent(grid);

        setContent(layout);
    }
}