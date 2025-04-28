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
public class Artist extends Person{
    
    private int artistId;
    private String artistDescription;

    public Artist(int artistId, String artistDescription, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.artistId = artistId;
        this.artistDescription = artistDescription;
    }

    public Artist(String artistDescription, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.artistDescription = artistDescription;
    }
    
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistDescription() {
        return artistDescription;
    }

    public void setArtistDescription(String artistDescription) {
        this.artistDescription = artistDescription;
    }
    
}
