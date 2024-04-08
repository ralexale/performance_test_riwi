import controllers.ClientController;
import controllers.ProductController;
import controllers.ShopController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClientController objClientController = new ClientController();
        ProductController objProductController = new ProductController();
        ShopController shopController = new ShopController();

        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    --- Store ---
                    1. Products
                    2. Clients
                    3. Shop
                    4. exit

                    choose and option:
                    """);

            switch (option) {
                case "1":
                    objProductController.showMenu();
                    break;
                case "2":
                    objClientController.showMenu();
                    break;
                case "3":
                    shopController.showMenu();
                    break;
            }
        } while (!option.equals("4"));

    }
}