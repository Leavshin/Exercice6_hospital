package org.example.exercice6.service;

import org.example.exercice6.model.Consultation;
import org.example.exercice6.repository.ConsultationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationService implements ConsultationRepository {

    private final String url = "jdbc:mysql://localhost:3306/hopital";
    private final String user = "root";
    private final String password = "password";

    @Override
    public void save(Consultation consultation) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO consultations (date, doctorName, patientId) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, Date.valueOf(consultation.getDate()));
                statement.setString(2, consultation.getDoctorName());
                statement.setInt(3, consultation.getPatientId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Consultation findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM consultations WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Consultation(
                                resultSet.getInt("id"),
                                resultSet.getDate("date").toLocalDate(),
                                resultSet.getString("doctorName"),
                                resultSet.getInt("patientId")
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
    public List<Consultation> findByPatientId(int patientId) {
        List<Consultation> consultations = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM consultations WHERE patientId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, patientId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        consultations.add(new Consultation(
                                resultSet.getInt("id"),
                                resultSet.getDate("date").toLocalDate(),
                                resultSet.getString("doctorName"),
                                resultSet.getInt("patientId")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void update(Consultation consultation) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE consultations SET date = ?, doctorName = ?, patientId = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, Date.valueOf(consultation.getDate()));
                statement.setString(2, consultation.getDoctorName());
                statement.setInt(3, consultation.getPatientId());
                statement.setInt(4, consultation.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM consultations WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
