/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DbConnection;
import DAO.MusicDAO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Music;
import model.User;
import static utils.ImageProcessor.byteArrayToImage;
import view.AdmHomeWindow;
import view.customDialogs.CustomJDialog;

/**
 *
 * @author Pero Schneider, Gabriel Santana Dias
 */
public class AdmHomeController{
    
    private AdmHomeWindow view;
    private User user;

    public AdmHomeController(AdmHomeWindow view, User user) throws IOException{
        this.view = view;
        this.user = user;        
        
    }
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
        view.getLbl_welcome().setText("Bem-vindo(a), administrador " + user.getName());
    }
 
    public void fillTopFive() throws IOException{
        ArrayList<Music> topFive = new ArrayList();
        
        try(Connection conn = new DbConnection().getConnection()){
        
            MusicDAO dao = new MusicDAO(conn);
            topFive = dao.getTop5MostLikedMusics();
            
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar o top 5 no banco.");
        }
        
        List<JLabel> titles = Arrays.asList(
            view.getTitle1(),
            view.getTitle2(),
            view.getTitle3(),
            view.getTitle4(),
            view.getTitle5()
        );
        
        List<JLabel> artists = Arrays.asList(
            view.getArtist1(),
            view.getArtist2(),
            view.getArtist3(),
            view.getArtist4(),
            view.getArtist5()
        );
        
        List<JLabel> images = Arrays.asList(
            view.getjLabel1(),
            view.getjLabel2(),
            view.getjLabel3(),
            view.getjLabel4(),
            view.getjLabel5()
        );

        for (int i = 0; i < topFive.size(); i++) {
            Music m = topFive.get(i);
            
            String title = m.getMusicTitle();
            titles.get(i).setText(title);
            String artist = m.getArtistName();
            artists.get(i).setText(artist);
            BufferedImage image = byteArrayToImage(m.getMusicPhoto());
            images.get(i).setIcon(new ImageIcon(image));
            
        }
    }

    public void fillTopFiveBad() throws IOException{
        ArrayList<Music> topFive = new ArrayList();
        
        try(Connection conn = new DbConnection().getConnection()){
        
            MusicDAO dao = new MusicDAO(conn);
            topFive = dao.getTop5LessLikedMusics();
            
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar o top 5 no banco.");
        }
        
        List<JLabel> titles = Arrays.asList(
            view.getTitle6(),
            view.getTitle7(),
            view.getTitle8(),
            view.getTitle9(),
            view.getTitle10()
        );
        
        List<JLabel> artists = Arrays.asList(
            view.getArtist6(),
            view.getArtist7(),
            view.getArtist8(),
            view.getArtist9(),
            view.getArtist10()
        );
        
        List<JLabel> images = Arrays.asList(
            view.getjLabel7(),
            view.getjLabel8(),
            view.getjLabel9(),
            view.getjLabel10(),
            view.getjLabel11()
        );

        for (int i = 0; i < topFive.size(); i++) {
            Music m = topFive.get(i);
            
            String title = m.getMusicTitle();
            titles.get(i).setText(title);
            String artist = m.getArtistName();
            artists.get(i).setText(artist);
            BufferedImage image = byteArrayToImage(m.getMusicPhoto());
            images.get(i).setIcon(new ImageIcon(image));
            
        }
    }
    
    public void totalUsers(){
        int users = 0;
        
        try(Connection conn = new DbConnection().getConnection()){
        
            MusicDAO dao = new MusicDAO(conn);
            users = dao.getTotalUsers();
            
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar usuários.");
        }
        
        view.getLbl_numberUsers().setText(String.valueOf(users));
    }
    
    public void totalMusics(){
        int musics = 0;
        
        try(Connection conn = new DbConnection().getConnection()){
        
            MusicDAO dao = new MusicDAO(conn);
            musics = dao.getTotalMusics();
            
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar músicas.");
        }
        
        view.getLbl_numberMusics().setText(String.valueOf(musics));
    }
    
    
    public User getUser() {
        return user;
    }
}

