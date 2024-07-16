package org.example.exercice6.model;

import java.time.LocalDate;
import java.util.List;

public class Consultation {
    private int id;
    private LocalDate date;
    private String doctorName;
    private int patientId;
    private List<Prescription> prescriptions;
    private List<CareSheet> careSheets;

    // Constructors
    public Consultation() {}

    public Consultation(int id, LocalDate date, String doctorName, int patientId) {
        this.id = id;
        this.date = date;
        this.doctorName = doctorName;
        this.patientId = patientId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<CareSheet> getCareSheets() {
        return careSheets;
    }

    public void setCareSheets(List<CareSheet> careSheets) {
        this.careSheets = careSheets;
    }
}
