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
        OpenPlaylistWindow opw = new OpenPlaylistWindow(user);  
        
        // TODO: pop-up da playlist, setVisible so pra testes
        this.view.getList_playlists().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                opw.setVisible(true);
            }
        });
        
        
        view.getList_playlists().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = view.getList_playlists().getSelectedIndex();

                    if (selectedIndex >= 0 && selectedIndex < currentPlaylists.size()) {
                        Playlist selectedPlaylist = currentPlaylists.get(selectedIndex);

                        // Aqui você pode, por exemplo, exibir as músicas da playlist:
                        System.out.println("Playlist selecionada: " + selectedPlaylist.getPlaylistName());
                        System.out.println("Músicas: " + selectedPlaylist.getPlaylistSongs());

                        // Você pode também criar um método para exibir os detalhes da playlist na tela
                        // exibirDetalhesDaPlaylist(selectedPlaylist);
                    }
                }
            }
        });

        this.view.getBtt_addPlaylist().addActionListener(e -> createPlaylist());
        this.view.getBtt_removePlaylist().addActionListener(e -> removePlaylist());
        this.view.getBtt_removeMusic().addActionListener(e -> removeMusic());

    }
    
    public void createPlaylist(){
        //deixar a janela da criação de playlist visível
        //criar a playlist
        //deixar invisível
    }
    
    public void removePlaylist(){
        //deixar a janela da criação de playlist visível
        //criar a playlist
        //deixar invisível
    }
    
    public void removeMusic(){
        //deixar a janela da criação de playlist visível
        //criar a playlist
        //deixar invisível
    }
    
    public void loadUserPaylists(){
        
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
    }
}