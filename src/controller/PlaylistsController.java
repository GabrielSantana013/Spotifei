/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DbConnection;
import DAO.PlaylistDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Playlist;
import model.User;
import view.OpenPlaylistWindow;
import view.PlaylistsWindow;
import view.customDialogs.CustomJDialog;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class PlaylistsController {
    private PlaylistsWindow view;
    private ArrayList<Playlist> currentPlaylists;
    private Playlist selectedPlaylist;
    private User user;
    

    
    public PlaylistsController(PlaylistsWindow view, User user) {
        this.view = view;
        this.user = user;
        this.currentPlaylists = new ArrayList<>();        
        
        this.view.getBtt_addPlaylist().addActionListener(e -> view.getPnl_addPlaylist().setVisible(true));
                
        view.getList_playlists().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = view.getList_playlists().getSelectedIndex();

                    if (selectedIndex >= 0 && selectedIndex < currentPlaylists.size()) {
                        selectedPlaylist = currentPlaylists.get(selectedIndex);     
                        
                        System.out.println("id " + selectedPlaylist.getPlaylistId());
                    }
                }
            }
        });

        this.view.getBtt_confirmar().addActionListener(e -> createPlaylist());        
        this.view.getBtt_removePlaylist().addActionListener(e -> removePlaylist());
        this.view.getBtt_openPlaylist().addActionListener(e -> showPlaylistDetails());
    }
    
    public void createPlaylist(){
        String playlistTitle = view.getTxt_nomePlaylist().getText();
        String playlistDescription = view.getTxt_description().getText();

        if (playlistTitle.isBlank()) {
            CustomJDialog.showCustomDialog("Aviso", "Digite um nome para a playlist.");
            return;
        }

        Playlist p = new Playlist(playlistTitle, playlistDescription, user.getUserId());

        try (Connection conn = new DbConnection().getConnection()) {
            PlaylistDAO dao = new PlaylistDAO(conn);
            dao.createPlaylist(p);

            loadUserPlaylists();

            view.getTxt_nomePlaylist().setText("");
            view.getTxt_description().setText("");
            view.getPnl_addPlaylist().setVisible(false);

            CustomJDialog.showCustomDialog("Sucesso", "Playlist criada com sucesso!");

        } catch (SQLException e) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao criar playlist.");            
        }
    }

    
    public void removePlaylist(){
        
        try(Connection conn = new DbConnection().getConnection()){
            PlaylistDAO dao = new PlaylistDAO(conn);
            dao.deletePlaylist(selectedPlaylist.getPlaylistId());
        
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao deletar playlist.");
            e.printStackTrace();
        }
        
        loadUserPlaylists();
        CustomJDialog.showCustomDialog("Aviso!", "Playlist deletada.");
    }
    
    public void showPlaylistDetails() {
        if (selectedPlaylist == null) {
            CustomJDialog.showCustomDialog("Aviso", "Selecione uma playlist primeiro.");
            return;
        }
        OpenPlaylistWindow playlistDetailsView = new OpenPlaylistWindow(selectedPlaylist);      
        playlistDetailsView.setVisible(true);
    }
    
    public void loadUserPlaylists(){
        
        try(Connection conn = new DbConnection().getConnection()){
            PlaylistDAO dao = new PlaylistDAO(conn);
            currentPlaylists = dao.getPlaylistsByUserId(user.getUserId());
            
            DefaultListModel<String> model = new DefaultListModel();
            
            for(Playlist p : currentPlaylists){
                model.addElement(p.getPlaylistName());
            }
            
            if(currentPlaylists != null){
                view.getList_playlists().setModel(model);
            }
            
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao carregar playlists");
            e.getMessage();
        }
    }
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
        view.getPnl_addPlaylist().setVisible(false);
    }
}