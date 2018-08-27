package com.haulmont.testtask.ui;

import com.haulmont.testtask.ui.tab.CustomerOrderTab;
import com.haulmont.testtask.ui.tab.CustomerTab;
import com.haulmont.testtask.ui.tab.MechanicTab;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@Theme(ValoTheme.THEME_NAME)
@SpringUI
public class MainUI extends UI {

    @Autowired
    private CustomerTab customerTab;

    @Autowired
    private MechanicTab mechanicTab;

    @Autowired
    private CustomerOrderTab customerOrderTab;

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Test Task");

        HorizontalLayout footer = new HorizontalLayout();
        footer.setHeight("100");
        footer.setWidth("100%");

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setSizeFull();

        Panel contentPanel = new Panel(contentLayout);
        contentPanel.setSizeFull();

        TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(customerTab, "Клиенты");
        tabSheet.addTab(mechanicTab, "Механики");
        tabSheet.addTab(customerOrderTab, "Заказы");
        tabSheet.setSizeFull();

        contentLayout.addComponent(tabSheet);

        VerticalLayout mainLayout = new VerticalLayout(contentPanel, footer);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(contentPanel, 1);

        setContent(mainLayout);
    }
}