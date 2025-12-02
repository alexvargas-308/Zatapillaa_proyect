package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
    private String server_name = "DESKTOP-I5A6RPI";
    private String port = "1433";
    private String database_name = "Zapatillas";
    private String user = "proyect_308";
    private String password = "123456";
    private String url = "jdbc:sqlserver://" + server_name + ":" + port + ";databaseName=" + database_name + ";trustServerCertificate=true;";
         
    Connection conn = null;
    public Connection getConnection() throws SQLException{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url,user,password);
        }catch(ClassNotFoundException e){
            
            System.err.println("Ha ocurrido un SQLExeption:"+e.getMessage());
            
        }
        
        return conn;
    }
}
