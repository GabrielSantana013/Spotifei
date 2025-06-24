/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 * Representa um artista, que é um tipo especializado de {@link Person}.
 * Contém informações específicas do artista, como ID e descrição.
 * 
 * @author unifgdias
 */
public class Artist extends Person {
    
    private int artistId;
    private String artistDescription;

    /**
     * Construtor completo do artista com ID.
     * 
     * @param artistId ID do artista.
     * @param artistDescription Descrição do artista.
     * @param name Nome do artista.
     * @param gender Gênero do artista.
     * @param birthDate Data de nascimento do artista.
     */
    public Artist(int artistId, String artistDescription, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.artistId = artistId;
        this.artistDescription = artistDescription;
    }

    /**
     * Construtor do artista sem ID.
     * 
     * @param artistDescription Descrição do artista.
     * @param name Nome do artista.
     * @param gender Gênero do artista.
     * @param birthDate Data de nascimento do artista.
     */
    public Artist(String artistDescription, String name, String gender, Date birthDate) {
        super(name, gender, birthDate);
        this.artistDescription = artistDescription;
    }

    /**
     * Retorna o ID do artista.
     * 
     * @return ID do artista.
     */
    public int getArtistId() {
        return artistId;
    }

    /**
     * Define o ID do artista.
     * 
     * @param artistId Novo ID do artista.
     */
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    /**
     * Retorna a descrição do artista.
     * 
     * @return Descrição do artista.
     */
    public String getArtistDescription() {
        return artistDescription;
    }

    /**
     * Define a descrição do artista.
     * 
     * @param artistDescription Nova descrição do artista.
     */
    public void setArtistDescription(String artistDescription) {
        this.artistDescription = artistDescription;
    }
    
}
