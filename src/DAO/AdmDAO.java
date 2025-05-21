package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;

import model.Artist;
import model.Music;

/**
 * DAO (Data Access Object) responsável pelas operações administrativas
 * no banco de dados da aplicação Spotifei. Permite inserção e remoção
 * de músicas e artistas, bem como recuperação da lista de artistas.
 * 
 * @author Gabriel
 */
public class AdmDAO {

    private Connection conn;

    /**
     * Construtor da classe AdmDAO.
     *
     * @param conn conexão ativa com o banco de dados.
     */
    public AdmDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Insere um novo artista na tabela {@code spotifei.artist}.
     *
     * @param artist objeto Artist contendo os dados do artista a ser inserido.
     * @throws SQLException caso ocorra erro durante a execução da query SQL.
     */
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

    /**
     * Insere uma nova música na tabela {@code spotifei.music}.
     *
     * @param title título da música.
     * @param description descrição da música.
     * @param duration duração da música em segundos.
     * @param genre gênero musical.
     * @param artistId ID do artista associado.
     * @param musicPhoto array de bytes representando a imagem da música.
     * @param musicAudio array de bytes representando o áudio da música.
     * @throws SQLException caso ocorra erro durante a execução da query SQL.
     */
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

    /**
     * Recupera todos os artistas cadastrados na tabela {@code spotifei.artist}.
     *
     * @return lista de objetos {@code Artist} com todos os artistas encontrados.
     * @throws SQLException caso ocorra erro durante a execução da query SQL.
     */
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

    /**
     * Exclui uma música da tabela {@code spotifei.music} com base no ID.
     *
     * @param musicId identificador da música a ser excluída.
     * @throws SQLException caso ocorra erro durante a execução da query SQL.
     */
    public void deleteMusicById(int musicId) throws SQLException {
        String sql = "DELETE FROM spotifei.music WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }

    /**
     * Exclui uma música da tabela {@code spotifei.music} com base no título.
     *
     * @param title título da música a ser excluída.
     * @throws SQLException caso ocorra erro durante a execução da query SQL.
     */
    public void deleteMusicByTitle(String title) throws SQLException {
        String sql = "DELETE FROM spotifei.music WHERE title = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.executeUpdate();
        }
    }
}
