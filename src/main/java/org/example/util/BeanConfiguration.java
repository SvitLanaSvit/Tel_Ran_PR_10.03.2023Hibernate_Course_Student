package org.example.util;


import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class BeanConfiguration {

    @Bean("sessionFactory")
    public SessionFactory getSessionFactory(){
       return HibernateUtil.getSessionFactory();
    }
}
