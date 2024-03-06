package com.tom.dao;

import com.tom.config.HibernateSessionFactoryConfig;
import com.tom.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestInsertHibernate {
    public static void main (String[] args){
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.println("Create new object");
            User newUser = new User("tom1@gmail.com", "tompeo1", "password1");
            System.out.println("Saving user");
            System.out.println(newUser);
            session.save(newUser);
            System.out.println("Generated id: " + newUser.getUserId());
            User user = session.get(User.class, newUser.getUserId());
            System.out.println(user);
            System.out.println("Done");

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
