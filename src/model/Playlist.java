/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author unifgdias
 */
public class Playlist {
    
    private int playlistId;
    private String playlistName, playlistDescription;
    private ArrayList<String> playlistSongs;
    private int userId;

    public Playlist(int playlistId, String playlistName, String playlistDescription, ArrayList<String> playlistSongs, int userId) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.playlistSongs = playlistSongs;
        this.userId = userId;
    }    

    public Playlist(int playlistId, String playlistName, String playlistDescription, ArrayList<String> playlistSongs) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.playlistSongs = playlistSongs;
    }

    public Playlist(String playlistName, String playlistDescription, int userId) {
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.userId = userId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistDescription() {
        return playlistDescription;
    }

    public void setPlaylistDescription(String playlistDescription) {
        this.playlistDescription = playlistDescription;
    }

    public ArrayList<String> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(ArrayList<String> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }    
    
    public static Playlist fromResultSet(ResultSet res) throws SQLException {
        String playlistSongsString = res.getString("playlist_songs");
        ArrayList<String> playlistSongs = new ArrayList<>();

        if (playlistSongsString != null && !playlistSongsString.isEmpty()) {
            playlistSongs = new ArrayList<>(Arrays.asList(playlistSongsString.split(";")));
        }
               

        return new Playlist(
            res.getInt("playlist_id"),
            res.getString("title"),
            res.getString("description"),            
            playlistSongs,
            res.getInt("user_id")              
        );
    }
        
}
