/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Representa um administrador, que é um tipo especializado de {@link Person}.
 * Contém informações específicas de administrador, como login, senha e ID.
 * 
 * @author unifgdias
 */
public class Adm extends Person{

    private int admId;
    private String admLogin, admPassword;
    private boolean adm;

    /**
     * Construtor completo do administrador, incluindo dados pessoais e credenciais.
     * 
     * @param admId ID do administrador.
     * @param admLogin Login do administrador.
     * @param admPassword Senha do administrador.
     * @param name Nome do administrador.
     * @param gender Gênero do administrador.
     * @param birthDate Data de nascimento do administrador.
     */
    public Adm(int admId, String admLogin, String admPassword, String name, 
            String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.admId = admId;
        this.admLogin = admLogin;
        this.admPassword = admPassword;
        this.adm = true;
    }

    /**
     * Construtor do administrador sem ID.
     * 
     * @param admLogin Login do administrador.
     * @param admPassword Senha do administrador.
     * @param name Nome do administrador.
     * @param gender Gênero do administrador.
     * @param birthDate Data de nascimento do administrador.
     */
    public Adm(String admLogin, String admPassword, String name, String gender, 
            Date birthDate) {
        super(name, gender, birthDate);
        this.admLogin = admLogin;
        this.admPassword = admPassword;
        this.adm = true;
    }

    /**
     * Construtor com apenas login e senha.
     * 
     * @param admLogin Login do administrador.
     * @param admPassword Senha do administrador.
     */
    public Adm(String admLogin, String admPassword) {
        this.admLogin = admLogin;
        this.admPassword = admPassword;
        this.adm = true;
    }

    /**
     * Construtor padrão.
     */
    public Adm() {
    }    

    /**
     * Retorna o ID do administrador.
     * 
     * @return ID do administrador.
     */
    public int getAdmId() {
        return admId;
    }

    /**
     * Define o ID do administrador.
     * 
     * @param admId Novo ID a ser definido.
     */
    public void setAdmId(int admId) {
        this.admId = admId;
    }

    /**
     * Retorna o login do administrador.
     * 
     * @return Login do administrador.
     */
    public String getAdmLogin() {
        return admLogin;
    }

    /**
     * Retorna a senha do administrador.
     * 
     * @return Senha do administrador.
     */
    public String getAdmPassword() {
        return admPassword;
    }

    /**
     * Retorna se o usuário é administrador (sempre true).
     * 
     * @return true indicando que é administrador.
     */
    public boolean isAdm() {
        return adm;
    }  

    /**
     * Cria um objeto Adm a partir de um objeto User, se for administrador.
     * 
     * @param user Objeto User.
     * @return Objeto Adm correspondente.
     * @throws IllegalArgumentException Caso o usuário não seja administrador.
     */
    public static Adm fromUser(User user) {
        if (!user.isAdm()) {
            throw new IllegalArgumentException("Usuário não é administrador.");
        }

        Adm adm = new Adm();
        adm.admId = user.getUserId();              // ou mapeie como preferir
        adm.admLogin = user.getUserLogin();
        adm.admPassword = user.getUserPassword();
        adm.adm = true;

        return adm;
    }

    /**
     * Cria um objeto Adm a partir de um {@link ResultSet} de uma consulta SQL.
     * 
     * @param res ResultSet da consulta.
     * @return Objeto Adm populado com os dados do ResultSet.
     * @throws SQLException Caso ocorra erro ao acessar os dados do ResultSet.
     */
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
