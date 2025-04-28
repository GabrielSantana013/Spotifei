/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author unifgdias
 */
public class Music {
    
    private int musicId, likes, deslikes, duration;
    private String musicTitle, musicDescription;

    public Music(int musicId, int likes, int deslikes, int duration, String musicTitle, String musicDescription) {
        this.musicId = musicId;
        this.likes = likes;
        this.deslikes = deslikes;
        this.duration = duration;
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;
    }

    public Music(int likes, int deslikes, int duration, String musicTitle, String musicDescription) {
        this.likes = likes;
        this.deslikes = deslikes;
        this.duration = duration;
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;
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
    
}
