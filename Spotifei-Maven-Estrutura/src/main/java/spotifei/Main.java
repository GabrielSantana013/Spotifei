/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package spotifei;

import DAO.DbConnection;
import DAO.MusicDAO;
import cache.MusicCache;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Music;
import java.io.IOException;
import view.LoginWindow;
import view.assets.fonts.FontLoader;
import view.customDialogs.CustomJDialog;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        FontLoader.registerAllFonts(); // registra todas as fontes
        List<Music> allMusics = null;
        try(Connection conn = new DbConnection().getConnection()){
        
            MusicDAO dao = new MusicDAO(conn);
            allMusics = dao.searchMusic("");
            MusicCache.loadCache(allMusics);            
            
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar músicas no banco.");
            System.out.println(e.getMessage());
        }
               
        LoginWindow lw = new LoginWindow();
        lw.setVisible(true);
    }
    
}
