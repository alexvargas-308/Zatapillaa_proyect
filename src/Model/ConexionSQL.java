package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=MiBase";
        String user = "proyect_308";
        String password = "123456";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a SQL Server correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
