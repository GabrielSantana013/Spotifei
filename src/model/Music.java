/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author unifgdias
 */
public class Music {
    
    private int musicId, likes, deslikes, duration;
    private String musicTitle, musicDescription, genre, artistName;
    private byte[] musicPhoto, musicAudio;

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

    public Music(int likes, int deslikes, int duration, String musicTitle, 
            String musicDescription) {
        this.likes = likes;
        this.deslikes = deslikes;
        this.duration = duration;
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;
    }
    
    public Music(int musicId, String title) {
        this.musicId = musicId;
        this.musicTitle = title;
    }

    public Music(String musicTitle, String artistName, byte[] musicPhoto) {
        this.musicTitle = musicTitle;
        this.artistName = artistName;
        this.musicPhoto = musicPhoto;
    }

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
