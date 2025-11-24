
package Model;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class ProductsDao {
     //Instanciar Conexion
    ConexionSQL cn = new ConexionSQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar productos
    public boolean registerProductsQuery(Products product){
        String query = "INSERT INTO products(code,name,description,unit_price,created,updated,"
                + "category_id VALUES(?,?,?,?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        conn = cn.getConnection();
        pst = conn.prepareStatement(query);
        
        pst.setInt(1,product.getCode());
        pst.setString(2,product.getName());
        pst.setString(3,product.getDescription());
        pst.setFloat(4,product.getUnit_price());
        pst.setTimestamp(5,dateTime);
        pst.setTimestamp(6, dateTime);
        pst.setInt();
    }7,product.getCategory_name();
}
