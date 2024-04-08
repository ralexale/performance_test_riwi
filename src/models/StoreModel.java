package models;

import database.ConfigurationDB;

import entities.Store;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreModel {

    public List<Store> findAll() {
        Connection objConnection = ConfigurationDB.openConnection();
        List<Store> objStores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tienda;";

            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);

            ResultSet resultSet = objPreparedStatement.executeQuery();

            while (resultSet.next()) {
                Store store = new Store();

                store.setId(resultSet.getInt("id_tienda"));
                store.setName(resultSet.getString("nombre"));
                store.setAddress(resultSet.getString("ubicacion"));

                objStores.add(store);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error getting the store" + e.getMessage());
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();

        return objStores;
    }

}
