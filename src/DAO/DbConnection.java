/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 *
 * @author gabas
 */
public class DbConnection {
        
    public Connection getConnection() throws SQLException{
    
        Properties prop = new Properties();
        try{InputStream input = DbConnection.class.getClassLoader()
                .getResourceAsStream("DAO/config.properties");
            prop.load(input);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
        String url = prop.getProperty("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");
        
        Connection conexao = DriverManager.getConnection(url, user, password);
        if (conexao != null && !conexao.isClosed()) {
                System.out.println("DbConnection works");
            } else {
                System.out.println("DbConnection isn't working");
            }
        return conexao;       
    }
}

