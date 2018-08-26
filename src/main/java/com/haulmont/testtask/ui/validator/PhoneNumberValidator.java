package com.haulmont.testtask.ui.validator;

import com.vaadin.data.validator.AbstractStringValidator;

public class PhoneNumberValidator extends AbstractStringValidator {

    public PhoneNumberValidator() {
        super("Неверный формат");
    }

    @Override
    protected boolean isValidValue(String s) {
        return s.matches("[\\d\\-+]+");
    }
}