/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UserDAO;
import DAO.DbConnection;
import model.User;
import view.RegistrationWindow;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.SwingUtilities;
import view.LoginWindow;
import view.customDialogs.CustomJDialog;


/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class RegistrationController {

    private RegistrationWindow view;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private boolean isAdm;
    

    public RegistrationController(RegistrationWindow view, boolean isAdm) {
        this.view = view;
        this.isAdm = isAdm;
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
            SwingUtilities.invokeLater(() -> {
                    CustomJDialog.showCustomDialog("Aviso!", "Data inválida");
                });
        }        
                
        User user = new User(userLogin, userPassword, name, gender, birthDate);       
        DbConnection connection = new DbConnection();
        
        try{
            Connection conn = connection.getConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            SwingUtilities.invokeLater(() -> {
                    CustomJDialog.showCustomDialog("Cadastro", "Usuário cadastrado com sucesso!");
                });
            if (this.isAdm) {
            view.setVisible(false);
            } else {
                LoginWindow lw = new LoginWindow();
                lw.setVisible(true);
                view.setVisible(false);
            }
        }catch(SQLException e){
            SwingUtilities.invokeLater(() -> {
                    CustomJDialog.showCustomDialog("ERRO!", "Erro de conexão com o banco de dados.\nEntre em contato com os administradores.");
            });
        }
    }
    
}
