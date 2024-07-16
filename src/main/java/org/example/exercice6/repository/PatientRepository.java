package org.example.exercice6.repository;

import org.example.exercice6.model.Patient;

import java.util.List;

public interface PatientRepository {
    void save(Patient patient);
    Patient findById(int id);
    List<Patient> findAll();
    List<Patient> findByName(String name);
    void update(Patient patient);
    void delete(int id);
}
