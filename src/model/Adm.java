/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author unifgdias
 */
public class Adm extends Person{

    private int admId;
    private String admLogin, admPassword;

    public Adm(int admId, String admLogin, String admPassword, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.admId = admId;
        this.admLogin = admLogin;
        this.admPassword = admPassword;
    }

    public Adm(String admLogin, String admPassword, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
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
    
}
