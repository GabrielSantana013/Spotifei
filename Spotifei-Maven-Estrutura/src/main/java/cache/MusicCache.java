/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Music;

/**
 * Classe responsável por armazenar em cache objetos {@code Music}, permitindo
 * acesso eficiente por ID ou título. Funciona como uma camada intermediária para
 * evitar consultas desnecessárias ao banco de dados.
 * 
 * O cache pode ser carregado, limpo, ou atualizado dinamicamente.
 * 
 * @author Gabriel
 */
public class MusicCache {

    private static final Map<Integer, Music> musicById = new HashMap<>();
    private static final Map<String, Music> musicByTitle = new HashMap<>();

    /**
     * Carrega uma lista de músicas no cache, indexando por ID e título (em minúsculas).
     * 
     * @param musics Lista de objetos {@code Music} a serem armazenados no cache.
     */
    public static void loadCache(List<Music> musics) {
        for (Music music : musics) {
            musicById.put(music.getMusicId(), music);
            musicByTitle.put(music.getMusicTitle().toLowerCase(), music);
        }
    }

    /**
     * Recupera uma música do cache com base em seu ID.
     * 
     * @param id O ID da música.
     * @return O objeto {@code Music} correspondente, ou {@code null} se não encontrado.
     */
    public static Music getMusicById(int id) {
        return musicById.get(id);
    }

    /**
     * Recupera uma música do cache com base em seu título (case insensitive).
     * 
     * @param title O título da música.
     * @return O objeto {@code Music} correspondente, ou {@code null} se não encontrado.
     */
    public static Music getMusicByTitle(String title) {
        return musicByTitle.get(title.toLowerCase());
    }

    /**
     * Retorna uma lista imutável com todas as músicas armazenadas no cache.
     * 
     * @return Lista de objetos {@code Music}.
     */
    public static List<Music> getAllMusics() {
        return Collections.unmodifiableList(new ArrayList<>(musicById.values()));
    }

    /**
     * Remove uma música do cache com base em seu ID.
     * 
     * @param musicId O ID da música a ser removida.
     */
    public static void removeById(int musicId) {
        Music music = musicById.remove(musicId);
        if (music != null) {
            musicByTitle.remove(music.getMusicTitle().toLowerCase());
        }
    }

    /**
     * Insere uma nova música no cache, indexando-a por ID e título.
     * 
     * @param music O objeto {@code Music} a ser inserido.
     */
    public static void insertMusic(Music music) {
        musicById.put(music.getMusicId(), music);
        musicByTitle.put(music.getMusicTitle().toLowerCase(), music);
    }

    /**
     * Limpa completamente o cache, removendo todas as músicas armazenadas.
     */
    public static void clear() {
        musicById.clear();
        musicByTitle.clear();
    }

    /**
     * Remove uma música do cache com base em seu título (case insensitive).
     * 
     * @param title O título da música a ser removida.
     */
    public static void removeByTitle(String title) {
        if (title == null) return;

        String key = title.toLowerCase();
        Music music = musicByTitle.remove(key);
        if (music != null) {
            musicById.remove(music.getMusicId());
        }
    }
}
