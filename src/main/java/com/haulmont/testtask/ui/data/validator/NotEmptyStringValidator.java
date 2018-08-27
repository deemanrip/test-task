package com.haulmont.testtask.ui.data.validator;

import com.vaadin.data.validator.AbstractStringValidator;

public class NotEmptyStringValidator extends AbstractStringValidator {

    public NotEmptyStringValidator() {
        super("Пустая строка недопустима");
    }

    @Override
    protected boolean isValidValue(String s) {
        return !s.trim().equals("");
    }
}