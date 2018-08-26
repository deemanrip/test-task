package com.haulmont.testtask.ui.auxiliary;

import com.vaadin.ui.*;

public class ConfirmDialog extends Window {

    private Button confirmButton;

    public ConfirmDialog(String confirmMessage) {
        super("Подтверждение");
        setModal(true);
        setResizable(false);
        setHeight("200");
        setWidth("300");

        Button cancelButton = new Button("Нет", clickEvent -> this.close());
        confirmButton = new Button("Да");
        Label message = new Label(confirmMessage);

        HorizontalLayout buttonGroup = new HorizontalLayout(confirmButton, cancelButton);
        buttonGroup.setMargin(true);
        buttonGroup.setSizeFull();

        VerticalLayout layout = new VerticalLayout(message, buttonGroup);
        layout.setComponentAlignment(buttonGroup, Alignment.BOTTOM_CENTER);
        layout.setMargin(true);
        layout.setSizeFull();

        setContent(layout);

        center();
    }

    public void addConfirmButtonListener(Button.ClickListener clickListener) {
        confirmButton.addClickListener(clickListener);
    }
}