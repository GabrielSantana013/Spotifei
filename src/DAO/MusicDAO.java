/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Music;

/**
 *
 * @author gabas
 */
public class MusicDAO {
    
    private Connection conn;

    public MusicDAO(Connection conn) {
        this.conn = conn;
    }
    
    public List<Music> searchMusic(String musicName) throws SQLException {
        String sql = "SELECT m.*, a.name AS artist_name FROM spotifei.music m " +
                 "JOIN spotifei.artist a ON m.artist_id = a.artist_id " +
                 "WHERE LOWER(m.title) LIKE LOWER(?) OR " +
                 "LOWER(m.genre) LIKE LOWER(?) OR " +
                 "LOWER(a.name) LIKE LOWER(?)";
    
        List<Music> musics = new ArrayList<>();
    
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "%" + musicName + "%"); // busca parcial
        statement.setString(2, "%" + musicName + "%");
        statement.setString(3, "%" + musicName + "%");
    
        ResultSet result = statement.executeQuery();
    
        while (result.next()) {
            musics.add(Music.fromResultSet(result));
        }
    
        return musics;
    }
    
    public Music getMusicByTitle(String title) throws SQLException {
        String sql = "SELECT m.*, a.name AS artist_name " +
                     "FROM spotifei.music m " +
                     "JOIN spotifei.artist a ON m.artist_id = a.artist_id " +
                     "WHERE m.title = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, title);
        ResultSet res = statement.executeQuery();

        if (res.next()) {
            return new Music(
                res.getInt("music_id"),            
                res.getInt("likes"),            
                res.getInt("dislikes"),
                res.getInt("duration"),
                res.getString("title"),
                res.getString("description"),                        
                res.getString("genre"),
                res.getString("artist_name")
            );
        }   
        return null;
    }

    public Music getMusicById(int musicId) throws SQLException {
        String sql = "SELECT m.*, a.name AS artist_name " +
                     "FROM spotifei.music m " +
                     "JOIN spotifei.artist a ON m.artist_id = a.artist_id " +
                     "WHERE m.music_id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, musicId);
        ResultSet res = statement.executeQuery();

        if (res.next()) {
            return new Music(
                res.getInt("music_id"),            
                res.getInt("likes"),            
                res.getInt("dislikes"),
                res.getInt("duration"),
                res.getString("title"),
                res.getString("description"),                        
                res.getString("genre"),
                res.getString("artist_name")
            );
        }   
        return null;
    }
    
    public void incrementLike(int musicId) throws SQLException {
    String sql = "UPDATE spotifei.music SET likes = likes + 1 WHERE music_id = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, musicId);
        statement.executeUpdate();
    }
}

    public void decrementLike(int musicId) throws SQLException {
        String sql = "UPDATE spotifei.music SET likes = likes - 1 WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }

    public void incrementDislike(int musicId) throws SQLException {
        String sql = "UPDATE spotifei.music SET dislikes = dislikes + 1 WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }

    public void decrementDislike(int musicId) throws SQLException {
        String sql = "UPDATE spotifei.music SET dislikes = dislikes - 1 WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }
    
    public ArrayList<Music> getTop5MostLikedMusics() throws SQLException {
        String sql = "SELECT m.*, a.name AS artist_name " +
                     "FROM spotifei.music m " +
                     "JOIN spotifei.artist a ON m.artist_id = a.artist_id " +
                     "ORDER BY m.likes DESC " +
                     "LIMIT 5";

        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        ArrayList<Music> topMusics = new ArrayList<>();
        while (res.next()) {
            topMusics.add(new Music(
                res.getString("title"),
                res.getString("artist_name"),
                res.getBytes("music_photo")
            ));
        }
        return topMusics;
    }

    
}


