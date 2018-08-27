package com.haulmont.testtask.ui.data.converter;

import com.vaadin.data.util.converter.Converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class DateToLocalDateConverter implements Converter<Date, LocalDate> {
    @Override
    public LocalDate convertToModel(Date date, Class<? extends LocalDate> aClass, Locale locale) throws ConversionException {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public Date convertToPresentation(LocalDate localDate, Class<? extends Date> aClass, Locale locale) throws ConversionException {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Class<LocalDate> getModelType() {
        return LocalDate.class;
    }

    @Override
    public Class<Date> getPresentationType() {
        return Date.class;
    }
}
