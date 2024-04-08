package controllers;

import entities.Client;
import models.ClientModel;

import javax.swing.*;

import static javax.swing.JOptionPane.showInputDialog;

import java.util.List;

public class ClientController {
    private ClientModel objClientModel;

    public ClientController() {
        this.objClientModel = new ClientModel();
    }

    // Métodos específicos del controlador de clientes
    public void createClient() {
        Client objClient = new Client();

        String name = showInputDialog("Insert client name: ");
        String lastname = showInputDialog("Insert client lastname: ");
        String email = showInputDialog("Insert client email: ");

        objClient.setName(name);
        objClient.setLastname(lastname);
        objClient.setEmail(email);

        this.objClientModel.save(objClient);
    }

    public String findAll() {
        List<Object> clients = this.objClientModel.findAll();
        String menuList = "";

        for (Object client : clients) {
            menuList += client.toString() + "\n";
        }
        return menuList;
    }

    public void deleteClient(int id) {
        this.objClientModel.delete(id);
    }

    public void updateClient(int id) {
        Client objClient = (Client) this.objClientModel.findByType(Integer.toString(id), "id_cliente");

        String name = showInputDialog("Insert client name: ", objClient.getName());
        String lastname = showInputDialog("Insert client lastname: ", objClient.getLastname());
        String email = showInputDialog("Insert client email: ", objClient.getEmail());

        objClient.setName(name);
        objClient.setLastname(lastname);
        objClient.setEmail(email);

        this.objClientModel.update(objClient);
    }

    public void findClientByName(String name) {
        Client objClient = (Client) this.objClientModel.findByType(name, "nombre");

        JOptionPane.showMessageDialog(null, objClient.toString());
    };

    public int selectClientId() {
        return Integer.parseInt(showInputDialog(this.findAll() + "\n" + "Insert the client id"));
    }

    public String selectClientName() {
        return showInputDialog(this.findAll() + "\n" + "Insert the client name");
    }

    public void showMenu() {
        String option = "";

        do {
            option = JOptionPane.showInputDialog(null, """
                    1. Create a new Client
                    2. Show all Clients
                    3. Delete an Client
                    4. Update an Client
                    5. show Client by name
                    6. Exit
                    choose and option:
                    """);

            switch (option) {
                case "1":
                    this.createClient();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, this.findAll());
                    break;
                case "3":
                    this.deleteClient(selectClientId());
                    break;
                case "4":
                    this.updateClient(selectClientId());
                    break;
                case "5":
                    this.findClientByName(selectClientName());
                    break;

            }
        } while (!option.equals("6"));
    }

}
