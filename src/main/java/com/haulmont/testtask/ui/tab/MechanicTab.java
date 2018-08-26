package com.haulmont.testtask.ui.tab;

import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.service.MechanicService;
import com.haulmont.testtask.ui.auxiliary.ConfirmDialog;
import com.haulmont.testtask.ui.auxiliary.ErrorDialog;
import com.haulmont.testtask.ui.form.MechanicFormWindow;
import com.haulmont.testtask.ui.grid.MechanicGrid;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class MechanicTab extends VerticalLayout {

    @Autowired
    private MechanicService mechanicService;

    @Autowired
    private MechanicGrid mechanicGrid;

    @PostConstruct
    private void init() {
        updateGridData();
        mechanicGrid.set();

        addComponent(getButtonGroup());
        addComponent(mechanicGrid);
        setExpandRatio(mechanicGrid, 1);
        setSizeFull();
    }

    private void updateGridData() {
        BeanItemContainer<Mechanic> container =
                new BeanItemContainer<>(Mechanic.class, mechanicService.getAllMechanics());
        mechanicGrid.setContainerDataSource(container);
    }

    private HorizontalLayout getButtonGroup() {
        HorizontalLayout buttonGroup = new HorizontalLayout();

        buttonGroup.addComponent( getAddButton() );
        buttonGroup.addComponent( getEditButton() );
        buttonGroup.addComponent( getDeleteButton() );
        buttonGroup.addComponent( getViewStatisticsButton() );

        buttonGroup.setHeight("100");
        buttonGroup.setWidth("100%");
        buttonGroup.setMargin(true);

        return buttonGroup;
    }

    private Button getAddButton() {
        return new Button("Добавить", clickEvent -> {
            MechanicFormWindow mechanicFormWindow = new MechanicFormWindow(new Mechanic());
            mechanicFormWindow.addConfirmButtonListener(confirmClickEvent -> {
                BeanFieldGroup<Mechanic> binder = mechanicFormWindow.getBinder();

                if (binder.isValid()) {
                    mechanicFormWindow.close();
                    Mechanic newMechanic = binder.getItemDataSource().getBean();
                    mechanicService.createMechanic(newMechanic);
                    updateGridData();
                } else {
                    ErrorDialog errorDialog = new ErrorDialog("Неверно заданы значения");
                    getUI().addWindow(errorDialog);
                }
            });

            getUI().addWindow(mechanicFormWindow);
        });
    }

    private Button getEditButton() {
        return new Button("Изменить", clickEvent -> {
            Mechanic selectedMechanic = (Mechanic) mechanicGrid.getSelectedRow();
            if (selectedMechanic == null) {
                Window errorDialog = new ErrorDialog("Не выбрана ни одна запись");
                getUI().addWindow(errorDialog);
                return;
            }

            MechanicFormWindow mechanicFormWindow = new MechanicFormWindow(selectedMechanic);
            mechanicFormWindow.addConfirmButtonListener(confirmClickEvent -> {
                BeanFieldGroup<Mechanic> binder = mechanicFormWindow.getBinder();

                if (binder.isValid()) {
                    mechanicFormWindow.close();
                    Mechanic updatedMechanic = binder.getItemDataSource().getBean();
                    mechanicService.updateMechanic(updatedMechanic);
                    updateGridData();
                } else {
                    ErrorDialog errorDialog = new ErrorDialog("Неверно заданы значения");
                    getUI().addWindow(errorDialog);
                }
            });

            getUI().addWindow(mechanicFormWindow);
        });
    }

    private Button getDeleteButton() {
        return new Button("Удалить", clickEvent -> {
            Mechanic selectedMechanic = (Mechanic) mechanicGrid.getSelectedRow();

            if (selectedMechanic == null) {
                Window errorDialog = new ErrorDialog("Не выбрана ни одна запись");
                getUI().addWindow(errorDialog);
                return;
            }

            ConfirmDialog confirmDialog = new ConfirmDialog("Удалить выбранную запись?");
            confirmDialog.addConfirmButtonListener(confirmClickEvent -> {
                confirmDialog.close();
                mechanicService.deleteMechanic(selectedMechanic);
                updateGridData();
            });
            getUI().addWindow(confirmDialog);
        });
    }

    private Button getViewStatisticsButton() {
        return new Button("Посмотреть статистику", clickEvent -> {

        });
    }
}