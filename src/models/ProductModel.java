package models;

import database.ConfigurationDB;
import entities.Product;
import repostiories.CrudRepository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class ProductModel implements CrudRepository {

    @Override
    public Object save(Object object) {
        Connection objConnection = ConfigurationDB.openConnection();

        Product objProduct = (Product) object;

        try {
            String sql = "INSERT INTO producto (nombre, precio, id_tienda, stock) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStoreId());
            objPrepare.setInt(4, objProduct.getStock());

            objPrepare.execute();

            ResultSet result = objPrepare.getGeneratedKeys();

            while (result.next()) {
                objProduct.setId(result.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Product insertion completed successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error creating product" + e.getMessage());
            throw new RuntimeException(e);
        }
        ConfigurationDB.closeConnection();
        return objProduct;
    }

    @Override
    public void update(Object object) {
        Connection objConnection = ConfigurationDB.openConnection();

        Product objProduct = (Product) object;
        try {
            String sql = "UPDATE producto SET nombre = ?, precio = ?, stock = ? WHERE id_producto = ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStock());
            objPrepare.setInt(4, objProduct.getId());

            int rs = objPrepare.executeUpdate();

            if (rs != 0) {
                JOptionPane.showMessageDialog(null, "Product updating completed successfully");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error modifying product" + e.getMessage());
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();
    }

    @Override
    public void delete(int id) {

        Connection objConnection = ConfigurationDB.openConnection();

        try {
            String sql = "DELETE FROM producto WHERE id_producto = ?;";

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

    public Object findByType(String value, String filterType) {
        Connection objConnection = ConfigurationDB.openConnection();

        Product objProduct = null;

        try {
            String sql = "SELECT * FROM producto WHERE " + filterType + "= ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            if (filterType.equals("nombre")) {
                objPrepare.setString(1, value);
            }

            if (filterType.equals("id_producto")) {
                objPrepare.setInt(1, Integer.parseInt(value));
            }

            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                objProduct = new Product();

                objProduct.setId(resultSet.getInt("id_producto"));
                objProduct.setName(resultSet.getString("nombre"));
                objProduct.setPrice(resultSet.getDouble("precio"));
                objProduct.setStoreId(resultSet.getInt("id_tienda"));
                objProduct.setStock(resultSet.getInt("stock"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error finding products");
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();
        return objProduct;
    }

    public List<Object> findProductsByStore(int id) {
        Connection objConnection = ConfigurationDB.openConnection();

        List<Object> objProducts = new ArrayList<>();

        try {
            String sql = "SELECT producto.* FROM tienda INNER JOIN producto ON tienda.id_tienda = producto.id_tienda WHERE tienda.id_tienda = ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();

                product.setId(resultSet.getInt("id_producto"));
                product.setName(resultSet.getString("nombre"));
                product.setPrice(resultSet.getDouble("precio"));
                product.setStoreId(resultSet.getInt("id_tienda"));

                objProducts.add(product);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error getting products by store");
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();

        return objProducts;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigurationDB.openConnection();
        List<Object> objProducts = new ArrayList<>();

        try {
            String sql = "SELECT * FROM producto;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();

                product.setId(resultSet.getInt("id_producto"));
                product.setName(resultSet.getString("nombre"));
                product.setPrice(resultSet.getDouble("precio"));
                product.setStoreId(resultSet.getInt("id_tienda"));
                product.setStock(resultSet.getInt("stock"));

                objProducts.add(product);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error getting products by store");
            throw new RuntimeException(e);
        }
        ConfigurationDB.closeConnection();
        return objProducts;
    }
}
