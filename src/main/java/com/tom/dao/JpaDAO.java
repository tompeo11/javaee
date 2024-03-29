package com.tom.dao;

import com.tom.config.HibernateSessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import lombok.extern.java.Log;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Log
public class JpaDAO<T> {
    protected SessionFactory sessionFactory;
    private Class<T> _genericClass;

    public JpaDAO(Class<T> genericClass){
        this.sessionFactory = HibernateSessionFactoryConfig.getSessionFactory();
        this._genericClass = genericClass;
    }

    public T create(T genericObjectToInsert){
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            session.save(genericObjectToInsert);
            session.flush();
            session.refresh(genericObjectToInsert);

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
        return genericObjectToInsert;
    }

    public T getById(Object genericObjectId){
        T result = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            Object queryObject = session.get(_genericClass, (Serializable) genericObjectId);
            result = _genericClass.cast(queryObject);

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }

        return result;
    }

    public T update(T update){
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            session.update(update);

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
        return update;
    }

    public void deleteById(Object genericObjectId){
        Transaction transaction = null;
        Object queryObject = null;

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            queryObject = session.get(_genericClass, (Serializable) genericObjectId);

            if (queryObject != null){
                session.delete(queryObject);
            }

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    //HIBERNATE Criteria Queries
    public List<T> getAll(){
        Transaction transaction = null;
        List<T> objectList = null;

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(_genericClass);
            Root<T> root = criteriaQuery.from(_genericClass);
            criteriaQuery.select(root);

            Query<T> query = session.createQuery(criteriaQuery);
            objectList = query.getResultList();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
        return objectList;
    }

    public long getTotalRecord(){
        Transaction transaction = null;
        long totalRecord = 0;

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<T> root = criteriaQuery.from(_genericClass);
            criteriaQuery.select(builder.count(root));

            Query<Long> query = session.createQuery(criteriaQuery);
            totalRecord = query.getSingleResult();


            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
        return totalRecord;
    }

    public List<T> getAllWithHQL(String hql){
        Transaction transaction = null;
        List<T> objectList = null;

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<T> hqlQuery = session.createNamedQuery(hql);
            objectList = hqlQuery.getResultList();


            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
        return objectList;
    }

    public long getTotalRecordWithHQL(String hql){
        Transaction transaction = null;
        long totalRecord = 0;

        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Long> hqlQuery = session.createNamedQuery(hql);
            totalRecord = hqlQuery.getSingleResult();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }

            e.printStackTrace();
        }
        return totalRecord;
    }

    public T findOneWithHQL(String hql, String paramName, Object paramValue){
        T t = null;
        try (Session session = sessionFactory.openSession()){
            t = session.createNamedQuery(hql, _genericClass).setParameter(paramName, paramValue).getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    public List<T> getByNamedQueryWithParams(String hql, Map<String, Object> params) {
        Transaction transaction = null;
        List<T> objectList = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<T> hqlQuery = session.createNamedQuery(hql);

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                hqlQuery.setParameter(entry.getKey(), entry.getValue());
            }

            objectList = hqlQuery.getResultList();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return objectList;
    }
}
