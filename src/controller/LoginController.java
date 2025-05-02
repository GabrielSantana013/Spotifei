/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.customDialogs.CustomJDialog;  // import custom dialog pop-up
import DAO.DbConnection;
import DAO.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.*;
import model.User;
import view.HomeWindow;
import view.LoginWindow;

/**
 *
 * @author Gabriel
 */
public class LoginController {
    
    private LoginWindow view;

    public LoginController(LoginWindow view) {
        this.view = view;       
    }
    
    public void userLogin(){    
       User user = new User(view.getTxt_login().getText(), 
               view.getTxt_password().getText(), -1, null, null, null, null);
       
       DbConnection connection = new DbConnection();
       try{
           Connection conn = connection.getConnection();
           UserDAO dao = new UserDAO(conn);
           ResultSet res = dao.search(user);
           if(res.next()){
               user = User.fromResultSet(res);
               SwingUtilities.invokeLater(() -> {
                    CustomJDialog.showCustomDialog("Aviso!", "Usuário logado com sucesso!");
                });
               System.out.println(user.toString());
               view.setVisible(false);
               HomeWindow homeWin = new HomeWindow();
               homeWin.setVisible(true);
           }else{
               SwingUtilities.invokeLater(() -> {
                    CustomJDialog.showCustomDialog("Erro!", "Usuário não encontrado!");
                });        
           }           
       }catch(SQLException e){
           SwingUtilities.invokeLater(() -> {
                    CustomJDialog.showCustomDialog("ERRO!", "Erro de conexão com o banco de dados.\nEntre em contato com os administradores.");
            });
       }     
    }
}
