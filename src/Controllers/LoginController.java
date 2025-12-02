
package Controllers;

import Model.Employees;
import Model.EmployeesDao;
import Views.Loginview;
import Views.Systemview;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener{
    //Encapsular variables
    private Employees employee;
    private EmployeesDao employees_dao;
    private Loginview login_view;

    public LoginController(Employees employee, EmployeesDao employees_dao, Loginview login_view) {
        this.employee = employee;
        this.employees_dao = employees_dao;
        this.login_view = login_view;
        this.login_view.btn_enter.addActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Obtener los datos de la vista
        String user = login_view.txt_userName.getText().trim();
        String pass = String.valueOf(login_view.txt_Password.getPassword());
        if(e.getSource() == login_view.btn_enter){
            if(!user.equals("")|| !pass.equals("")){
                employee = employees_dao.loginQuery(user, pass);
                if(employee.getUsername()!=null){
                    if(employee.getRol().equals("Administrador")){
                        Systemview admin = new Systemview();
                        admin.setVisible(true);
                    }else{
                        Systemview aux = new Systemview();
                        aux.setVisible(true);
                    }
                    this.login_view.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario o Password Incorrecto");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Los Campos o uno de campos esta vacio");
            }
        }
        
    }
    
}