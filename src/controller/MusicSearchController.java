/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DbConnection;
import DAO.MusicDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Music;
import view.SearchWindow;
import view.customDialogs.CustomJDialog;

/**
 *
 * @author gabas
 */



public class MusicSearchController {
    
    private SearchWindow view;

    public MusicSearchController(SearchWindow view) {
        this.view = view;        
    }
    
    public void searchMusic(){
        String musicName = view.getSearch_name().getText();
        
        if (musicName == null || musicName.trim().isEmpty()) {
            CustomJDialog.showCustomDialog("Aviso!", "Digite o nome de uma música para buscar.");
            return;
        }
        
        try (Connection conn = new DbConnection().getConnection()) {
            MusicDAO musicDAO = new MusicDAO(conn);
            List<Music> musics = musicDAO.searchMusic(musicName);

            if (musics.isEmpty()) {
                CustomJDialog.showCustomDialog("Resultado", "Nenhuma música encontrada.");
                view.getjList1().setListData(new String[0]); // limpa a lista
            } 
            else{
                String[] titles = musics.stream()
                            .map(Music::getMusicTitle)
                            .toArray(String[]::new);
                view.getjList1().setListData(titles); // atualiza com os títulos das músicas
            }

        } catch (SQLException e) {
            e.printStackTrace();
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar músicas no banco de dados.");
        }
    }
    
}
