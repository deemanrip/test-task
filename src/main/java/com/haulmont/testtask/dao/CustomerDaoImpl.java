package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {
        entityManager.remove(customer);
    }

    @Override
    public List<Customer> getAll() {
        String jpqlQuery = "from Customer";
        TypedQuery<Customer> query = entityManager.createQuery(jpqlQuery, Customer.class);

        return query.getResultList();
    }

    @Override
    public Customer getById(Long id) {
        return entityManager.find(Customer.class, id);
    }
}
