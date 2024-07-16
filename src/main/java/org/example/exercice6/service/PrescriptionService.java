package org.example.exercice6.service;

import org.example.exercice6.model.Prescription;
import org.example.exercice6.util.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PrescriptionService {

    public void save(Prescription prescription) {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(prescription);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Prescription findById(int id) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.get(Prescription.class, id);
        }
    }

    public List<Prescription> findAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("from Prescription", Prescription.class).list();
        }
    }

    public void update(Prescription prescription) {
        save(prescription);
    }

    public void delete(Prescription prescription) {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(prescription);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
