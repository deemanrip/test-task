package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.model.MechanicOrderAmount;
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
        entityManager.merge(mechanic);
    }

    @Override
    public void delete(Mechanic mechanic) {
        entityManager.remove( entityManager.merge(mechanic) );
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

    @Override
    public List<MechanicOrderAmount> getMechanicOrderAmounts() {
        String jpqlQuery =
                " select new com.haulmont.testtask.model.MechanicOrderAmount(o.mechanic, count(o))" +
                " from CustomerOrder o" +
                " group by o.mechanic";

        TypedQuery<MechanicOrderAmount> query = entityManager.createQuery(jpqlQuery, MechanicOrderAmount.class);
        return query.getResultList();
    }
}