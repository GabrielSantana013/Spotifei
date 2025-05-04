/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auth;

import DAO.DbConnection;
import DAO.UserDAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import model.Adm;

/**
 *
 * @author Gabriel
 */
public class AdmAuthenticator implements Authenticator<Adm>{

    @Override
    public Adm authenticate(String login, String password){
    
        /*WIP: Substituir o UserDAO pelo AdmDAO assim que estiver pronto.*/
        /*try{
            Connection conn = new DbConnection().getConnection();
            UserDAO dao = new UserDAO(conn);
            ResultSet result = dao.search(new Adm(login, password));
            
            if(result.next()){
                return Adm.fromResultSet(result);
            }else{
                return null;
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }*/
        return null;
    }
        
}

