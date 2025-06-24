package model;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Representa um usuário do sistema que herda de Person.
 * Contém informações de login, histórico de músicas, músicas curtidas e não curtidas, e o status de administrador.
 * 
 * @author unifgdias
 */
public class User extends Person {
    
    private String userLogin;
    private String userPassword;
    private int userId;
    private boolean adm;
    
    private ArrayList<String> historic;
    private ArrayList<Music> likedMusics = new ArrayList<>();
    private ArrayList<Music> dislikedMusics = new ArrayList<>();
    
    /**
     * Construtor completo do usuário.
     * 
     * @param userLogin Login do usuário.
     * @param userPassword Senha do usuário.
     * @param userId ID do usuário.
     * @param historic Histórico de músicas tocadas (títulos).
     * @param likedMusics Lista de músicas curtidas.
     * @param dislikedMusics Lista de músicas não curtidas.
     * @param name Nome do usuário (herdado de Person).
     * @param gender Gênero do usuário (herdado de Person).
     * @param birthDate Data de nascimento do usuário (herdado de Person).
     * @param isAdm Indica se o usuário é administrador.
     */
    public User(String userLogin, String userPassword, int userId,
            ArrayList<String> historic, ArrayList<Music> likedMusics,
            ArrayList<Music> dislikedMusics, String name, String gender, 
            Date birthDate, boolean isAdm) {
        super(name, gender, birthDate);
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userId = userId;
        this.historic = historic;
        this.likedMusics = likedMusics;
        this.dislikedMusics = dislikedMusics;
        this.adm = isAdm;
    }

    /**
     * Construtor para criar usuário com dados básicos e sem listas de músicas.
     * O usuário não é administrador por padrão.
     * 
     * @param userLogin Login do usuário.
     * @param userPassword Senha do usuário.
     * @param name Nome do usuário.
     * @param gender Gênero do usuário.
     * @param birthDate Data de nascimento.
     */
    public User(String userLogin, String userPassword, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.adm = false;
    }
    
    /**
     * Construtor mais simples para autenticação ou criação rápida, sem dados pessoais.
     * O usuário não é administrador por padrão.
     * 
     * @param userLogin Login do usuário.
     * @param userPassword Senha do usuário.
     */
    public User(String userLogin, String userPassword){
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.adm = false;
    }
      
    // Getters e setters
    
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
    
    public void setLikedMusics(ArrayList<Music> likedMusics) {
        this.likedMusics = likedMusics;
    }

    public void setDislikedMusics(ArrayList<Music> dislikedMusics) {
        this.dislikedMusics = dislikedMusics;
    }

    public ArrayList<Music> getLikedMusics() {
        return likedMusics;
    }

    public ArrayList<Music> getDislikedMusics() {
        return dislikedMusics;
    }

    /**
     * Retorna a quantidade de músicas curtidas.
     * 
     * @return Número de músicas curtidas, 0 se a lista for nula.
     */
    public int getLikedCount() {
        if(likedMusics != null)
            return likedMusics.size();
        else
            return 0;
    }

    /**
     * Retorna a quantidade de músicas não curtidas.
     * 
     * @return Número de músicas não curtidas, 0 se a lista for nula.
     */
    public int getDislikedCount() {
        if(dislikedMusics != null)
            return dislikedMusics.size();
        else
            return 0;
    }
    
    /**
     * Cria um objeto User a partir de um ResultSet.
     * Faz a conversão do histórico de músicas de String para ArrayList.
     * Inicializa listas de liked e disliked vazias.
     * 
     * @param res ResultSet da consulta SQL.
     * @return Objeto User criado.
     * @throws SQLException Caso ocorra erro no acesso ao ResultSet.
     */
    public static User fromResultSet(ResultSet res) throws SQLException {
        String historicString = res.getString("historic");
        ArrayList<String> historicList = new ArrayList<>();

        if (historicString != null && !historicString.isEmpty()) {
            historicList = new ArrayList<>(Arrays.asList(historicString.split(";")));
        }

        Date birthDate = new Date(res.getDate("birth_date").getTime());

        ArrayList<Music> likedMusics = new ArrayList<>();
        ArrayList<Music> dislikedMusics = new ArrayList<>();

        return new User(
            res.getString("login_user"),
            res.getString("password_user"),
            res.getInt("user_id"),
            historicList,
            likedMusics,
            dislikedMusics,
            res.getString("name"),
            res.getString("gender"),
            birthDate,
            res.getBoolean("is_adm")
        );
    }

    /**
     * Adiciona uma música ao histórico do usuário.
     * Se a música já estiver no histórico, ela será movida para o topo.
     * O histórico mantém no máximo 10 músicas.
     * 
     * @param musicTitle Título da música a adicionar.
     */
    public void addToHistoric(String musicTitle) {
        if (historic == null) {
            historic = new ArrayList<>();
        }

        historic.remove(musicTitle); // remove se já existir para evitar duplicação
        historic.add(0, musicTitle); // adiciona no topo

        if (historic.size() > 10) {
            historic.remove(historic.size() - 1); // remove o mais antigo
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
