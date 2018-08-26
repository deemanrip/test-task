package com.haulmont.testtask.ui.auxiliary;

import com.vaadin.ui.*;

public class ErrorDialog extends Window {

    public ErrorDialog(String errorMessage) {
        super("Ошибка");

        setModal(true);
        setResizable(false);
        setHeight("150");
        setWidth("300");

        Button okButton = new Button("ОК", clickEvent -> this.close());
        Label message = new Label(errorMessage);

        VerticalLayout layout = new VerticalLayout(message, okButton);
        layout.setComponentAlignment(okButton, Alignment.BOTTOM_CENTER);
        layout.setComponentAlignment(message, Alignment.TOP_CENTER);
        layout.setMargin(true);
        layout.setSizeFull();

        setContent(layout);

        center();
    }
}