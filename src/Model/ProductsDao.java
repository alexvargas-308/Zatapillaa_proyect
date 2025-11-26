
package Model;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductsDao{
    //Instanciar Conexion
    ConexionSQL cn = new ConexionSQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar productos
    public boolean registerProductsQuery(Products product){
        String query = "INSERT INTO products (code, name, description, unit_price, created, updates,"
                + "category_id VALUES (?,?,?,?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setFloat(4, product.getUnit_price());
            pst.setTimestamp(5,dateTime);
            pst.setTimestamp(6, dateTime);
            pst.setInt(7, product.getCategory_id());
            
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al registrar producto"+ e);
            return false;
        }
    }
    
    //Listar Produtos
    public List listProductsQuery(String value){
        List<Products> list_products = new ArrayList();
        String query = "select pro.*, ca.name AS category_name FROM products pro,"
                + " categories ca WHERE pro.category_id = ca.id";
        String query_search_products = "select pro.*, ca.name AS category_name FROM"
                + " products pro inner join categories ca on pro.category_id = ca.id WHERE pro.name"
                + "like '%"+value+"%'";
        try{
            conn = cn.getConnection();
            if(value.equalsIgnoreCase("")){
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            }else{
                pst = conn.prepareStatement(query_search_products);
                rs = pst.executeQuery();
            }
            while(rs.next()){
                Products product = new Products();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getInt("code"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnit_price(rs.getFloat("unit_price"));
                product.setProduct_quantity(rs.getInt("product_quantity"));
                product.setCategory_name(rs.getString("category_name"));
                list_products.add(product);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return list_products;
    }
    
    //Modificar Producto
    public boolean updateProductQuery(Products product){
        String query = "UPDATE produts SET code = ?, name = ?, description = ?,"
                + "unit_price = ?, category_id = ? WHERE id = ?";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1,product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setFloat(4, product.getUnit_price());
            pst.setTimestamp(5, dateTime);
            
            pst.setInt(6,product.getCategory_id());
            pst.setInt(7,product.getId());
            
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al modificar producto"+ e);
            return false;
        }
    }
    
    //Eliminar Product
    public boolean deleteProductsQuery(int id){
        String query = "DELETE FROM products WHERE id = "+id;
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se puede eliminar el"
                    + "producto si tiene relacion con ota tabla"+e);
            return false;
        }
    }
    
    //Buscar producto
    public Products searchProduct(int id){
        String query ="select pro.*,ca.name as category_name from products pro inner join"
                + "categories ca on pro.category_id = ca.id where pro.ed = ?";
        Products product = new Products();
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                product.setId(rs.getInt("id"));
                product.setCode(rs.getInt("code"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnit_price(rs.getFloat("unit_price"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setCategory_name(rs.getString("category_name"));
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }
    
    //Buscar producto por codigo
    public Products searchCode(int code){
        String query = "select pro.id, pro.name from products pro where pro.code = ?";
        Products product = new Products();
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1, code);
            rs = pst.executeQuery();
            
            pst.setInt(1, code);
            rs = pst.executeQuery();
            if(rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("nombre"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return product;
    }
    
    //Traer la cantidad de productos
    public Products searchID(int id){
        String query ="SELECT pro.product_quantity from products pro where pro.id = ?";
        Products product = new Products();
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()){
                product.setProduct_quantity(rs.getInt("product quantity"));
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }
    
    //Actualizar Stock
    public boolean updateStockQuery(int amount,int product_id ){
        String query = "update products set product_quantity = ? where id = ?";
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1,amount);
            pst.setInt(2, product_id);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
