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
        String sql = "INSERT INTO spotifei.playlist (title, description, playlist_songs, user_id) VALUES (?, ?, ?, ?)";
        String serializedSong = String.join(";",playlist.getPlaylistSongs());

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, playlist.getPlaylistName());
            statement.setString(2, playlist.getPlaylistDescription());
            statement.setString(3, serializedSong); // Ex: "1;3;7;"
            statement.setInt(4, playlist.getUserId());
            statement.executeUpdate();
        }

    public void deletePlaylist(int playlistId) throws SQLException {
        String sql = "DELETE FROM spotifei.playlist WHERE playlist_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playlistId);
            statement.executeUpdate();
    }

    /*public void removeMusicFromPlaylist(int playlistId, int musicId) throws SQLException {
        // 1. Buscar playlist_songs atual
        String selectSql = "SELECT playlist_songs FROM spotifei.playlist WHERE playlist_id = ?";
        String currentSongs = "";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playlistId);
            try (ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    currentSongs = res.getString("playlist_songs");
                }
            }
        }

        // 2. Remover o musicId da string
        String idStr = musicId + ";";
        String updatedSongs = currentSongs.replace(idStr, "");

        // 3. Atualizar a string no banco
        String updateSql = "UPDATE spotifei.playlist SET playlist_songs = ? WHERE playlist_id = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setString(1, updatedSongs);
            updateStmt.setInt(2, playlistId);
            updateStmt.executeUpdate();
        }
    }*/

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
