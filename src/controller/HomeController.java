/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DbConnection;
import DAO.MusicDAO;
import DAO.UserDAO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.Music;
import model.User;
import view.HomeWindow;
import view.customDialogs.CustomJDialog;

/**
 *
 * @author Gabriel
 */
public class HomeController{
    
    private HomeWindow view;
    private User user;

    public HomeController(HomeWindow view, User user) throws IOException{
        this.view = view;
        this.user = user;        
        
    }
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
        view.getLbl_welcome().setText("Bem-vindo(a), " + user.getName());
    }
    
    public void setLikesAndDislikes() {
        try (Connection conn = new DbConnection().getConnection()) {
            UserDAO dao = new UserDAO(conn);
            user.setLikedMusics(dao.getLikedMusics(user.getUserId()));
            user.setDislikedMusics(dao.getDislikedMusics(user.getUserId()));
        } catch (SQLException ex) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar músicas no banco.");
            return;
        }

        List<Music> liked = user.getLikedMusics();
        List<Music> disliked = user.getDislikedMusics();

        view.getNum_likes().setText("Likes - " + (liked != null ? liked.size() : "nenhuma") + " músicas curtidas");
        view.getNum_dislikes().setText("Dislikes - " + (disliked != null ? disliked.size() : "nenhuma") + " músicas descurtidas");

        if (liked != null && !liked.isEmpty()) {
            String[] likedTitles = liked.stream()
                .map(Music::getMusicTitle)
                .toArray(String[]::new);
            view.getListLikes().setListData(likedTitles);
        } else {
            view.getListLikes().setListData(new String[0]);
        }

        if (disliked != null && !disliked.isEmpty()) {
            String[] dislikedTitles = disliked.stream()
                .map(Music::getMusicTitle)
                .toArray(String[]::new);
            view.getListDislikes().setListData(dislikedTitles);
        } else {
            view.getListDislikes().setListData(new String[0]);
        }
    }
    
    public void fillTopFive()throws IOException{
    
        ArrayList<Music> topFive = new ArrayList();
        BufferedImage image = null;
        try(Connection conn = new DbConnection().getConnection()){
        
            MusicDAO dao = new MusicDAO(conn);
            topFive = dao.getTop5MostLikedMusics();
            
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar o top 5 no banco.");
        }
                
        try{
        ByteArrayInputStream input = new ByteArrayInputStream(topFive.get(0).getMusicPhoto());
        image = ImageIO.read(input);
        }catch(IOException ex){
            CustomJDialog.showCustomDialog("Erro!", "Imagem corrompida!");
        }        
        
        view.getjLabel1().setIcon(new ImageIcon(image));
    }


    public User getUser() {
        return user;
    }
}

