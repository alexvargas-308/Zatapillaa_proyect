
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

public class PurchasesDao {
    ConexionSQL cn = new ConexionSQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar Compra
    public boolean registerPurchaseQuery(int supplier_id,int employee_id,
            float total){
        String query = "INSERT INTO purchases(supplier_id, employee_id, total, created)"
                + "VALUES(?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, supplier_id);
            pst.setInt(2, employee_id);
            pst.setFloat(3, total);
            pst.setTimestamp(4,dateTime);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al ingresar la compra"+e);
            return false;
        }
    }
    //Registrar Detalles de la compra
    public boolean registerPurchaseDetailQuery(int purchase_id, float purchase_price,
            int purchase_amount, float purchase_subtotal, int product_id){
        String query = "INSERT INTO purchases_details(purchase_id, purchase_price,"
                + "purchase_amount, purchase_subtotal, purchase_id)"
                + "VALUES(?,?,?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, purchase_id);
            pst.setFloat(2, purchase_price);
            pst.setInt(3, purchase_amount);
            pst.setFloat(4, purchase_subtotal);
            pst.setTimestamp(5, dateTime);
            pst.setInt(6, product_id);
            
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al registrar detalles de la"
                    + "compra"+e);
            return false;
        }
    }
    //Obtener ID de la compra
    public int purchaseId(){
        int id = 0;
        String query = "select MAX(id)AS id FROM purchases";
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return id;
    }
    //Listar Todas las compras realizadas
    public List listAllPurchasesQuery(){
        List<Purchases>list_purchases = new ArrayList();
        String query = "select pu.*, su.name as supplier_name from purchases pu,"
                + "suppliers su where pu.supplier_id = su.id order by pu.id asc";
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Purchases purchase = new Purchases();
                purchase.setId(rs.getInt("id"));
                purchase.setSupplier_name_product(rs.getString("supplier_name"));
                purchase.setTotal(rs.getFloat("total"));
                purchase.setCreated(rs.getString("created"));
                list_purchases.add(purchase);
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list_purchases;
    }
    //Listar compras para imprimir factura
    public List listPurchaseDetailQuery(int id){
        List<Purchases>list_purchases = new ArrayList();
        
        String query = "select pu.created, pude.purchase_price, pude.purchase_amount,"
                + " pude.purchase_subtotal, su.name as supplier_name, pro.name as"
                + " product_name, em.full_name from purchases pu inner join"
                + " purchase_details pude on pu.id = pude.purchase_id inner join"
                + " products pro on pude.product_id = pro.id inner join suppliers su on"
                + " pu.supplier_id = su.id inner join employees em on"
                + " pu.employee_id = em.id where pu.id = ?";
    }
    
}
