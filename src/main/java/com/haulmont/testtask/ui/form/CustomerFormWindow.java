package com.haulmont.testtask.ui.form;

import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.ui.validator.EmptyStringValidator;
import com.haulmont.testtask.ui.validator.NotDigitValidator;
import com.haulmont.testtask.ui.validator.PhoneNumberValidator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.converter.StringToDoubleConverter;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.DoubleValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class CustomerFormWindow extends FormWindow {

    private BeanFieldGroup<Customer> binder;

    @PropertyId("firstName")
    private TextField nameField = new TextField("Имя");

    @PropertyId("surname")
    private TextField surnameField = new TextField("Фамилия");

    @PropertyId("patronymic")
    private TextField patronymicField = new TextField("Отчество");

    @PropertyId("phoneNumber")
    private TextField phoneNumberField = new TextField("Номер телефона");

    public CustomerFormWindow(Customer customer) {
        super("Редактирование записи");

        addFieldValidators();

        FormLayout layout = new FormLayout();
        layout.addComponent(nameField);
        layout.addComponent(surnameField);
        layout.addComponent(patronymicField);
        layout.addComponent(phoneNumberField);
        layout.setSpacing(true);

        binder = new BeanFieldGroup<>(Customer.class);
        binder.setBuffered(false);
        binder.setItemDataSource(customer);
        binder.bindMemberFields(this);

        addFormLayout(layout);
    }

    public BeanFieldGroup<Customer> getBinder() {
        return binder;
    }

    public void setBinder(BeanFieldGroup<Customer> binder) {
        this.binder = binder;
    }

    private void addFieldValidators() {
        nameField.setRequired(true);
        nameField.addValidator(new NullValidator("Обязательное поле", false));
        nameField.addValidator(new NotDigitValidator());
        nameField.addValidator(new EmptyStringValidator());

        surnameField.setRequired(true);
        surnameField.addValidator(new NullValidator("Обязательное поле", false));
        surnameField.addValidator(new NotDigitValidator());
        surnameField.addValidator(new EmptyStringValidator());

        patronymicField.setRequired(true);
        patronymicField.addValidator(new NullValidator("Обязательное поле", false));
        patronymicField.addValidator(new NotDigitValidator());
        patronymicField.addValidator(new EmptyStringValidator());

        phoneNumberField.setRequired(true);
        phoneNumberField.addValidator(new NullValidator("Обязательное поле", false));
        phoneNumberField.addValidator(new PhoneNumberValidator());
        phoneNumberField.addValidator(new EmptyStringValidator());
    }
}