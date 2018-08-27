package com.haulmont.testtask.ui.data.validator;

import com.vaadin.data.validator.AbstractStringValidator;

public class NotDigitValidator extends AbstractStringValidator {

    public NotDigitValidator() {
        super("Цифры недопустимы");
    }

    @Override
    protected boolean isValidValue(String s) {
        return s.matches("\\D+");
    }
}