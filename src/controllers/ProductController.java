package controllers;

import entities.Product;
import models.ProductModel;

import javax.swing.*;

import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;

public class ProductController {
    private final ProductModel objProductModel;
    private final StoreController objStoreController;

    public ProductController() {
        this.objProductModel = new ProductModel();
        this.objStoreController = new StoreController();
    }


    public void saveProduct() {
        Product objProduct = new Product();

        String name = showInputDialog("Insert product name: ");
        double price = Double.parseDouble(showInputDialog("Insert product price: "));
        int stock = Integer.parseInt(showInputDialog("Insert product stock: "));
        int storeId = objStoreController.selectStoreId();

        objProduct.setName(name);
        objProduct.setPrice(price);
        objProduct.setStock(stock);
        objProduct.setStoreId(storeId);

        this.objProductModel.save(objProduct);
    }

    ;

    public void updateProduct(int id) {

        Product objProduct = (Product) this.objProductModel.findByType(Integer.toString(id), "id_producto");

        String name = showInputDialog("Insert product name: ", objProduct.getName());
        double price = Double.parseDouble(showInputDialog("Insert product price: ", objProduct.getPrice()));
        int stock = Integer.parseInt(showInputDialog("Insert product stock: ", objProduct.getStock()));



        objProduct.setName(name);
        objProduct.setPrice(price);
        objProduct.setStock(stock);

        this.objProductModel.update(objProduct);
    }

    ;

    public void deleteProduct(int id) {
        this.objProductModel.delete(id);
    }

    ;

    public void findProductByName(String name) {
        Product objProduct = (Product) this.objProductModel.findByType(name, "nombre");

        JOptionPane.showMessageDialog(null, objProduct.toString());
    }

    ;

    public String findAll() {
        List<Object> products = this.objProductModel.findAll();
        String menuList = "";

        for (Object productTemp : products) {
            menuList += productTemp.toString() + "\n";
        }
        return menuList;
    }

    ;

    public int selectProductId() {
        return Integer.parseInt(showInputDialog(this.findAll() + "\n" + "Insert the product id"));
    }

    public String selectProductName() {
        return showInputDialog(this.findAll() + "\n" + "Insert the product name");
    }


    public String showProductsByStore(int storeId) {

        List<Object> products = this.objProductModel.findProductsByStore(storeId);
        String menuList = "";

        for (Object productTemp : products) {
            menuList += productTemp.toString() + "\n";
        }
        return menuList;
    }




    public void showMenu() {
        String option = "";

        do {
            option = showInputDialog(null, """
                    1. Create a new Product
                    2. Show all Products
                    3. Delete an Product
                    4. Update an Products
                    5. show Products by name
                    6. show products by store
                    7. Exit
                    choose and option:
                    """);

            switch (option) {
                case "1":
                    this.saveProduct();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, this.findAll());
                    break;
                case "3":
                    this.deleteProduct(selectProductId());
                    break;
                case "4":
                    this.updateProduct(selectProductId());
                    break;
                case "5":
                    findProductByName(selectProductName());
                    break;
                case "6":
                    int storeId = objStoreController.selectStoreId();
                    JOptionPane.showMessageDialog(null, this.showProductsByStore(storeId));
                    break;


            }
        } while (!option.equals("7"));
    }
}
