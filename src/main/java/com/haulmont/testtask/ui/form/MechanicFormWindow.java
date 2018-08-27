package com.haulmont.testtask.ui.form;

import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.ui.data.validator.NotEmptyStringValidator;
import com.haulmont.testtask.ui.data.validator.NotDigitValidator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class MechanicFormWindow extends FormWindow {

    private BeanFieldGroup<Mechanic> binder;

    @PropertyId("firstName")
    private TextField nameField = new TextField("Имя");

    @PropertyId("surname")
    private TextField surnameField = new TextField("Фамилия");

    @PropertyId("patronymic")
    private TextField patronymicField = new TextField("Отчество");

    @PropertyId("hourlyPayment")
    private TextField hourlyPaymentField = new TextField("Почасовая оплата");

    public MechanicFormWindow(String caption, Mechanic mechanic) {
        super(caption);

        setHeight("400");
        setWidth("400");

        configureFields();

        FormLayout formLayout = new FormLayout();
        formLayout.addComponent(nameField);
        formLayout.addComponent(surnameField);
        formLayout.addComponent(patronymicField);
        formLayout.addComponent(hourlyPaymentField);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        binder = new BeanFieldGroup<>(Mechanic.class);
        binder.setBuffered(false);
        binder.setItemDataSource(mechanic);
        binder.bindMemberFields(this);

        addFormLayout(formLayout);
    }

    public BeanFieldGroup<Mechanic> getBinder() {
        return binder;
    }

    public void setBinder(BeanFieldGroup<Mechanic> binder) {
        this.binder = binder;
    }

    private void configureFields() {
        nameField.setNullRepresentation("");
        nameField.setRequired(true);
        nameField.addValidator(new NullValidator("Обязательное поле", false));
        nameField.addValidator(new NotDigitValidator());
        nameField.addValidator(new NotEmptyStringValidator());

        surnameField.setNullRepresentation("");
        surnameField.setRequired(true);
        surnameField.addValidator(new NullValidator("Обязательное поле", false));
        surnameField.addValidator(new NotDigitValidator());
        surnameField.addValidator(new NotEmptyStringValidator());

        patronymicField.setNullRepresentation("");
        patronymicField.setRequired(true);
        patronymicField.addValidator(new NullValidator("Обязательное поле", false));
        patronymicField.addValidator(new NotDigitValidator());
        patronymicField.addValidator(new NotEmptyStringValidator());

        hourlyPaymentField.setRequired(true);
        hourlyPaymentField.setNullRepresentation("0");
        hourlyPaymentField.setConversionError("Необходимо указать число");
    }
}