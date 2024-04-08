package controllers;

import entities.Product;
import entities.Shop;
import models.ProductModel;
import models.ShopModel;

import javax.swing.*;
import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;

public class ShopController {
    private final ShopModel objShopModel;

    private final ProductModel objProductModel;
    private final ClientController objClientController;
    private final ProductController objProductController;

    public ShopController() {
        this.objShopModel = new ShopModel();
        this.objClientController = new ClientController();
        this.objProductController = new ProductController();
        this.objProductModel = new ProductModel();
    }

    public void createShop() {
        Shop objShop = new Shop();

        int clientId = this.objClientController.selectClientId();
        int productId = this.objProductController.selectProductId();
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Insert the product quantity"));

        objShop.setClientId(clientId);
        objShop.setProductId(productId);
        objShop.setQuantity(quantity);

        updateStockProduct(productId, quantity);

        this.objShopModel.save(objShop);
        showInvoice(productId, clientId);
    }

    public String findAll() {
        List<Object> shops = this.objShopModel.findAll();
        String menuList = "";

        for (Object shopTemp : shops) {
            menuList += shopTemp.toString() + "\n";
        }
        return menuList;
    }

    public void deleteShop(int id) {
        this.objShopModel.delete(id);
    }

    public void updateShop(int id) {
        Shop objShop = (Shop) this.objShopModel.findByType(Integer.toString(id), "id_compra");

        int clientId = objClientController.selectClientId();
        int productId = objProductController.selectProductId();
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Insert the product quantity"));

        objShop.setClientId(clientId);
        objShop.setProductId(productId);
        objShop.setQuantity(quantity);

        updateStockProduct(productId, quantity);

        this.objShopModel.update(objShop);
    }

    public int selectShopId() {
        return Integer.parseInt(showInputDialog(this.findAll() + "\n" + "Insert the client id"));
    }

    public String ShowShopsForProduct(int productId) {

        List<Object> products = this.objShopModel.findShopsByProduct(productId);
        String menuList = "";

        for (Object productTemp : products) {
            menuList += productTemp.toString() + "\n";
        }

        return menuList;
    }

    public void updateStockProduct(int productId, int quantity) {
        Product objProduct = (Product) this.objProductModel.findByType(Integer.toString(productId), "id_producto");

        int newStock = objProduct.getStock() - quantity;

        if (newStock <= 0) {
            JOptionPane.showMessageDialog(null, "not more stock for that product");
            throw new RuntimeException("no more product");
        }

        objProduct.setStock(newStock);

        this.objProductModel.update(objProduct);
    }

    public void showInvoice(int productId,int clientId ){
        Shop objShop = this.objShopModel.getInvoice(productId,clientId);

        String product = "Product: "+ objShop.getProduct().getName() + " " + objShop.getProduct().getPrice();
        String client = "Client: "+ objShop.getClient().getName()+ " " + objShop.getClient().getName();
        String store = "Store: "+ objShop.getStore().getName() + " " + objShop.getStore().getAddress();

        double price = objShop.getProduct().getPrice() * objShop.getQuantity();
        double iva = price * 0.19;
        double totalPrice = price + iva;

        String invoiceText = "---Invoice---\n" + client + "\n" + product + "\n" + store + "\n" + "iva del producto: " + iva + "\n" + totalPrice;

        JOptionPane.showMessageDialog(null, invoiceText);
    }

    public void showMenu() {
        String option = "";

        do {
            option = JOptionPane.showInputDialog(null, """
                    1. Create a new Shop
                    2. Show all Shops
                    3. Delete an Shop
                    4. Update an Shop
                    5. show all shops for a product
                    6. Exit
                    choose and option:
                    """);

            switch (option) {
                case "1":
                    this.createShop();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, this.findAll());
                    break;
                case "3":
                    this.deleteShop(selectShopId());
                    break;
                case "4":
                    this.updateShop(selectShopId());
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, this.ShowShopsForProduct(selectShopId()));
                    break;
            }

        } while (!option.equals("6"));
    }
}
