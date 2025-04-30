/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author unifgdias
 */
public class User extends Person{
    
    private String userLogin, userPassword;
    private int userId;

    private ArrayList<String> historic; //talvez adicionar isso no construtor?
    
    public User(String userLogin, String userPassword, int userId, String name, 
            String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userId = userId;
    }

    public User(String userLogin, String userPassword, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.userLogin = userLogin;
        this.userPassword = userPassword;
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
    
}
