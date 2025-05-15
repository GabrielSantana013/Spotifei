/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.Playlist;
/**
 *
 * @author gabas
 */
public class PlaylistDAO {
    
    private Connection conn;

    public PlaylistDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void createPlaylist(Playlist playlist) throws SQLException {
        String sql = "INSERT INTO spotifei.playlist (title, description, user_id) VALUES (?, ?, ?)";        
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, playlist.getPlaylistName());
            statement.setString(2, playlist.getPlaylistDescription());            
            statement.setInt(3, playlist.getUserId());
            statement.executeUpdate();
        }

    public void deletePlaylist(int playlistId) throws SQLException {
        String sql = "DELETE FROM spotifei.playlist WHERE playlist_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playlistId);
            statement.executeUpdate();
    }

    public void updatePlaylistSongs(int playlistId, String updatedSongs) throws SQLException {
        String sql = "UPDATE spotifei.playlist SET playlist_songs = ? WHERE playlist_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, updatedSongs);
            statement.setInt(2, playlistId);
            statement.executeUpdate();
        }
    }
    
    public String getPlaylistSongs(int playlistId) throws SQLException {
        String sql = "SELECT playlist_songs FROM spotifei.playlist WHERE playlist_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("playlist_songs");
            } else {
                return null;
            }
        }
    }

    public ArrayList<Playlist> getPlaylistsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM spotifei.playlist WHERE user_id = ?";
        ArrayList<Playlist> playlists = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet res = statement.executeQuery()) {
                while (res.next()) {
                    playlists.add(Playlist.fromResultSet(res));
                }
            }
        }

        return playlists;
    }
    
}
