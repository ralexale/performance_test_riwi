package models;

import repostiories.CrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.ConfigurationDB;
import entities.Client;
import entities.Product;
import entities.Shop;
import entities.Store;

public class ShopModel implements CrudRepository {

    @Override
    public Object save(Object object) {
        Connection objConnection = ConfigurationDB.openConnection();
        Shop objShop = (Shop) object;
        try {
            String sql = "INSERT INTO compra (id_cliente, id_producto, cantidad) VALUES ( ?, ?, ?);";
            PreparedStatement statement = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, objShop.getClientId());
            statement.setInt(2, objShop.getProductId());
            statement.setInt(3, objShop.getQuantity());

            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            while (rs.next()) {
                objShop.setId(rs.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "shop insertion completed successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConfigurationDB.closeConnection();

        return objShop;
    }

    @Override
    public void update(Object object) {

        Connection objConnection = ConfigurationDB.openConnection();
        Shop objShop = (Shop) object;
        try {
            String sql = "UPDATE compra SET id_cliente = ?, id_producto = ?, cantidad = ? WHERE id_compra = ?;";
            PreparedStatement statement = (PreparedStatement) objConnection.prepareStatement(sql);
            statement.setInt(1, objShop.getClientId());
            statement.setInt(2, objShop.getProductId());
            statement.setInt(3, objShop.getQuantity());
            statement.setInt(4, objShop.getId());
            statement.execute();

            if (statement.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Shop updating completed successfully");
            }
        } catch (SQLException e) {
            ConfigurationDB.closeConnection();
            throw new RuntimeException(e);
        }
        ConfigurationDB.closeConnection();

    }

    @Override
    public void delete(int id) {
        Connection objConnection = ConfigurationDB.openConnection();

        try {
            String sql = "DELETE FROM compra WHERE id_compra = ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            boolean rs = objPrepare.execute();

            if (rs) {
                JOptionPane.showMessageDialog(null, "Shop deleting completed successfully");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting shop" + e.getMessage());
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

        Shop objShop = null;

        try {
            String sql = "SELECT * FROM compra WHERE " + filterType + "= ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            if (filterType.equals("id_cliente")) {
                objPrepare.setString(1, value);
            }

            if (filterType.equals("id_producto")) {
                objPrepare.setInt(1, Integer.parseInt(value));
            }

            if (filterType.equals("id_compra")) {
                objPrepare.setInt(1, Integer.parseInt(value));
            }

            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                objShop = new Shop();

                objShop.setId(resultSet.getInt("id_compra"));
                objShop.setClientId(resultSet.getInt("id_cliente"));
                objShop.setProductId(resultSet.getInt("id_producto"));
                objShop.setDateShop(resultSet.getTimestamp("fecha_compra"));
                objShop.setQuantity(resultSet.getInt("cantidad"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error getting shop");
        }

        return objShop;
    }

    public List<Object> findShopsByProduct(int productId) {
        Connection objConnection = ConfigurationDB.openConnection();
        List<Object> shopList = new ArrayList<>();
        try {
            String sql = "SELECT * From compra WHERE id_producto = ?";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            objPrepare.setInt(1, productId);

            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                Shop objShop = new Shop();

                objShop.setId(resultSet.getInt("id_compra"));
                objShop.setClientId(resultSet.getInt("id_cliente"));
                objShop.setProductId(resultSet.getInt("id_producto"));
                objShop.setDateShop(resultSet.getTimestamp("fecha_compra"));
                objShop.setQuantity(resultSet.getInt("cantidad"));

                shopList.add(objShop);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();
        return shopList;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigurationDB.openConnection();
        List<Object> shopList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM compra;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                Shop objShop = new Shop();

                objShop.setId(resultSet.getInt("id_compra"));
                objShop.setClientId(resultSet.getInt("id_cliente"));
                objShop.setProductId(resultSet.getInt("id_producto"));
                objShop.setDateShop(resultSet.getTimestamp("fecha_compra"));
                objShop.setQuantity(resultSet.getInt("cantidad"));


                shopList.add(objShop);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error getting shops");
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();
        return shopList;
    }

    public Shop getInvoice(int productId,int ClientId) {
        Connection objConnection = ConfigurationDB.openConnection();

        Shop objShop = null;

        try {
            String sql = "SELECT producto.nombre as nombre_producto, producto.precio, producto.id_producto, \n" +
                    "cliente.nombre as cliente_nombre , cliente.apellido, cliente.email, cliente.id_cliente,\n" +
                    "tienda.nombre as tienda_nombre, tienda.ubicacion, tienda.id_tienda, compra.cantidad, compra.id_compra From compra \n" +
                    "INNER JOIN producto ON producto.id_producto = compra.id_producto\n" +
                    "INNER JOIN tienda ON tienda.id_tienda = producto.id_tienda\n" +
                    "INNER JOIN cliente ON cliente.id_cliente = compra.id_cliente WHERE producto.id_producto = ? and cliente.id_cliente = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, productId);
            objPrepare.setInt(2, ClientId);

            ResultSet resultSet = objPrepare.executeQuery();

            while (resultSet.next()) {
                objShop = new Shop();
                Client objCLient = new Client();
                Product objProduct = new Product();
                Store objStore = new Store();

                objShop.setQuantity(resultSet.getInt("cantidad"));
                objShop.setId(resultSet.getInt("id_compra"));

                // cliente
                objCLient.setName(resultSet.getString("cliente_nombre"));
                objCLient.setLastname(resultSet.getString("apellido"));
                objCLient.setEmail("email");
                objCLient.setId(resultSet.getInt("id_cliente"));

                // producto
                objProduct.setPrice(resultSet.getDouble("precio"));
                objProduct.setName(resultSet.getString("nombre_producto"));
                objProduct.setId(resultSet.getInt("id_producto"));

                // tienda
                objStore.setName(resultSet.getString("tienda_nombre"));
                objStore.setAddress(resultSet.getString("ubicacion"));
                objStore.setId(resultSet.getInt("id_tienda"));

                objShop.setClient(objCLient);
                objShop.setProduct(objProduct);
                objShop.setStore(objStore);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error getting invoice");
            throw new RuntimeException(e);
        }

        ConfigurationDB.closeConnection();

        return objShop;
    }

}
