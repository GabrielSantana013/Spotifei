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
import model.Music;
import model.User;

/**
 * Classe responsável por gerenciar as operações de acesso a dados (DAO)
 * relacionadas aos usuários no banco de dados da aplicação Spotifei.
 * Realiza inserções, autenticação, atualização de histórico e interações
 * com músicas (like/dislike).
 * 
 * @author gabas
 */
public class UserDAO {

    private Connection conn;

    /**
     * Construtor que recebe uma conexão com o banco de dados.
     * 
     * @param conn conexão com o banco de dados PostgreSQL.
     */
    public UserDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Insere um novo usuário na tabela 'users'.
     * 
     * @param user objeto User contendo os dados a serem inseridos.
     * @throws SQLException se ocorrer erro durante a execução do comando SQL.
     */
    public void insert(User user) throws SQLException{
        String sql = "insert into spotifei.users(name, gender, birth_date,"
                + " login_user, password_user) values(?,?,?,?,?)";                
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getGender());
        statement.setDate(3, new java.sql.Date(user.getBirthDate().getTime()));
        statement.setString(4, user.getUserLogin());
        statement.setString(5, user.getUserPassword());
        statement.execute();
        conn.close();
    }

    /**
     * Realiza o login do usuário verificando suas credenciais.
     * 
     * @param user objeto User contendo login e senha.
     * @return ResultSet com os dados do usuário, caso encontrado.
     * @throws SQLException se ocorrer erro ao acessar o banco.
     */
    public ResultSet login(User user) throws SQLException {
        String sql = "select * from spotifei.users where login_user = ? AND"
                + " password_user = ?";   
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUserLogin());
        statement.setString(2, user.getUserPassword());
        statement.execute();
        return statement.getResultSet();
    }

    /**
     * Atualiza o histórico de músicas do usuário na base de dados.
     * 
     * @param user objeto User com o histórico a ser atualizado.
     * @throws SQLException se ocorrer erro na atualização.
     */
    public void updateHistoric(User user) throws SQLException {
        String sql = "UPDATE spotifei.users SET historic = ? WHERE login_user = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        String serializedHistoric = String.join(";", user.getHistoric());
        statement.setString(1, serializedHistoric);
        statement.setString(2, user.getUserLogin());
        statement.executeUpdate();
    }

    /**
     * Obtém a interação (like, dislike ou none) de um usuário com uma música.
     * 
     * @param userId ID do usuário.
     * @param musicId ID da música.
     * @return "like", "dislike" ou "none".
     * @throws SQLException se ocorrer erro na consulta.
     */
    public String getUserInteraction(int userId, int musicId) throws SQLException {
        String sql = "SELECT is_like FROM spotifei.user_like_music WHERE user_id = ?"
                + " AND music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, musicId);
            try (ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    return res.getBoolean("is_like") ? "like" : "dislike";
                }
            }
        }
        return "none";
    }

    /**
     * Insere ou atualiza a interação do usuário com uma música (like/dislike).
     * 
     * @param userId ID do usuário.
     * @param musicId ID da música.
     * @param is_like true para like, false para dislike.
     * @throws SQLException se ocorrer erro durante a operação.
     */
    public void insertOrUpdateInteraction(int userId, int musicId, boolean is_like) throws SQLException {
        String sql = "INSERT INTO spotifei.user_like_music (user_id, music_id, "
                + "is_like) VALUES (?, ?, ?) "
                + "ON CONFLICT (user_id, music_id) DO UPDATE SET is_like = "
                + "EXCLUDED.is_like";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, musicId);
            statement.setBoolean(3, is_like);
            statement.executeUpdate();
        }
    }

    /**
     * Retorna uma lista de músicas curtidas pelo usuário.
     * 
     * @param userId ID do usuário.
     * @return lista de objetos Music curtidos.
     * @throws SQLException se ocorrer erro durante a consulta.
     */
    public ArrayList<Music> getLikedMusics(int userId) throws SQLException {
        String sql = "SELECT m.* FROM spotifei.music m " +
                     "JOIN spotifei.user_like_music ulm ON m.music_id = ulm.music_id " +
                     "WHERE ulm.user_id = ? AND ulm.is_like = TRUE";
        ArrayList<Music> likedMusics = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int musicId = res.getInt("music_id");
                String musicName = res.getString("title");
                Music music = new Music(musicId, musicName);
                likedMusics.add(music);
            }
        }
        return likedMusics;
    }

    /**
     * Retorna uma lista de músicas que o usuário não curtiu (dislikes).
     * 
     * @param userId ID do usuário.
     * @return lista de objetos Music não curtidos.
     * @throws SQLException se ocorrer erro durante a consulta.
     */
    public ArrayList<Music> getDislikedMusics(int userId) throws SQLException {
        String sql = "SELECT m.* FROM spotifei.music m " +
                     "JOIN spotifei.user_like_music ulm ON m.music_id = ulm.music_id " +
                     "WHERE ulm.user_id = ? AND ulm.is_like = FALSE";
        ArrayList<Music> dislikedMusics = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet res = statement.executeQuery();
            while (res.next()) {                
                int musicId = res.getInt("music_id");
                String musicName = res.getString("title");
                Music music = new Music(musicId, musicName);               
                dislikedMusics.add(music);
            }
        }
        return dislikedMusics;
    }
}

