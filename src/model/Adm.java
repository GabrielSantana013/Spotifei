/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author unifgdias
 */
public class Adm extends Person{

    private int admId;
    private String admLogin, admPassword;

    public Adm(int admId, String admLogin, String admPassword, String name, 
            String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.admId = admId;
        this.admLogin = admLogin;
        this.admPassword = admPassword;
    }

    public Adm(String admLogin, String admPassword, String name, String gender, 
            Date birthDate) {
        super(name, gender, birthDate);
        this.admLogin = admLogin;
        this.admPassword = admPassword;
    }

    public Adm(String admLogin, String admPassword) {
        this.admLogin = admLogin;
        this.admPassword = admPassword;
    }
 
    public int getAdmId() {
        return admId;
    }

    public void setAdmId(int admId) {
        this.admId = admId;
    }

    public String getAdmLogin() {
        return admLogin;
    }

    public String getAdmPassword() {
        return admPassword;
    }
    
    
    //esse método cria um adm com os resultados da consulta (usar para login)
    public static Adm fromResultSet(ResultSet res) throws SQLException {
 
        Date birthDate = new Date(res.getDate("birth_date").getTime());
        
        return new Adm(
            res.getInt("adm_id"),
            res.getString("login_adm"),
            res.getString("password_adm"),
            res.getString("name"),
            res.getString("gender"),
            birthDate
        );
    }
    
}
