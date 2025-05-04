/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auth;

import DAO.DbConnection;
import DAO.UserDAO;
import model.User;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Gabriel
 */
public class UserAuthenticator implements Authenticator<User>{

    @Override
    public User authenticate(String login, String password){
    
        try{
            Connection conn = new DbConnection().getConnection();
            UserDAO dao = new UserDAO(conn);
            ResultSet result = dao.search(new User(login, password));
            
            if(result.next()){
                return User.fromResultSet(result);
            }else{
                return null;
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
        
}
