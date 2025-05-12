/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DbConnection;
import DAO.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import model.User;
import view.HomeWindow;
import view.customDialogs.CustomJDialog;

/**
 *
 * @author Gabriel
 */
public class HomeController {
    
    private HomeWindow view;
    private User user;

    public HomeController(HomeWindow view, User user) {
        this.view = view;
        this.user = user;
        
    }
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
        view.getLbl_welcome().setText("Bem-vindo(a), " + user.getName());
    }
    
    public void setLikesAndDislikes(){
        
        try(Connection conn = new DbConnection().getConnection()) {
            UserDAO dao = new UserDAO(conn);
            user.setLikedMusics(dao.getLikedMusics(user.getUserId()));
            user.setDislikedMusics(dao.getDislikedMusics(user.getUserId()));
        }catch(SQLException ex) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar musicas no banco");            
        }
        
        view.getBttLikes().setText("Você já curtiu " + user.getLikedCount()+ " musicas!");
        view.getBttDislikes().setText("Você não curtiu " + user.getDislikedCount() + " musicas :(");
    }

    public User getUser() {
        return user;
    }
}
