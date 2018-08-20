package com.haulmont.testtask;

import com.haulmont.testtask.config.PersistenceJPAConfig;
import com.haulmont.testtask.invariants.OrderStatus;
import com.haulmont.testtask.model.Customer;
import com.haulmont.testtask.model.CustomerOrder;
import com.haulmont.testtask.model.Mechanic;
import com.haulmont.testtask.service.CustomerService;
import com.haulmont.testtask.service.CustomerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class Test {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
        CustomerService service = ctx.getBean(CustomerServiceImpl.class);
        /*EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();*/

        Customer customer = new Customer();

        customer.setFirstName("customer saved from service");
        service.createCustomer(customer);

        /*Mechanic mechanic = new Mechanic();
        mechanic.setFirstName("mechanic1");

        CustomerOrder customerOrder =
                new CustomerOrder(
                        "test description", customer, mechanic,
                        LocalDate.now(), LocalDate.of(2017, 5, 11),
                        445.444, OrderStatus.ACCEPTED
                );*/

        /*EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(customer);
        em.persist(mechanic);
        em.persist(customerOrder);

        et.commit();

        em.close();*/
    }
}
