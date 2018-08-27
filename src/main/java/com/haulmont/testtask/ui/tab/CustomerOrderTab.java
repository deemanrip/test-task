package com.haulmont.testtask.ui.tab;

import com.haulmont.testtask.invariants.OrderStatus;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.model.CustomerOrder;
import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.service.CustomerOrderService;
import com.haulmont.testtask.service.CustomerService;
import com.haulmont.testtask.service.MechanicService;
import com.haulmont.testtask.ui.auxiliary.ConfirmDialog;
import com.haulmont.testtask.ui.auxiliary.ErrorDialog;
import com.haulmont.testtask.ui.form.CustomerOrderFormWindow;
import com.haulmont.testtask.ui.grid.CustomerOrderGrid;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.EnumSet;
import java.util.List;

@SpringComponent
@UIScope
public class CustomerOrderTab extends VerticalLayout {

    @Autowired
    private CustomerOrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MechanicService mechanicService;

    @Autowired
    private CustomerOrderGrid orderGrid;

    @PostConstruct
    private void init() {
        updateGridData();
        orderGrid.configureColumns();

        addComponent( getGridToolbar() );
        addComponent( getFilterFieldGroup() );
        addComponent(orderGrid);
        setExpandRatio(orderGrid, 1);
        setSizeFull();
    }

    private void updateGridData() {
        BeanItemContainer<CustomerOrder> container =
                new BeanItemContainer<>(CustomerOrder.class, orderService.getAllOrders());
        orderGrid.setContainerDataSource(container);
    }

    private HorizontalLayout getGridToolbar() {
        HorizontalLayout buttonGroup = new HorizontalLayout();

        buttonGroup.addComponent( getAddButton() );
        buttonGroup.addComponent( getEditButton() );
        buttonGroup.addComponent( getDeleteButton() );

        buttonGroup.setHeight("100");
        buttonGroup.setWidth("100%");
        buttonGroup.setMargin(true);

        return buttonGroup;
    }

    private HorizontalLayout getFilterFieldGroup() {
        HorizontalLayout filterFieldGroup = new HorizontalLayout();

        ComboBox customerFilterField = getCustomerFilterField();
        ComboBox statusFilterField = getOrderStatusFilterField();
        TextField orderDescriptionFilterField = new TextField("Описание");
        Button acceptFilterButton = new Button("Применить", clickEvent -> {
            List<CustomerOrder> orders = orderService.getFilteredOrders(
                    (Customer) customerFilterField.getValue(),
                    (OrderStatus) statusFilterField.getValue(),
                    orderDescriptionFilterField.getValue());

            BeanItemContainer<CustomerOrder> container =
                    new BeanItemContainer<>(CustomerOrder.class, orders);
            orderGrid.setContainerDataSource(container);
        });

        filterFieldGroup.addComponent( customerFilterField );
        filterFieldGroup.addComponent( statusFilterField );
        filterFieldGroup.addComponent( orderDescriptionFilterField );
        filterFieldGroup.addComponent( acceptFilterButton );

        filterFieldGroup.setHeight("150");
        filterFieldGroup.setWidth("100%");
        filterFieldGroup.setMargin(true);

        return filterFieldGroup;
    }

    private Button getAddButton() {
        return new Button("Добавить", clickEvent -> {
            BeanItemContainer<Customer> customers = new BeanItemContainer<>(Customer.class, customerService.getAllCustomers());
            BeanItemContainer<Mechanic> mechanics = new BeanItemContainer<>(Mechanic.class, mechanicService.getAllMechanics());

            CustomerOrderFormWindow orderFormWindow =
                    new CustomerOrderFormWindow("Создание новой записи", new CustomerOrder(), customers, mechanics);
            orderFormWindow.addConfirmButtonListener(confirmClickEvent -> {
                BeanFieldGroup<CustomerOrder> binder = orderFormWindow.getBinder();

                if (binder.isValid()) {
                    orderFormWindow.close();
                    CustomerOrder newOrder = binder.getItemDataSource().getBean();
                    orderService.createOrder(newOrder);
                    updateGridData();
                } else {
                    ErrorDialog errorDialog = new ErrorDialog("Неверно заданы значения");
                    getUI().addWindow(errorDialog);
                }
            });

            getUI().addWindow(orderFormWindow);
        });
    }

    private Button getEditButton() {
        return new Button("Изменить", clickEvent -> {
            CustomerOrder selectedOrder = (CustomerOrder) orderGrid.getSelectedRow();
            if (selectedOrder == null) {
                Window errorDialog = new ErrorDialog("Не выбрана ни одна запись");
                getUI().addWindow(errorDialog);
                return;
            }

            BeanItemContainer<Customer> customers = new BeanItemContainer<>(Customer.class, customerService.getAllCustomers());
            BeanItemContainer<Mechanic> mechanics = new BeanItemContainer<>(Mechanic.class, mechanicService.getAllMechanics());

            CustomerOrderFormWindow orderFormWindow =
                    new CustomerOrderFormWindow("Редактирование записи", selectedOrder, customers, mechanics);
            orderFormWindow.addConfirmButtonListener(confirmClickEvent -> {
                BeanFieldGroup<CustomerOrder> binder = orderFormWindow.getBinder();

                if (binder.isValid()) {
                    orderFormWindow.close();
                    CustomerOrder updatedOrder = binder.getItemDataSource().getBean();
                    orderService.updateOrder(updatedOrder);
                    updateGridData();
                } else {
                    ErrorDialog errorDialog = new ErrorDialog("Неверно заданы значения");
                    getUI().addWindow(errorDialog);
                }
            });

            getUI().addWindow(orderFormWindow);
        });
    }

    private Button getDeleteButton() {
        return new Button("Удалить", clickEvent -> {
            CustomerOrder selectedOrder = (CustomerOrder) orderGrid.getSelectedRow();

            if (selectedOrder == null) {
                Window errorDialog = new ErrorDialog("Не выбрана ни одна запись");
                getUI().addWindow(errorDialog);
                return;
            }

            ConfirmDialog confirmDialog = new ConfirmDialog("Удалить выбранную запись?");
            confirmDialog.addConfirmButtonListener(confirmClickEvent -> {
                confirmDialog.close();
                orderService.deleteOrder(selectedOrder);
                updateGridData();
            });
            getUI().addWindow(confirmDialog);
        });
    }

    private ComboBox getCustomerFilterField() {
        BeanItemContainer<Customer> container =
                new BeanItemContainer<>(Customer.class, customerService.getAllCustomers());
        ComboBox comboBox = new ComboBox("Клиент");
        comboBox.setContainerDataSource(container);

        return comboBox;
    }

    private ComboBox getOrderStatusFilterField() {
        BeanItemContainer<OrderStatus> container =
                new BeanItemContainer<>(OrderStatus.class, EnumSet.allOf(OrderStatus.class));
        ComboBox comboBox = new ComboBox("Статус");
        comboBox.setContainerDataSource(container);

        return comboBox;
    }
}