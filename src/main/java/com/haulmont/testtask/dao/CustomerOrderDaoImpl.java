package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.CustomerOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerOrderDaoImpl implements CustomerOrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(CustomerOrder order) {
        entityManager.persist(order);
    }

    @Override
    public void update(CustomerOrder order) {
        entityManager.merge(order);
    }

    @Override
    public void delete(CustomerOrder order) {
        entityManager.remove(order);
    }

    @Override
    public List<CustomerOrder> getAll() {
        String jpqlQuery = "from CustomerOrder";
        TypedQuery<CustomerOrder> query = entityManager.createQuery(jpqlQuery, CustomerOrder.class);

        return query.getResultList();
    }

    @Override
    public CustomerOrder getById(Long id) {
        return entityManager.find(CustomerOrder.class, id);
    }
}