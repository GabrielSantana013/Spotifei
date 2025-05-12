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
 *
 * @author gabas
 */
public class UserDAO {
    
    private Connection conn;
    
    public UserDAO(Connection conn){
        this.conn = conn;
    }
    
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
    
    public ResultSet login(User user)throws SQLException{
        String sql = "select * from spotifei.users where login_user = ? AND"
                + " password_user = ?";   
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUserLogin());
        statement.setString(2, user.getUserPassword());
        statement.execute();
        ResultSet result = statement.getResultSet();
        return result;
    }
    
    public void updateHistoric(User user) throws SQLException {
        String sql = "UPDATE spotifei.users SET historic = ? WHERE login_user = ?";
        PreparedStatement statement = conn.prepareStatement(sql);

        // Serializa o histórico
        String serializedHistoric = String.join(";", user.getHistoric());

        statement.setString(1, serializedHistoric);
        statement.setString(2, user.getUserLogin());
        statement.executeUpdate();
    }
    
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
                // Adapte conforme seu modelo de Music
                likedMusics.add(music);
            }
        }
        return likedMusics;
    }

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
