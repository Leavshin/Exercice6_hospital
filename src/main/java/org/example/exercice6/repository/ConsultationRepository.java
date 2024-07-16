package org.example.exercice6.repository;

import org.example.exercice6.model.Consultation;

import java.util.List;

public interface ConsultationRepository {
    void save(Consultation consultation);
    Consultation findById(int id);
    List<Consultation> findByPatientId(int patientId);
    void update(Consultation consultation);
    void delete(int id);
}
