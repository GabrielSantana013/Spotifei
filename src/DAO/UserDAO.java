/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
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
    
    public Boolean getUserLikeStatus(int userId, int musicId) throws SQLException {
        String sql = "SELECT is_like FROM spotifei.user_like_music WHERE user_id = ? AND music_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, musicId);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getBoolean("is_like"); // true = like, false = dislike
            }
        }
        return null; // nunca curtiu/descurtiu
    }

    // Adiciona ou atualiza a reação do usuário
    public void insertOrUpdateLike(int userId, int musicId, boolean isLike) throws SQLException {
        Boolean currentStatus = getUserLikeStatus(userId, musicId);

        if (currentStatus == null) {
            // Novo like/dislike
            String sql = "INSERT INTO spotifei.user_like_music (user_id, "
                    + "music_id, is_like) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, musicId);
            statement.setBoolean(3, isLike);
            statement.executeUpdate();
            
        } else if (currentStatus != isLike) {
            // Mudou de like para dislike ou vice-versa
            String sql = "UPDATE spotifei.user_like_music SET is_like = ? "
                    + "WHERE user_id = ? AND music_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBoolean(1, isLike);
            statement.setInt(2, userId);
            statement.setInt(3, musicId);
            statement.executeUpdate();
            
        } else {
            // Remove like/dislike (toggle)
            String sql = "DELETE FROM user_like_music WHERE user_id = ? AND music_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, musicId);
            statement.executeUpdate();
            
        }
    }
}
