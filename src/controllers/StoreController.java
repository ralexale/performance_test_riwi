package controllers;

import entities.Store;
import models.StoreModel;

import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;

public class StoreController {
    private final StoreModel objStoreModel;

    public StoreController() {
        this.objStoreModel = new StoreModel();
    }

    public String showAllStores() {
        List<Store> stores = this.objStoreModel.findAll();
        String menuList = "";

        for (Object storeTemp : stores) {
            menuList += storeTemp.toString() + "\n";
        }
        return menuList;
    }

    public int selectStoreId() {
        return Integer.parseInt(showInputDialog(this.showAllStores() + "\n" + "Insert the store id"));
    }


}
