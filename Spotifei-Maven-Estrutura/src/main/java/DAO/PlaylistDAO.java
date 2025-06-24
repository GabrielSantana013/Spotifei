package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.Playlist;

/**
 * Classe DAO responsável pelas operações de acesso a dados da tabela 'playlist'
 * no banco de dados Spotifei.
 * Permite criar, deletar, atualizar e buscar playlists relacionadas a um usuário.
 * 
 * @author gabas
 */
public class PlaylistDAO {
    
    private Connection conn;

    /**
     * Construtor do DAO de Playlist.
     * 
     * @param conn conexão ativa com o banco de dados
     */
    public PlaylistDAO(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * Cria uma nova playlist no banco de dados.
     * 
     * @param playlist objeto Playlist a ser inserido
     * @throws SQLException se ocorrer erro na operação com o banco de dados
     */
    public void createPlaylist(Playlist playlist) throws SQLException {
        String sql = "INSERT INTO spotifei.playlist (title, description, user_id) VALUES (?, ?, ?)";        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, playlist.getPlaylistName());
        statement.setString(2, playlist.getPlaylistDescription());            
        statement.setInt(3, playlist.getUserId());
        statement.executeUpdate();
    }

    /**
     * Exclui uma playlist com base no seu ID.
     * 
     * @param playlistId ID da playlist a ser removida
     * @throws SQLException se ocorrer erro ao deletar a playlist
     */
    public void deletePlaylist(int playlistId) throws SQLException {
        String sql = "DELETE FROM spotifei.playlist WHERE playlist_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, playlistId);
        statement.executeUpdate();
    }

    /**
     * Atualiza a lista de músicas de uma playlist.
     * O campo de músicas é armazenado como uma String.
     * 
     * @param playlistId ID da playlist a ser atualizada
     * @param updatedSongs nova representação textual das músicas
     * @throws SQLException se ocorrer erro ao atualizar a playlist
     */
    public void updatePlaylistSongs(int playlistId, String updatedSongs) throws SQLException {
        String sql = "UPDATE spotifei.playlist SET playlist_songs = ? WHERE playlist_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, updatedSongs);
            statement.setInt(2, playlistId);
            statement.executeUpdate();
        }
    }

    /**
     * Retorna a lista de músicas de uma playlist como String.
     * 
     * @param playlistId ID da playlist desejada
     * @return representação em String das músicas, ou null se não existir
     * @throws SQLException se ocorrer erro na consulta
     */
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

    /**
     * Retorna todas as playlists associadas a um determinado usuário.
     * 
     * @param userId ID do usuário
     * @return lista de objetos Playlist pertencentes ao usuário
     * @throws SQLException se ocorrer erro ao buscar as playlists
     */
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
