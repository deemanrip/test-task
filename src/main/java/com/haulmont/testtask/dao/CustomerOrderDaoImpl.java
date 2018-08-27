package com.haulmont.testtask.dao;

import com.haulmont.testtask.invariants.OrderStatus;
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
        entityManager.remove( entityManager.merge(order) );
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

    @Override
    public List<CustomerOrder> getByFields(Long customerId, OrderStatus status, String description) {
        StringBuilder jpqlQuery = new StringBuilder("from CustomerOrder o");

        boolean notEmptyDescription = description != null && !description.trim().equals("");
        boolean notEmptyFilters = customerId != null || status != null || notEmptyDescription;

        StringBuilder whereExpression = new StringBuilder();
        String and = " and ";
        if (notEmptyFilters) {
            jpqlQuery.append(" where ");
        }

        if (customerId != null) {
            whereExpression.append(and);
            whereExpression.append(" o.customer.id = :customerId ");
        }

        if (status != null) {
            whereExpression.append(and);
            whereExpression.append(" o.orderStatus = :status ");
        }

        if (notEmptyDescription) {
            whereExpression.append(and);
            whereExpression.append(" LOWER (o.description) like :description ");
        }

        if (notEmptyFilters) jpqlQuery.append(whereExpression.subSequence(and.length(), whereExpression.length()));
        TypedQuery<CustomerOrder> query = entityManager.createQuery(jpqlQuery.toString(), CustomerOrder.class);

        if (customerId != null) query.setParameter("customerId", customerId);
        if (status != null) query.setParameter("status", status);
        if (notEmptyDescription) query.setParameter("description", "%" + description.toLowerCase() + "%");

        return query.getResultList();
    }
}