/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UserDAO;
import DAO.dbConnection;
import model.User;
import view.RegistrationWindow;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;


/**
 *
 * @author gabas
 */
public class RegistrationController {

    private RegistrationWindow view;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public RegistrationController(RegistrationWindow view) {
        this.view = view;
    }
    
    public void saveUser(){
       
        String userLogin = view.getTxt_login().getText();
        String userPassword = view.getTxt_password().getText();    
        String name = view.getTxt_name().getText();
        String gender = view.getCbox_gender().getSelectedItem().toString();        
        String bDateString = view.getTxt_birthDate().getText();
        Date birthDate = null;
        
        try{
            //não aceita datas inválidas
            sdf.setLenient(false);
            birthDate = sdf.parse(bDateString);            
        }catch(ParseException e){
            JOptionPane.showMessageDialog(view, "Data inválida.");
        }        
                
        User user = new User(userLogin, userPassword, name, gender, birthDate);       
        dbConnection conexao = new dbConnection();
        
        try{
            Connection conn = conexao.getConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            JOptionPane.showMessageDialog(view, "Usuário Cadastrado");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão" + e.getMessage());
        }
    }   
}
