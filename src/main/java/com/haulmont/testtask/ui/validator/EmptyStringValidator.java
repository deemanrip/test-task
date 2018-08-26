package com.haulmont.testtask.ui.validator;

import com.vaadin.data.validator.AbstractStringValidator;

public class EmptyStringValidator extends AbstractStringValidator {

    public EmptyStringValidator() {
        super("Пустая строка недопустима");
    }

    @Override
    protected boolean isValidValue(String s) {
        return !s.trim().equals("");
    }
}