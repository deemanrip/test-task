package com.haulmont.testtask.ui.form;

import com.vaadin.ui.*;

public class FormWindow extends Window {
    private Panel panel;
    private Button confirmButton;
    private VerticalLayout verticalLayout;

    public FormWindow(String caption) {
        super(caption);

        panel = new Panel();
        panel.setSizeFull();
        confirmButton = new Button("ОК");
        verticalLayout = new VerticalLayout();

        Button cancelButton = new Button("Отменить");
        cancelButton.addClickListener(clickEvent -> this.close());

        setModal(true);
        setResizable(false);
        setHeight("500");
        setWidth("700");
        center();

        HorizontalLayout buttonGroup = new HorizontalLayout();
        buttonGroup.setSizeFull();
        buttonGroup.setMargin(true);

        verticalLayout.addComponent(buttonGroup);
        verticalLayout.setMargin(true);
        verticalLayout.setSizeFull();

        buttonGroup.addComponent(confirmButton);
        buttonGroup.addComponent(cancelButton);
        verticalLayout.setComponentAlignment(buttonGroup, Alignment.BOTTOM_CENTER);

        panel.setContent(verticalLayout);
        setContent(panel);
    }

    public void addFormLayout(FormLayout formLayout) {
        verticalLayout.addComponent(formLayout, 0);
    }

    public void addConfirmButtonListener(Button.ClickListener clickListener) {
        confirmButton.addClickListener(clickListener);
    }
}