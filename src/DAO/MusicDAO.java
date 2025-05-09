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
                res.getInt("deslikes"),
                res.getInt("duration"),
                res.getString("title"),
                res.getString("description"),                        
                res.getString("genre"),
                res.getString("artist_name")
            );
        }   
        return null;
    }
}
