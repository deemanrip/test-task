package com.haulmont.testtask.ui.form;

import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.ui.validator.EmptyStringValidator;
import com.haulmont.testtask.ui.validator.NotDigitValidator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.converter.StringToDoubleConverter;
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

    public MechanicFormWindow(Mechanic mechanic) {
        super("Редактирование записи");

        addFieldValidators();

        FormLayout layout = new FormLayout();
        layout.addComponent(nameField);
        layout.addComponent(surnameField);
        layout.addComponent(patronymicField);
        layout.addComponent(hourlyPaymentField);
        layout.setSpacing(true);

        binder = new BeanFieldGroup<>(Mechanic.class);
        binder.setBuffered(false);
        binder.setItemDataSource(mechanic);
        binder.bindMemberFields(this);

        addFormLayout(layout);
    }

    public BeanFieldGroup<Mechanic> getBinder() {
        return binder;
    }

    public void setBinder(BeanFieldGroup<Mechanic> binder) {
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

        hourlyPaymentField.setRequired(true);
        hourlyPaymentField.addValidator(new NullValidator("Обязательное поле", false));
        hourlyPaymentField.addValidator(new EmptyStringValidator());
        hourlyPaymentField.setConverter(new StringToDoubleConverter());
    }
}