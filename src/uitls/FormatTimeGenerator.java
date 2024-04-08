package uitls;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Date;

public class FormatTimeGenerator {

    Date showDatePickerDialog() {
        // Crear un JSpinner para seleccionar la fecha
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy/MM/dd"));

        // Crear el panel que contiene el JSpinner
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(spinner, BorderLayout.CENTER);

        // Mostrar el JOptionPane con el JSpinner
        int result = JOptionPane.showConfirmDialog(null, panel, "Select a date in format yyyy/mm/dd",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Obtener la fecha seleccionadaa
            Date selectedDate = (Date) spinner.getValue();
            return selectedDate;
        }
        return null; // Retornar null si el usuario cancela la selección
    }

    LocalTime showTimePickerDialog() {
        // Crear un JSpinner para seleccionar la hora
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);

        // Crear el panel que contiene el JSpinner
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(spinner, BorderLayout.CENTER);

        // Mostrar el JOptionPane con el JSpinner
        int result = JOptionPane.showConfirmDialog(null, panel, "Select a Time", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Obtener la hora seleccionada
            java.util.Date selectedTime = (java.util.Date) spinner.getValue();
            return LocalTime.of(selectedTime.getHours(), selectedTime.getMinutes());
        }
        return null; // Retornar null si el usuario cancela la selección
    }

}
