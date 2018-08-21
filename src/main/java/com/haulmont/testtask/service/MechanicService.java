package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Mechanic;

import java.util.List;

public interface MechanicService {

    void createOrUpdateMechanic(Mechanic mechanic);
    void deleteMechanic(Mechanic mechanic);

    List<Mechanic> findAllMechanics();
}
