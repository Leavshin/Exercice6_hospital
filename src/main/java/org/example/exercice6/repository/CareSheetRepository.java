package org.example.exercice6.repository;

import org.example.exercice6.model.CareSheet;

import java.util.List;

public interface CareSheetRepository {
    void save(CareSheet ficheDeSoin);
    CareSheet findById(int id);
    List<CareSheet> findByConsultationId(int consultationId);
    void update(CareSheet ficheDeSoin);
    void delete(int id);
}
