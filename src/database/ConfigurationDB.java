package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConfigurationDB {
    public static Connection connection;

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection was closed successfully");
            } catch (SQLException e) {
                System.err.println("Connection failed. " + e.getMessage());
            }
        }
    }

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/filtro";
            String user = "root";
            String password = "Rlwl2023.";

            connection = (Connection) DriverManager.getConnection(url, user, password);

            System.out.println("Connection was successful");
        } catch (ClassNotFoundException e) {
            System.err.println("Error. Driver not found");
        } catch (SQLException e) {
            System.err.println("Connection failed. " + e.getMessage());
        }
        return connection;
    }
}
