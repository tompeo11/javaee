package com.tom.config;

import com.tom.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateSessionFactoryConfig {
    private static SessionFactory sessionFactory;

    public static  SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            //create session factory
            try {
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/c2202l_javaee_ebook?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "trancckute11");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                Configuration configuration = new Configuration();
                configuration.setProperties(settings);

                //models
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                return sessionFactory;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  sessionFactory;
    }
}
