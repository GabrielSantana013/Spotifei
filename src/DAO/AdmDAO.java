/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Gabriel
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;

import model.Artist;
import model.Music;

public class AdmDAO {
    
    private Connection conn;

    public AdmDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertArtist(Artist artist) throws SQLException {
        String sql = "INSERT INTO spotifei.artist (name, gender, birth_date, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, artist.getName());
            statement.setString(2, artist.getGender());
            statement.setDate(3, new java.sql.Date(artist.getBirthDate().getTime()));
            statement.setString(4, artist.getArtistDescription());
            statement.executeUpdate();
        }
    }

    public void insertMusic(String title, String description, int duration, 
            String genre, int artistId, byte[] musicPhoto, byte[] musicAudio) 
            throws SQLException {
        String sql = "INSERT INTO spotifei.music (title, description, duration, "
                + "genre, artist_id, music_photo, music_audio) VALUES (?, ?, ?, "
                + "?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setInt(3, duration);
            statement.setString(4, genre);
            statement.setInt(5, artistId);
            statement.setBytes(6, musicPhoto);
            statement.setBytes(7, musicAudio);
            statement.executeUpdate();
        }
    }
    
    public List<Artist> getAllArtists() throws SQLException {
        List<Artist> artistList = new ArrayList<>();

        String sql = "SELECT artist_id, description, name, gender, birth_date FROM spotifei.artist";

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet res = statement.executeQuery()) {           
            while (res.next()) {
                int id = res.getInt("artist_id");
                String description = res.getString("description");
                String name = res.getString("name");
                String gender = res.getString("gender");
                Date birthDate = res.getDate("birth_date");
                Artist artist = new Artist(id, description, name, gender, birthDate);                
                artistList.add(artist);
            }
        }

        return artistList;
    }
    
    public void deleteMusicById(int musicId) throws SQLException {
        String sql = "DELETE FROM spotifei.music WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }

    public void deleteMusicByTitle(String title) throws SQLException {
        String sql = "DELETE FROM spotifei.music WHERE title = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.executeUpdate();
        }
    }

}
