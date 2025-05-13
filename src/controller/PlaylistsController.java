/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Playlist;
import model.User;
import view.OpenPlaylistWindow;
import view.PlaylistsWindow;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class PlaylistsController {
    private PlaylistsWindow view;
    private ArrayList<Playlist> currentPlaylists;
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
    }
}