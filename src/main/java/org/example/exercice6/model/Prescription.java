package org.example.exercice6.model;

public class Prescription {
    private int id;
    private String content;
    private int consultationId;

    // Constructors
    public Prescription() {}

    public Prescription(int id, String content, int consultationId) {
        this.id = id;
        this.content = content;
        this.consultationId = consultationId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }
}
