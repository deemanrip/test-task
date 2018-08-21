package com.haulmont.testtask.config;

import com.vaadin.spring.server.SpringVaadinServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebContextInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));
        registerServlet(servletContext);
    }

    private void registerServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "vaadin", SpringVaadinServlet.class);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }
}