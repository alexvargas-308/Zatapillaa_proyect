
package Model;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class CategoriesDao {
    //Instanciar Conexion
    ConexionSQL cn = new ConexionSQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar Categoria
    public boolean registerCategoryQuery(Categories categories){
        String query = "INSERT INTO categories(name,creatd,updated)VALUES(?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1,categories.getName());
            
           
        
    }
}
