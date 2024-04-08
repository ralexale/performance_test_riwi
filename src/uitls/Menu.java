package uitls;

import javax.swing.*;

public class Menu<T> {

    private T obj;

    public Menu(T obj) {
        this.obj = obj;
    }

    public void showMenu(String[] options) {
        String option = "";

        do {
            StringBuilder menuBuilder = new StringBuilder();
            for (int i = 0; i < options.length; i++) {
                menuBuilder.append(i + 1).append(". ").append(options[i]).append("\n");
            }
            menuBuilder.append(options.length + 1).append(". Exit\n");
            menuBuilder.append("Choose an option:");

            option = JOptionPane.showInputDialog(null, menuBuilder.toString());

            try {
                int choice = Integer.parseInt(option);
                if (choice >= 1 && choice <= options.length) {
                    performAction(choice); // Llama al método performAction para ejecutar la acción asociada con la opción seleccionada
                } else if (choice == options.length + 1) {
                    // Exit option
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid option! Please choose again.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number.");
            }
        } while (true);
    }

    // Método genérico para ejecutar acciones asociadas con las opciones del menú
    protected void performAction(int choice) {
        // Este método puede ser sobrescrito en las subclases para proporcionar una implementación específica
    }
}
