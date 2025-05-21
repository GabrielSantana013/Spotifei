/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Representa uma playlist criada por um usuário contendo uma lista de músicas.
 * Cada playlist tem um ID, nome, descrição, lista de músicas e está associada a um usuário.
 * 
 * @author unifgdias
 */
public class Playlist {
    
    private int playlistId;
    private String playlistName, playlistDescription;
    private ArrayList<String> playlistSongs;
    private int userId;

    /**
     * Construtor completo da playlist com todos os atributos.
     * 
     * @param playlistId ID da playlist.
     * @param playlistName Nome da playlist.
     * @param playlistDescription Descrição da playlist.
     * @param playlistSongs Lista de músicas na playlist.
     * @param userId ID do usuário dono da playlist.
     */
    public Playlist(int playlistId, String playlistName, String playlistDescription, ArrayList<String> playlistSongs, int userId) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.playlistSongs = playlistSongs;
        this.userId = userId;
    }    

    /**
     * Construtor da playlist sem associação direta ao usuário (sem userId).
     * 
     * @param playlistId ID da playlist.
     * @param playlistName Nome da playlist.
     * @param playlistDescription Descrição da playlist.
     * @param playlistSongs Lista de músicas.
     */
    public Playlist(int playlistId, String playlistName, String playlistDescription, ArrayList<String> playlistSongs) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.playlistSongs = playlistSongs;
    }

    /**
     * Construtor para criar uma playlist sem músicas inicialmente, mas associada a um usuário.
     * 
     * @param playlistName Nome da playlist.
     * @param playlistDescription Descrição da playlist.
     * @param userId ID do usuário dono da playlist.
     */
    public Playlist(String playlistName, String playlistDescription, int userId) {
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.userId = userId;
    }

    // Getters e setters
    
    /**
     * Obtém o ID da playlist.
     * 
     * @return ID da playlist.
     */
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     * Define o ID da playlist.
     * 
     * @param playlistId ID a ser definido.
     */
    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Obtém o nome da playlist.
     * 
     * @return Nome da playlist.
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Define o nome da playlist.
     * 
     * @param playlistName Nome a ser definido.
     */
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Obtém a descrição da playlist.
     * 
     * @return Descrição da playlist.
     */
    public String getPlaylistDescription() {
        return playlistDescription;
    }

    /**
     * Define a descrição da playlist.
     * 
     * @param playlistDescription Descrição a ser definida.
     */
    public void setPlaylistDescription(String playlistDescription) {
        this.playlistDescription = playlistDescription;
    }

    /**
     * Obtém a lista de músicas da playlist.
     * 
     * @return Lista de músicas.
     */
    public ArrayList<String> getPlaylistSongs() {
        return playlistSongs;
    }

    /**
     * Define a lista de músicas da playlist.
     * 
     * @param playlistSongs Lista de músicas a ser definida.
     */
    public void setPlaylistSongs(ArrayList<String> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    /**
     * Obtém o ID do usuário dono da playlist.
     * 
     * @return ID do usuário.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Define o ID do usuário dono da playlist.
     * 
     * @param userId ID do usuário a ser definido.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }    
    
    /**
     * Cria um objeto Playlist a partir de um ResultSet de uma consulta SQL.
     * Converte a string de músicas separadas por ";" em uma lista.
     * 
     * @param res ResultSet com dados da playlist.
     * @return Objeto Playlist criado a partir do ResultSet.
     * @throws SQLException Se ocorrer erro na leitura dos dados.
     */
    public static Playlist fromResultSet(ResultSet res) throws SQLException {
        String playlistSongsString = res.getString("playlist_songs");
        ArrayList<String> playlistSongs = new ArrayList<>();

        if (playlistSongsString != null && !playlistSongsString.isEmpty()) {
            playlistSongs = new ArrayList<>(Arrays.asList(playlistSongsString.split(";")));
        }
               

        return new Playlist(
            res.getInt("playlist_id"),
            res.getString("title"),
            res.getString("description"),            
            playlistSongs,
            res.getInt("user_id")              
        );
    }
        
}
