package models;

import database.ConfigurationDB;
import entities.Client;

import repostiories.CrudRepository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientModel implements CrudRepository {

    @Override
    public Object save(Object object) {
        Connection objConnection = ConfigurationDB.openConnection();

        Client objClient = (Client) object;

        try {
            String sql = "INSERT INTO cliente (nombre, apellido, email) VALUES (?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objClient.getName());
            objPrepare.setString(2, objClient.getLastname());
            objPrepare.setString(3, objClient.getEmail());
            objPrepare.execute();

            ResultSet result = objPrepare.getGeneratedKeys();

            while (result.next()) {
                objClient.setId(result.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Client insertion completed successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error creating Client" + e.getMessage());
            throw new RuntimeException(e);
        }
        ConfigurationDB.closeConnection();
        return object;
    }

    @Override
    public void update(Object object) {
        Connection objConnection = ConfigurationDB.openConnection();

        Client objClient = (Client) object;

        try {
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ? WHERE id_cliente = ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            objPrepare.setString(1, objClient.getName());
            objPrepare.setString(2, objClient.getLastname());
            objPrepare.setString(3, objClient.getEmail());
            objPrepare.setInt(4, objClient.getId());

            int rs = objPrepare.executeUpdate();

            if (rs != 0) {
                JOptionPane.showMessageDialog(null, "Client updating completed successfully");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error modifying Client" + e.getMessage());
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();
    }

    @Override
    public void delete(int id) {
        Connection objConnection = ConfigurationDB.openConnection();

        try {
            String sql = "DELETE FROM cliente WHERE id_cliente = ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            boolean rs = objPrepare.execute();

            if (rs) {
                JOptionPane.showMessageDialog(null, "Product deleting completed successfully");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConfigurationDB.closeConnection();
    }

    @Override
    public Object find(String value) {
        return null;
    }

    @Override
    public Object findByType(String value, String filterType) {
        Connection objConnection = ConfigurationDB.openConnection();

        Client objClient = null;

        try {
            String sql = "SELECT * FROM cliente WHERE " + filterType + "= ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            if (filterType.equals("nombre")) {
                objPrepare.setString(1, value);
            }

            if (filterType.equals("id_cliente")) {
                objPrepare.setInt(1, Integer.parseInt(value));
            }

            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                objClient = new Client();

                objClient.setId(resultSet.getInt("id_cliente"));
                objClient.setName(resultSet.getString("nombre"));
                objClient.setLastname(resultSet.getString("apellido"));
                objClient.setEmail(resultSet.getString("email"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error finding client");
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();
        return objClient;

    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigurationDB.openConnection();
        List<Object> clients = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cliente;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                Client objClient = new Client();

                objClient.setId(resultSet.getInt("id_cliente"));
                objClient.setName(resultSet.getString("nombre"));
                objClient.setLastname(resultSet.getString("apellido"));
                objClient.setEmail(resultSet.getString("email"));

                clients.add(objClient);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error getting products by store");
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();

        return clients;
    }
}
