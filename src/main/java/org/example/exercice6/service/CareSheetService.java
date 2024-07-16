package org.example.exercice6.service;

import org.example.exercice6.model.CareSheet;
import org.example.exercice6.util.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CareSheetService {

    public void save(CareSheet careSheet) {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(careSheet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public CareSheet findById(int id) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.get(CareSheet.class, id);
        }
    }

    public List<CareSheet> findAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("from CareSheet", CareSheet.class).list();
        }
    }

    public void update(CareSheet careSheet) {
        save(careSheet);
    }

    public void delete(CareSheet careSheet) {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(careSheet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
