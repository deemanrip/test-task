package com.haulmont.testtask.ui.tab;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.service.CustomerService;
import com.haulmont.testtask.ui.auxiliary.ConfirmDialog;
import com.haulmont.testtask.ui.auxiliary.ErrorDialog;
import com.haulmont.testtask.ui.form.CustomerFormWindow;
import com.haulmont.testtask.ui.grid.CustomerGrid;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class CustomerTab extends VerticalLayout {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerGrid customerGrid;

    @PostConstruct
    private void init() {
        updateGridData();
        customerGrid.set();

        addComponent(getButtonGroup());
        addComponent(customerGrid);
        setExpandRatio(customerGrid, 1);
        setSizeFull();
    }

    private void updateGridData() {
        BeanItemContainer<Customer> container =
                new BeanItemContainer<>(Customer.class, customerService.getAllCustomers());
        customerGrid.setContainerDataSource(container);
    }

    private HorizontalLayout getButtonGroup() {
        HorizontalLayout buttonGroup = new HorizontalLayout();

        buttonGroup.addComponent( getAddButton() );
        buttonGroup.addComponent( getEditButton() );
        buttonGroup.addComponent( getDeleteButton() );

        buttonGroup.setHeight("100");
        buttonGroup.setWidth("100%");
        buttonGroup.setMargin(true);

        return buttonGroup;
    }

    private Button getAddButton() {
        return new Button("Добавить", clickEvent -> {
            CustomerFormWindow customerFormWindow = new CustomerFormWindow(new Customer());
            customerFormWindow.addConfirmButtonListener(confirmClickEvent -> {
                BeanFieldGroup<Customer> binder = customerFormWindow.getBinder();

                if (binder.isValid()) {
                    customerFormWindow.close();
                    Customer newCustomer = binder.getItemDataSource().getBean();
                    customerService.createCustomer(newCustomer);
                    updateGridData();
                } else {
                    ErrorDialog errorDialog = new ErrorDialog("Неверно заданы значения");
                    getUI().addWindow(errorDialog);
                }
            });

            getUI().addWindow(customerFormWindow);
        });
    }

    private Button getEditButton() {
        return new Button("Изменить", clickEvent -> {
            Customer selectedCustomer = (Customer) customerGrid.getSelectedRow();
            if (selectedCustomer == null) {
                Window errorDialog = new ErrorDialog("Не выбрана ни одна запись");
                getUI().addWindow(errorDialog);
                return;
            }

            CustomerFormWindow customerFormWindow = new CustomerFormWindow(selectedCustomer);
            customerFormWindow.addConfirmButtonListener(confirmClickEvent -> {
                BeanFieldGroup<Customer> binder = customerFormWindow.getBinder();

                if (binder.isValid()) {
                    customerFormWindow.close();
                    Customer updatedCustomer = binder.getItemDataSource().getBean();
                    customerService.updateCustomer(updatedCustomer);
                    updateGridData();
                } else {
                    ErrorDialog errorDialog = new ErrorDialog("Неверно заданы значения");
                    getUI().addWindow(errorDialog);
                }
            });

            getUI().addWindow(customerFormWindow);
        });
    }

    private Button getDeleteButton() {
        return new Button("Удалить", clickEvent -> {
            Customer selectedCustomer = (Customer) customerGrid.getSelectedRow();

            if (selectedCustomer == null) {
                Window errorDialog = new ErrorDialog("Не выбрана ни одна запись");
                getUI().addWindow(errorDialog);
                return;
            }

            ConfirmDialog confirmDialog = new ConfirmDialog("Удалить выбранную запись?");
            confirmDialog.addConfirmButtonListener(confirmClickEvent -> {
                confirmDialog.close();
                customerService.deleteCustomer(selectedCustomer);
                updateGridData();
            });
            getUI().addWindow(confirmDialog);
        });
    }
}