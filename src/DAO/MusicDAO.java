/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Music;

/**
 *
 * @author gabas
 */
public class MusicDAO {
    
    private Connection conn;

    public MusicDAO(Connection conn) {
        this.conn = conn;
    }
    
    public List<Music> searchMusic(String musicName)throws SQLException{
        String sql = "select * from spotifei.music where lower(title) like lower(?)";
        List<Music> musics = new ArrayList<>();
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "%" + musicName + "%");//busca parcial
        ResultSet result = statement.executeQuery();
        
        while(result.next()){
         musics.add(Music.fromResultSet(result));
        }
        return musics;
    }
    
}
