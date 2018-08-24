package com.haulmont.testtask.ui;

import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.service.MechanicService;
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
    private MechanicService mechanicService;

    @Override
    protected void init(VaadinRequest request) {

        for (int i = 0; i < 10; i++) {
            Mechanic mechanic = new Mechanic("test " + i, "test " + i, "test " + i, 10.2);
            mechanicService.createMechanic(mechanic);
        }

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);

        BeanItemContainer<Mechanic> itemContainer =
                new BeanItemContainer<>(Mechanic.class, mechanicService.getAllMechanics());

        Grid grid = new Grid(itemContainer);
        grid.setColumnOrder("firstName", "surname");

        layout.addComponent(new Label("Main UI"));
        layout.addComponent(grid);

        setContent(layout);
    }
}