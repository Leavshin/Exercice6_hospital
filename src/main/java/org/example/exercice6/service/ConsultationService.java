package org.example.exercice6.service;

import org.example.exercice6.model.Consultation;
import org.example.exercice6.util.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ConsultationService {

    public void save(Consultation consultation) {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(consultation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Consultation findById(int id) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.get(Consultation.class, id);
        }
    }

    public List<Consultation> findAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("from Consultation", Consultation.class).list();
        }
    }

    public List<Consultation> findByDoctorName(String doctorName) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("from Consultation where doctorName like :doctorName", Consultation.class)
                    .setParameter("doctorName", "%" + doctorName + "%").list();
        }
    }

    public void update(Consultation consultation) {
        save(consultation);
    }

    public void delete(Consultation consultation) {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(consultation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
