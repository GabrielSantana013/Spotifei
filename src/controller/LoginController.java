/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DbConnection;
import DAO.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
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
               JOptionPane.showMessageDialog(view, "Login efetuado com Sucesso!");
               System.out.println(user.toString());
               view.setVisible(false);
               HomeWindow homeWin = new HomeWindow();
               homeWin.setVisible(true);
           }else{
               JOptionPane.showMessageDialog(view, "Usuário nao encontrado.", 
                       "Erro", JOptionPane.ERROR_MESSAGE);           
           }           
       }catch(SQLException e){
           JOptionPane.showMessageDialog(view, "Erro de conexao", 
                       "Erro", JOptionPane.ERROR_MESSAGE);
       }     
    }
}
