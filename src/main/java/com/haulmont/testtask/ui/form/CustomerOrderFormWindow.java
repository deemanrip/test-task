package com.haulmont.testtask.ui.form;

import com.haulmont.testtask.invariants.OrderStatus;
import com.haulmont.testtask.model.CustomerOrder;
import com.haulmont.testtask.ui.data.converter.DateToLocalDateConverter;
import com.haulmont.testtask.ui.data.validator.NotEmptyStringValidator;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.*;

import java.util.EnumSet;

public class CustomerOrderFormWindow extends FormWindow {

    private BeanFieldGroup<CustomerOrder> binder;

    @PropertyId("description")
    private TextArea descriptionField = new TextArea("Описание");

    @PropertyId("customer")
    private ComboBox customerField = new ComboBox("Клиент");

    @PropertyId("mechanic")
    private ComboBox mechanicField = new ComboBox("Механик");

    @PropertyId("creationDate")
    private DateField creationDateField = new DateField("Дата создания");

    @PropertyId("workCompletionDate")
    private DateField workCompletionDateField = new DateField("Дата окончания работ");

    @PropertyId("cost")
    private TextField costField = new TextField("Стоимость");

    @PropertyId("orderStatus")
    private ComboBox orderStatusField = new ComboBox("Статус");

    public CustomerOrderFormWindow(String caption, CustomerOrder order, Container customers, Container mechanics) {
        super(caption);

        setHeight("600");
        setWidth("500");

        configureFields(customers, mechanics);

        FormLayout formLayout = new FormLayout();

        formLayout.addComponent(descriptionField);
        formLayout.addComponent(customerField);
        formLayout.addComponent(mechanicField);
        formLayout.addComponent(creationDateField);
        formLayout.addComponent(workCompletionDateField);
        formLayout.addComponent(costField);
        formLayout.addComponent(orderStatusField);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        binder = new BeanFieldGroup<>(CustomerOrder.class);
        binder.setBuffered(false);
        binder.setItemDataSource(order);
        binder.bindMemberFields(this);

        creationDateField.setEnabled(false);

        addFormLayout(formLayout);
    }

    public BeanFieldGroup<CustomerOrder> getBinder() {
        return binder;
    }

    public void setBinder(BeanFieldGroup<CustomerOrder> binder) {
        this.binder = binder;
    }

    private void configureFields(Container customers, Container mechanics) {
        descriptionField.setRequired(true);
        descriptionField.setNullRepresentation("");
        descriptionField.addValidator(new NotEmptyStringValidator());
        descriptionField.addValidator(new NullValidator("Обязательное поле", false));

        customerField.setContainerDataSource(customers);
        customerField.addValidator(new NullValidator("Обязательное поле", false));

        mechanicField.setContainerDataSource(mechanics);
        mechanicField.addValidator(new NullValidator("Обязательное поле", false));

        creationDateField.setConverter(new DateToLocalDateConverter());
        creationDateField.setEnabled(false);
        creationDateField.setReadOnly(true);

        workCompletionDateField.setConverter(new DateToLocalDateConverter());
        workCompletionDateField.setRequired(true);
        workCompletionDateField.addValidator(new NullValidator("Обязательное поле", false));

        costField.setRequired(true);
        costField.setNullRepresentation("0");
        costField.setConversionError("Необходимо указать число");

        BeanItemContainer<OrderStatus> container =
                new BeanItemContainer<>(OrderStatus.class, EnumSet.allOf(OrderStatus.class));
        orderStatusField.setContainerDataSource(container);
        orderStatusField.addValidator(new NullValidator("Обязательное поле", false));
    }
}