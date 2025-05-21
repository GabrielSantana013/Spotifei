package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Music;

/**
 * Classe DAO responsável por acessar e manipular dados da entidade {@code Music}
 * no banco de dados. Realiza buscas, atualizações de likes/dislikes, e consultas
 * de estatísticas.
 * 
 * Os métodos assumem que as tabelas estão no schema {@code spotifei} e que há
 * relacionamento entre as tabelas {@code music} e {@code artist}.
 * 
 * Requer uma conexão {@link java.sql.Connection} válida passada via construtor.
 * 
 * @author gabas
 */
public class MusicDAO {
    
    private Connection conn;

    /**
     * Construtor da classe {@code MusicDAO}.
     * 
     * @param conn conexão com o banco de dados a ser usada nas operações.
     */
    public MusicDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Busca músicas com base no nome, gênero ou nome do artista.
     * A busca é case-insensitive e parcial.
     * 
     * @param musicName termo a ser buscado.
     * @return lista de músicas encontradas.
     * @throws SQLException se ocorrer erro na consulta.
     */
    public List<Music> searchMusic(String musicName) throws SQLException {
        String sql = "SELECT m.*, a.name AS artist_name FROM spotifei.music m " +
                     "JOIN spotifei.artist a ON m.artist_id = a.artist_id " +
                     "WHERE LOWER(m.title) LIKE LOWER(?) OR " +
                     "LOWER(m.genre) LIKE LOWER(?) OR " +
                     "LOWER(a.name) LIKE LOWER(?)";

        List<Music> musics = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "%" + musicName + "%");
        statement.setString(2, "%" + musicName + "%");
        statement.setString(3, "%" + musicName + "%");

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            musics.add(Music.fromResultSet(result));
        }

        return musics;
    }

    /**
     * Retorna uma música com base no título exato.
     * 
     * @param title título da música.
     * @return objeto {@code Music} correspondente ou {@code null} se não encontrado.
     * @throws SQLException se ocorrer erro na consulta.
     */
    public Music getMusicByTitle(String title) throws SQLException {
        String sql = "SELECT m.*, a.name AS artist_name " +
                     "FROM spotifei.music m " +
                     "JOIN spotifei.artist a ON m.artist_id = a.artist_id " +
                     "WHERE m.title = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, title);
        ResultSet res = statement.executeQuery();

        if (res.next()) {
            return Music.fromResultSet(res);
        }
        return null;
    }

    /**
     * Retorna uma música com base no ID.
     * 
     * @param musicId ID da música.
     * @return objeto {@code Music} correspondente ou {@code null} se não encontrado.
     * @throws SQLException se ocorrer erro na consulta.
     */
    public Music getMusicById(int musicId) throws SQLException {
        String sql = "SELECT m.*, a.name AS artist_name " +
                     "FROM spotifei.music m " +
                     "JOIN spotifei.artist a ON m.artist_id = a.artist_id " +
                     "WHERE m.music_id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, musicId);
        ResultSet res = statement.executeQuery();

        if (res.next()) {
            return Music.fromResultSet(res);
        }
        return null;
    }

    /**
     * Incrementa o número de curtidas de uma música.
     * 
     * @param musicId ID da música.
     * @throws SQLException se ocorrer erro na atualização.
     */
    public void incrementLike(int musicId) throws SQLException {
        String sql = "UPDATE spotifei.music SET likes = likes + 1 WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }

    /**
     * Decrementa o número de curtidas de uma música.
     * 
     * @param musicId ID da música.
     * @throws SQLException se ocorrer erro na atualização.
     */
    public void decrementLike(int musicId) throws SQLException {
        String sql = "UPDATE spotifei.music SET likes = likes - 1 WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }

    /**
     * Incrementa o número de dislikes de uma música.
     * 
     * @param musicId ID da música.
     * @throws SQLException se ocorrer erro na atualização.
     */
    public void incrementDislike(int musicId) throws SQLException {
        String sql = "UPDATE spotifei.music SET dislikes = dislikes + 1 WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }

    /**
     * Decrementa o número de dislikes de uma música.
     * 
     * @param musicId ID da música.
     * @throws SQLException se ocorrer erro na atualização.
     */
    public void decrementDislike(int musicId) throws SQLException {
        String sql = "UPDATE spotifei.music SET dislikes = dislikes - 1 WHERE music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, musicId);
            statement.executeUpdate();
        }
    }

    /**
     * Retorna as 5 músicas mais curtidas no banco de dados.
     * 
     * @return lista com as top 5 músicas com mais likes.
     * @throws SQLException se ocorrer erro na consulta.
     */
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

    /**
     * Retorna as 5 músicas mais descurtidas no banco de dados.
     * 
     * @return lista com as top 5 músicas com mais dislikes.
     * @throws SQLException se ocorrer erro na consulta.
     */
    public ArrayList<Music> getTop5LessLikedMusics() throws SQLException {
        String sql = "SELECT m.*, a.name AS artist_name " +
                     "FROM spotifei.music m " +
                     "JOIN spotifei.artist a ON m.artist_id = a.artist_id " +
                     "ORDER BY m.dislikes DESC " +
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

    /**
     * Retorna o número total de usuários cadastrados.
     * 
     * @return quantidade de usuários.
     * @throws SQLException se ocorrer erro na consulta.
     */
    public int getTotalUsers() throws SQLException {
        String sql = "SELECT COUNT(*) FROM spotifei.users";

        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        int users = 0;
        if (res.next()) {
            users = res.getInt(1);
        }

        res.close();
        statement.close();
        return users;
    }

    /**
     * Retorna o número total de músicas cadastradas.
     * 
     * @return quantidade de músicas.
     * @throws SQLException se ocorrer erro na consulta.
     */
    public int getTotalMusics() throws SQLException {
        String sql = "SELECT COUNT(*) FROM spotifei.music";

        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        int musics = 0;
        if (res.next()) {
            musics = res.getInt(1);
        }

        res.close();
        statement.close();
        return musics;
    }

    /**
     * Retorna os bytes do áudio de uma música com base no ID.
     * 
     * @param musicId ID da música.
     * @return array de bytes contendo o áudio ou {@code null} se não encontrado.
     * @throws SQLException se ocorrer erro na consulta.
     */
    public byte[] getMusicAudio(int musicId) throws SQLException {
        String sql = "SELECT music_audio FROM spotifei.music WHERE music_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, musicId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            return rs.getBytes("music_audio");
        }
        return null;
    }
}
