package org.example.connections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySqlConnection {
    private static Connection connection;
    // static block
    static {
        String username = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/cours_jdbc";
        try {
            // charger le driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // établir la connexion avec la base de données
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private MySqlConnection() {}
    public static Connection getConnection() {
        return connection;
    }
}
