/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author unifgdias
 */
public class User extends Person{
    
    private String userLogin, userPassword;
    private int userId;
    private boolean adm;
            
    private ArrayList<String> historic; //talvez adicionar isso no construtor?

    
    public User(String userLogin, String userPassword, int userId, ArrayList<String> historic, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userId = userId;
        this.historic = historic;
        this.adm = false;
    }

    public User(String userLogin, String userPassword, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.adm = false;
    }
    
    public User(String userLogin, String userPassword){
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.adm = false;
    }
    
    
    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAdm() {
        return adm;
    }

    public ArrayList<String> getHistoric() {
        return historic;
    }

    public void setHistoric(ArrayList<String> historic) {
        this.historic = historic;
    }
    
    //esse método cria um usuário com os resultados da consulta (usar para login)
    public static User fromResultSet(ResultSet res) throws SQLException {
        
        String historicString = res.getString("historic");
        ArrayList<String> historicList = new ArrayList<>();
    
        if (historicString != null && !historicString.isEmpty()) {
            historicList = new ArrayList<>(Arrays.asList(historicString.split(";")));
        }
        
        Date birthDate = new Date(res.getDate("birth_date").getTime());
                
        return new User(
            res.getString("login_user"),
            res.getString("password_user"),
            res.getInt("user_id"),
            historicList,
            res.getString("name"),
            res.getString("gender"),
            birthDate
        );
    }
    
    public void addToHistoric(String musicTitle) {
        
        if (historic == null) {
            historic = new ArrayList<>();
        }

        historic.remove(musicTitle); // remove se já existir p/
        historic.add(0, musicTitle); // adiciona no topo

        if (historic.size() > 10) {
            historic.remove(historic.size() - 1);
        }
    }

    @Override
    public String toString() {
        return "User{" + "userLogin=" + userLogin + ", userPassword=" + 
                userPassword + ", userId=" + userId + ", historic=" + 
                historic + ", name=" + super.getName() + 
                ", gender=" + super.getGender()+
                ", birthDate=" + super.getFormattedBirthDate()+
                '}';
    }
}
