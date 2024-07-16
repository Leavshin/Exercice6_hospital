package org.example.exercice6.service;

import org.example.exercice6.model.Patient;
import org.example.exercice6.repository.PatientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientService implements PatientRepository {

    private final String url = "jdbc:mysql://localhost:3306/hopital";
    private final String user = "root";
    private final String password = "password";

    @Override
    public void save(Patient patient) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO patients (firstName, lastName, birthDate, photoPath, phone) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, patient.getFirstName());
                statement.setString(2, patient.getLastName());
                statement.setDate(3, Date.valueOf(patient.getBirthDate()));
                statement.setString(4, patient.getPhotoPath());
                statement.setString(5, patient.getPhone());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM patients WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Patient(
                                resultSet.getInt("id"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getDate("birthDate").toLocalDate(),
                                resultSet.getString("photoPath"),
                                resultSet.getString("phone"),
                                null // consultations à charger séparément
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM patients";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        patients.add(new Patient(
                                resultSet.getInt("id"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getDate("birthDate").toLocalDate(),
                                resultSet.getString("photoPath"),
                                resultSet.getString("phone"),
                                null // consultations à charger séparément
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> findByName(String name) {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM patients WHERE lastName LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + name + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        patients.add(new Patient(
                                resultSet.getInt("id"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getDate("birthDate").toLocalDate(),
                                resultSet.getString("photoPath"),
                                resultSet.getString("phone"),
                                null // consultations à charger séparément
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void update(Patient patient) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE patients SET firstName = ?, lastName = ?, birthDate = ?, photoPath = ?, phone = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, patient.getFirstName());
                statement.setString(2, patient.getLastName());
                statement.setDate(3, Date.valueOf(patient.getBirthDate()));
                statement.setString(4, patient.getPhotoPath());
                statement.setString(5, patient.getPhone());
                statement.setInt(6, patient.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM patients WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
