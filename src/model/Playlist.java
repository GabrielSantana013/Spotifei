/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author unifgdias
 */
public class Playlist {
    
    private int playlistId, playlistLikes, playlistDeslikes;
    private String playlistName, playlistDescription;
    private ArrayList<Music> playlistSongs;

    public Playlist(int playlistId, int playlistLikes, int playlistDeslikes, String playlistName, String playlistDescription, ArrayList<Music> playlistSongs) {
        this.playlistId = playlistId;
        this.playlistLikes = playlistLikes;
        this.playlistDeslikes = playlistDeslikes;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.playlistSongs = playlistSongs;
    }

    public Playlist(int playlistLikes, int playlistDeslikes, String playlistName, String playlistDescription, ArrayList<Music> playlistSongs) {
        this.playlistLikes = playlistLikes;
        this.playlistDeslikes = playlistDeslikes;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.playlistSongs = playlistSongs;
    }

    public Playlist(int playlistId, int playlistLikes, int playlistDeslikes, String playlistName, String playlistDescription) {
        this.playlistId = playlistId;
        this.playlistLikes = playlistLikes;
        this.playlistDeslikes = playlistDeslikes;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
    }
    
    
    
}
