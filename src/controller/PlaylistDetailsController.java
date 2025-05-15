/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DbConnection;
import DAO.PlaylistDAO;
import java.sql.Connection;
import java.sql.SQLException;
import model.Playlist;
import view.OpenPlaylistWindow;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.customDialogs.CustomJDialog;

/**
 *
 * @author gabas
 */
public class PlaylistDetailsController {
    
    private OpenPlaylistWindow view;
    private Playlist playlist;
    private int musicIndex;

    public PlaylistDetailsController(OpenPlaylistWindow view, Playlist playlist) {
        this.view = view;
        this.playlist = playlist;
        
        view.getBtt_removeMusic().addActionListener(e -> removePlaylistSong());   
    }
    
    public void setPlaylistAtributtes(){
        view.getBtt_playlistName().setText(playlist.getPlaylistName());  
        view.getTxt_description().setText(playlist.getPlaylistDescription());
        view.setTitle(playlist.getPlaylistName());        
    }
    
    public void loadPlaylistSongs() {
        try(Connection conn = new DbConnection().getConnection()){
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);
            String stringSongs = playlistDAO.getPlaylistSongs(playlist.getPlaylistId());            

            DefaultListModel<String> model = new DefaultListModel();
            
            if (stringSongs != null && !stringSongs.isEmpty()) {
                String[] actualSongs = stringSongs.split(";");
                for (String musica : actualSongs) {
                    model.addElement(musica.trim());
                }
                view.getList_musics().setModel(model);                
            }

        } catch (SQLException e) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao carregar músicas da playlist.");
            e.printStackTrace();            
        }
    }
    
    public void removePlaylistSong() {               
        
        musicIndex = view.getList_musics().getSelectedIndex();
        
        System.out.println("mIndex " + musicIndex);
        
        if (musicIndex < 0) {
            CustomJDialog.showCustomDialog("Aviso", "Nenhuma música selecionada.");
            System.out.println("Selected Index: " + musicIndex);
            System.out.println("View Selected Index: " + view.getList_musics().getSelectedIndex());
            return;
        }

        ArrayList<String> currentSongs = playlist.getPlaylistSongs();
        currentSongs.remove(musicIndex);

        String updatedSongs = String.join(";", currentSongs);

        try (Connection conn = new DbConnection().getConnection()) {
            PlaylistDAO dao = new PlaylistDAO(conn);
            dao.updatePlaylistSongs(playlist.getPlaylistId(), updatedSongs);
        } catch (SQLException e) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao excluir música da playlist.");
            System.out.println(e.getMessage());
            return;
        }

        CustomJDialog.showCustomDialog("Aviso", "Música excluída com sucesso!");
        loadPlaylistSongs();
    }

    
}
