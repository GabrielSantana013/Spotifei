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
    
    public ResultSet search(User user)throws SQLException{
        String sql = "select * from spotifei.users where login_user = ? AND"
                + " password_user = ?";   
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUserLogin());
        statement.setString(2, user.getUserPassword());
        statement.execute();
        ResultSet result = statement.getResultSet();
        return result;
    }
}
