package org.example.exercice6.service;

import org.example.exercice6.model.Prescription;
import org.example.exercice6.repository.PrescriptionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionService implements PrescriptionRepository {

    private final String url = "jdbc:mysql://localhost:3306/hopital";
    private final String user = "root";
    private final String password = "password";

    @Override
    public void save(Prescription prescription) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO prescriptions (content, consultationId) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, prescription.getContent());
                statement.setInt(2, prescription.getConsultationId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Prescription findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM prescriptions WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Prescription(
                                resultSet.getInt("id"),
                                resultSet.getString("content"),
                                resultSet.getInt("consultationId")
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
    public List<Prescription> findByConsultationId(int consultationId) {
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM prescriptions WHERE consultationId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, consultationId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        prescriptions.add(new Prescription(
                                resultSet.getInt("id"),
                                resultSet.getString("content"),
                                resultSet.getInt("consultationId")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }

    @Override
    public void update(Prescription prescription) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE prescriptions SET content = ?, consultationId = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, prescription.getContent());
                statement.setInt(2, prescription.getConsultationId());
                statement.setInt(3, prescription.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM prescriptions WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
