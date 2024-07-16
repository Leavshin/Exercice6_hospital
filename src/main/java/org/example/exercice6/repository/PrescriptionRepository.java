package org.example.exercice6.repository;

import org.example.exercice6.model.Prescription;

import java.util.List;

public interface PrescriptionRepository {
    void save(Prescription prescription);
    Prescription findById(int id);
    List<Prescription> findByConsultationId(int consultationId);
    void update(Prescription prescription);
    void delete(int id);
}
