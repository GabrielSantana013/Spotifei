/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Representa uma música com informações como título, descrição, gênero, artista, 
 * número de likes e dislikes, duração, além de foto e áudio em formato binário.
 * 
 * @author unifgdias
 */
public class Music {
    
    private int musicId, likes, deslikes, duration, artistId;
    private String musicTitle, musicDescription, genre, artistName;
    private byte[] musicPhoto, musicAudio;

    /**
     * Construtor completo da música, incluindo dados básicos e mídia.
     * 
     * @param musicId ID da música.
     * @param likes Número de likes.
     * @param deslikes Número de dislikes.
     * @param duration Duração da música em segundos.
     * @param musicTitle Título da música.
     * @param musicDescription Descrição da música.
     * @param genre Gênero musical.
     * @param artistName Nome do artista.
     */
    public Music(int musicId, int likes, int deslikes, int duration, 
            String musicTitle, String musicDescription, String genre,
            String artistName) {
        this.musicId = musicId;
        this.likes = likes;
        this.deslikes = deslikes;
        this.duration = duration;
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;
        this.genre = genre;
        this.artistName = artistName;
    }

    /**
     * Construtor para música com dados essenciais.
     * 
     * @param likes Número de likes.
     * @param deslikes Número de dislikes.
     * @param duration Duração em segundos.
     * @param musicTitle Título da música.
     * @param musicDescription Descrição da música.
     */
    public Music(int likes, int deslikes, int duration, String musicTitle, 
            String musicDescription) {
        this.likes = likes;
        this.deslikes = deslikes;
        this.duration = duration;
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;
    }
    
    /**
     * Construtor com ID e título.
     * 
     * @param musicId ID da música.
     * @param title Título da música.
     */
    public Music(int musicId, String title) {
        this.musicId = musicId;
        this.musicTitle = title;
    }

    /**
     * Construtor com título, artista e foto.
     * 
     * @param musicTitle Título da música.
     * @param artistName Nome do artista.
     * @param musicPhoto Foto da música em bytes.
     */
    public Music(String musicTitle, String artistName, byte[] musicPhoto) {
        this.musicTitle = musicTitle;
        this.artistName = artistName;
        this.musicPhoto = musicPhoto;
    }

    /**
     * Construtor completo com todos os atributos.
     * 
     * @param musicId ID da música.
     * @param likes Número de likes.
     * @param deslikes Número de dislikes.
     * @param duration Duração em segundos.
     * @param musicTitle Título da música.
     * @param musicDescription Descrição da música.
     * @param genre Gênero musical.
     * @param artistName Nome do artista.
     * @param musicPhoto Foto da música em bytes.
     * @param musicAudio Áudio da música em bytes.
     */
    public Music(int musicId, int likes, int deslikes, int duration, 
            String musicTitle, String musicDescription, String genre, 
            String artistName, byte[] musicPhoto, byte[] musicAudio) {
        this.musicId = musicId;
        this.likes = likes;
        this.deslikes = deslikes;
        this.duration = duration;
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;
        this.genre = genre;
        this.artistName = artistName;
        this.musicPhoto = musicPhoto;
        this.musicAudio = musicAudio;
    }

    /**
     * Construtor para criação com dados e associação a artista via ID.
     * 
     * @param likes Número de likes.
     * @param deslikes Número de dislikes.
     * @param duration Duração em segundos.
     * @param artistId ID do artista.
     * @param musicTitle Título da música.
     * @param musicDescription Descrição da música.
     * @param genre Gênero musical.
     * @param musicPhoto Foto da música em bytes.
     * @param musicAudio Áudio da música em bytes.
     */
    public Music(int likes, int deslikes, int duration, int artistId, 
            String musicTitle, String musicDescription, String genre,
            byte[] musicPhoto, byte[] musicAudio) {
        this.likes = likes;
        this.deslikes = deslikes;
        this.duration = duration;
        this.artistId = artistId;
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;
        this.genre = genre;
        this.musicPhoto = musicPhoto;
        this.musicAudio = musicAudio;
    }
    
      public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDeslikes() {
        return deslikes;
    }

    public void setDeslikes(int deslikes) {
        this.deslikes = deslikes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusicDescription() {
        return musicDescription;
    }

    public void setMusicDescription(String musicDescription) {
        this.musicDescription = musicDescription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public byte[] getMusicPhoto() {
        return musicPhoto;
    }

    public void setMusicPhoto(byte[] musicPhoto) {
        this.musicPhoto = musicPhoto;
    }

    public byte[] getMusicAudio() {
        return musicAudio;
    }

    public void setMusicAudio(byte[] musicAudio) {
        this.musicAudio = musicAudio;
    }
        
    /**
     * Cria um objeto Music a partir de um ResultSet de uma consulta SQL.
     * 
     * @param res ResultSet com os dados da música.
     * @return Objeto Music criado a partir do ResultSet.
     * @throws SQLException se ocorrer erro na leitura do ResultSet.
     */
    public static Music fromResultSet(ResultSet res) throws SQLException {
        
        return new Music(
            res.getInt("music_id"),            
            res.getInt("likes"),            
            res.getInt("dislikes"),
            res.getInt("duration"),
            res.getString("title"),
            res.getString("description"),                        
            res.getString("genre"),
            res.getString("artist_name"),
            res.getBytes("music_photo"),
            res.getBytes("music_audio")
        );
    }
    
}

  

