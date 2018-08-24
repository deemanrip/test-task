package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Mechanic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MechanicDaoImpl implements MechanicDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Mechanic mechanic) {
        entityManager.persist(mechanic);
    }

    @Override
    public void update(Mechanic mechanic) {

    }

    @Override
    public void delete(Mechanic mechanic) {
        entityManager.remove(mechanic);
    }

    @Override
    public List<Mechanic> getAll() {
        String jpqlQuery = "from Mechanic";
        TypedQuery<Mechanic> query = entityManager.createQuery(jpqlQuery, Mechanic.class);

        return query.getResultList();
    }

    @Override
    public Mechanic getById(Long id) {
        return entityManager.find(Mechanic.class, id);
    }
}
