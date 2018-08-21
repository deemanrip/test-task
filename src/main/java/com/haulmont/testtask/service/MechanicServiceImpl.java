package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MechanicServiceImpl implements MechanicService {

    @Autowired
    private MechanicRepository repository;

    @Override
    public void createOrUpdateMechanic(Mechanic mechanic) {
        repository.save(mechanic);
    }

    @Override
    public void deleteMechanic(Mechanic mechanic) {
        repository.delete(mechanic);
    }

    @Override
    public List<Mechanic> findAllMechanics() {
        List<Mechanic> result = new ArrayList<>();
        repository.findAll().forEach(result::add);

        return result;
    }
}
