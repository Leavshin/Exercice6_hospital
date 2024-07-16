package org.example.exercice6.service;

import org.example.exercice6.model.CareSheet;
import org.example.exercice6.repository.CareSheetRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CareSheetService implements CareSheetRepository {

    private final String url = "jdbc:mysql://localhost:3306/hopital";
    private final String user = "root";
    private final String password = "password";

    @Override
    public void save(CareSheet careSheet) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO fichesDeSoin (content, consultationId) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, careSheet.getContent());
                statement.setInt(2, careSheet.getConsultationId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CareSheet findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM fichesDeSoin WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new CareSheet(
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
    public List<CareSheet> findByConsultationId(int consultationId) {
        List<CareSheet> fichesDeSoin = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM fichesDeSoin WHERE consultationId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, consultationId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        fichesDeSoin.add(new CareSheet(
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
        return fichesDeSoin;
    }

    @Override
    public void update(CareSheet careSheet) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE fichesDeSoin SET content = ?, consultationId = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, careSheet.getContent());
                statement.setInt(2, careSheet.getConsultationId());
                statement.setInt(3, careSheet.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM fichesDeSoin WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
