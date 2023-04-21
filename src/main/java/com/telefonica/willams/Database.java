package com.telefonica.willams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    public void saveResponse(DataCLP dataCLP) {
        String insertSQL = "INSERT INTO data_clp (bit_set, sensor1, sensor2) VALUES (?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        ) {
            preparedStatement.setByte(1, dataCLP.bitSet);
            preparedStatement.setInt(2, dataCLP.sensor1);
            preparedStatement.setFloat(3, dataCLP.sensor2);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Resposta salva no banco de dados.");
            } else {
                System.out.println("Erro ao salvar a resposta no banco de dados.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
