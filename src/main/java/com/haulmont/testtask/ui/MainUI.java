package com.haulmont.testtask.ui;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.CustomerService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

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
        grid.setColumnOrder("firstName", "surname");

        layout.addComponent(new Label("Main UI"));
        layout.addComponent(grid);

        setContent(layout);
    }
}