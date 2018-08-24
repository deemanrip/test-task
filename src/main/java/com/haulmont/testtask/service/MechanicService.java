package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Mechanic;

import java.util.List;

public interface MechanicService {

    void createMechanic(Mechanic mechanic);
    void updateMechanic(Mechanic mechanic);
    void deleteMechanic(Long id);

    List<Mechanic> getAllMechanics();
}
