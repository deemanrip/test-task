package com.haulmont.testtask.ui.form;

import com.vaadin.ui.*;

public class FormWindow extends Window {
    private Button confirmButton;
    private VerticalLayout verticalLayout;

    public FormWindow(String caption) {
        super(caption);

        Panel panel = new Panel();
        panel.setSizeFull();
        confirmButton = new Button("ОК");
        verticalLayout = new VerticalLayout();

        Button cancelButton = new Button("Отменить");
        cancelButton.addClickListener(clickEvent -> this.close());

        setModal(true);
        setResizable(false);
        center();

        HorizontalLayout buttonGroup = new HorizontalLayout();
        buttonGroup.setWidth("100%");
        buttonGroup.setHeight("100");
        buttonGroup.setMargin(true);

        verticalLayout.addComponent(buttonGroup);
        verticalLayout.setSizeFull();

        buttonGroup.addComponent(confirmButton);
        buttonGroup.addComponent(cancelButton);

        panel.setContent(verticalLayout);
        setContent(panel);
    }

    public void addFormLayout(FormLayout formLayout) {
        verticalLayout.addComponent(formLayout, 0);
        verticalLayout.setExpandRatio(formLayout, 1);
    }

    public void addConfirmButtonListener(Button.ClickListener clickListener) {
        confirmButton.addClickListener(clickListener);
    }
}