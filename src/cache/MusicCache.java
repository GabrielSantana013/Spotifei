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
 *
 * @author Gabriel
 */
public class MusicCache {

    private static final Map<Integer, Music> musicById = new HashMap<>();
    private static final Map<String, Music> musicByTitle = new HashMap<>();

    public static void loadCache(List<Music> musics) {
        for (Music music : musics) {
            musicById.put(music.getMusicId(), music);
            musicByTitle.put(music.getMusicTitle().toLowerCase(), music);
        }
    }

    public static Music getMusicById(int id) {
        return musicById.get(id);
    }

    public static Music getMusicByTitle(String title) {
        return musicByTitle.get(title.toLowerCase());
    }

    public static List<Music> getAllMusics() {
        return Collections.unmodifiableList(new ArrayList<>(musicById.values()));
    }


    public static void clear() {
        musicById.clear();
        musicByTitle.clear();
    }

}
