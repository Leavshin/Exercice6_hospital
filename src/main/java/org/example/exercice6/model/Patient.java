package org.example.exercice6.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String photoPath;
    private String phone;
    private List<Consultation> consultations;

    // Constructors
    public Patient() {}

    public Patient(int id, String firstName, String lastName, LocalDate birthDate, String photoPath, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.photoPath = photoPath;
        this.phone = phone;
    }

    public Patient(int id, String firstName, String lastName, LocalDate birthDate, String photoPath, String phone, Object o) {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    // Méthode setPhoto : Gère l'upload de la photo du patient
    public void setPhoto(InputStream photoInputStream, String photoFileName) {
        try {
            String fileName = generateFileName(photoFileName);
            String uploadDir = "path/to/your/upload/directory"; // Chemin où vous souhaitez stocker les photos
            Path uploadPath = Path.of(uploadDir);

            // Vérifiez si le répertoire d'upload existe, sinon créez-le
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Déplacez le fichier téléchargé vers le répertoire d'upload
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(photoInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            // Mettez à jour le chemin de la photo dans l'objet Patient
            this.photoPath = fileName;
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins
        }
    }

    // Méthode utilitaire pour générer un nom de fichier unique
    private String generateFileName(String part) {
        String extension = part.substring(part.lastIndexOf('.'));
        return System.currentTimeMillis() + extension;
    }
}
