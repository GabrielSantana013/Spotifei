/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa uma pessoa com nome, gênero e data de nascimento.
 * Fornece métodos para acessar e modificar esses dados, incluindo
 * formatação e parsing da data de nascimento.
 * 
 * @author unifgdias
 */
public class Person {
    
    private String name, gender;
    private Date birthDate;

    /**
     * Construtor padrão.
     */
    public Person(){}

    /**
     * Construtor que inicializa a pessoa com nome, gênero e data de nascimento.
     * 
     * @param name Nome da pessoa.
     * @param gender Gênero da pessoa.
     * @param birthDate Data de nascimento da pessoa.
     */
    public Person(String name, String gender, Date birthDate) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    /**
     * Retorna o nome da pessoa.
     * 
     * @return Nome da pessoa.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da pessoa.
     * 
     * @param name Novo nome a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o gênero da pessoa.
     * 
     * @return Gênero da pessoa.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Define o gênero da pessoa.
     * 
     * @param gender Novo gênero a ser definido.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Retorna a data de nascimento da pessoa.
     * 
     * @return Data de nascimento.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Define a data de nascimento da pessoa a partir de uma string no formato "dd/MM/yyyy".
     * 
     * @param dateStr String com a data formatada.
     * @throws ParseException Caso a string não esteja no formato esperado.
     */
    public void setBirthDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.birthDate = sdf.parse(dateStr);
    }
    
    /**
     * Retorna a data de nascimento formatada como string no formato "dd/MM/yyyy".
     * 
     * @return Data formatada como string.
     */
    public String getFormattedBirthDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(this.birthDate);
    }
    
}
