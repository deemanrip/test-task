package com.haulmont.testtask.service;

import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.model.MechanicOrderAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MechanicServiceImpl implements MechanicService {

    @Autowired
    private MechanicDao dao;

    @Override
    public void createMechanic(Mechanic mechanic) {
        dao.save(mechanic);
    }

    @Override
    public void updateMechanic(Mechanic mechanic) {
        dao.update(mechanic);
    }

    @Override
    public void deleteMechanic(Mechanic mechanic) {
        dao.delete(mechanic);
    }

    @Override
    public List<Mechanic> getAllMechanics() {
        return dao.getAll();
    }

    @Override
    public Mechanic getMechanicById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<MechanicOrderAmount> getMechanicOrderAmounts() {
        return dao.getMechanicOrderAmounts();
    }
}