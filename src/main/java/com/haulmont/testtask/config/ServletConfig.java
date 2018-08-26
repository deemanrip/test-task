package com.haulmont.testtask.config;

import com.vaadin.spring.server.SpringVaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
public class ServletConfig extends SpringVaadinServlet {

}
