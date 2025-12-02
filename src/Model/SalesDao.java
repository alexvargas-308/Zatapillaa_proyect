
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

public class SalesDao {
    //Instanciar Conexion
    ConnectionSQL cn = new ConnectionSQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar venta
    public boolean regsterSaleQuery(int customer_id, int employee_id, float total){
        String query = "INSERT INTO sales (customer_id, employee_id, total, sale_date)"
                + "VALUES(?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, customer_id);
            pst.setInt(2, employee_id);
            pst.setFloat(3, total);
            pst.setTimestamp(4, dateTime);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    //Registrar detalles de venta //Sale_details
    public boolean registerSaleDetailsQuery(int product_id, float sale_id, int
            sale_quantity, float sale_price, float sale_subtotal){
        String query = "INSERT INTO sale_details(product_id, sale_id, sale_quantity, sale_price,"
                + "sale_subtotal)VALUES(?,?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, product_id);
            pst.setFloat(2, sale_id);
            pst.setInt(3, sale_quantity);
            pst.setFloat(4, sale_id);
            pst.setFloat(5, sale_subtotal);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public int SaleID(){
       int id = 0;
       String query = "SELECT MAX(id)AS id FROM sales";
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
    
    public List listAllSalesQuery(){
        List<Sales>list_sales = new ArrayList();
        String query = "SELECT s.id AS invoice, c.full_name AS customer, e.full_name as employee,"
                + "s.total, s.sale_date from sales s inner join customers c on s.customer_id = c.id inner join"
                + "employees e on s.employee_id = e.id order by s.id ASC";
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                Sales sale = new Sales();
                sale.setId(rs.getInt("invoice"));
                sale.setCustomer_name(rs.getString("customer"));
                sale.setEmployee_name(rs.getString("employee"));
                sale.setTotal_to_pay(rs.getFloat("total"));
                sale.setSale_date(rs.getString("sale_date"));
                list_sales.add(sale);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return list_sales;
    }
}
