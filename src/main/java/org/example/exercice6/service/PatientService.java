package org.example.exercice6.service;

import org.example.exercice6.model.Patient;
import org.example.exercice6.util.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PatientService {

    public void save(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Patient findById(int id) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.get(Patient.class, id);
        }
    }

    public List<Patient> findAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("from Patient", Patient.class).list();
        }
    }

    public List<Patient> findByName(String name) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("from Patient where lastName like :name", Patient.class)
                    .setParameter("name", "%" + name + "%").list();
        }
    }

    public void update(Patient patient) {
        save(patient);
    }

    public void delete(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
