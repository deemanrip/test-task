package com.haulmont.testtask.model;

import javax.persistence.*;

@Entity
public class Mechanic {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    private String surname;

    private String patronymic;

    @Column(name = "hourly_payment")
    private double hourlyPayment;

    public Mechanic() {
    }

    public Mechanic(String firstName, String surname, String patronymic, double hourlyPayment) {
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.hourlyPayment = hourlyPayment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public double getHourlyPayment() {
        return hourlyPayment;
    }

    public void setHourlyPayment(double hourlyPayment) {
        this.hourlyPayment = hourlyPayment;
    }
}