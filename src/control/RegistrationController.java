/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DAO.UserDAO;
import DAO.dbConnection;
import model.User;
import view.RegistrationWindow;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;


/**
 *
 * @author gabas
 */
public class RegistrationController {

    private RegistrationWindow view;

    public RegistrationController(RegistrationWindow view) {
        this.view = view;
    }
    
    public void saveUser(){
        
        //wip: substitua por view.getTxt_nome_cadastro().getText();
        String userLogin = "";
        String userPassword = "";    
        String name = "";
        String gender = "";
        Date birthDate = null; //modifica depois qnd a classe existir
                
        User user = new User(userLogin, userPassword, name, gender, birthDate);
        
        dbConnection conexao = new dbConnection();
        try{
            Connection conn = conexao.getConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            JOptionPane.showMessageDialog(view, "Usuário Cadastrado");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão");
        }
    }
    
}
