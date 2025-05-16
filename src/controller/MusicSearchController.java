package controller;

import DAO.DbConnection;
import DAO.MusicDAO;
import DAO.PlaylistDAO;
import DAO.UserDAO;
import cache.MusicCache;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import model.Music;
import model.Playlist;
import model.User;
import static utils.ImageProcessor.byteArrayToImage;
import view.SearchWindow;
import view.customDialogs.CustomJDialog;

public class MusicSearchController {
    private SearchWindow view;
    private ArrayList<Music> currentMusics;
    private ArrayList<Playlist> currentPlaylists;
    private User user;
    private Music selectedMusic;

    public MusicSearchController(SearchWindow view, User user) {
        this.view = view;
        this.user = user;
        this.currentMusics = new ArrayList<>();
        this.selectedMusic = null;

        displayHistoric();

        this.view.getjList1().addListSelectionListener(e -> onMusicSelected(e));
        this.view.getBtt_like().addActionListener(e -> likeSelectedMusic());
        this.view.getBtt_dislike().addActionListener(e -> dislikeSelectedMusic());
        this.view.getBtt_addPlaylist().addActionListener(e -> addMusicToPlaylist());
    }


    public void searchMusic() {
        String musicName = view.getSearch_name().getText();

        if (musicName == null || musicName.trim().isEmpty()) {            
            displayHistoric();
            return;
        }

       String searchLower = musicName.toLowerCase();
        List<Music> musics = MusicCache.getAllMusics().stream()
                .filter(m -> 
                    m.getMusicTitle().toLowerCase().contains(searchLower) ||
                    m.getArtistName().toLowerCase().contains(searchLower) ||
                    m.getGenre().toLowerCase().contains(searchLower)
                )
                .collect(Collectors.toList());

        if (musics.isEmpty()) {
            CustomJDialog.showCustomDialog("Resultado", "Nenhuma música encontrada.");
            view.getjList1().setListData(new String[0]);
            currentMusics.clear();

            displayHistoric();                

        } else {
            currentMusics = new ArrayList<>(musics);

            String[] titles = currentMusics.stream()
                    .map(Music::getMusicTitle)
                    .toArray(String[]::new);
            view.getjList1().setListData(titles);
        }
    }
     
    private void displayHistoric() {
        List<String> historicTitles = user.getHistoric();

        if (historicTitles != null && !historicTitles.isEmpty()) {
            List<Music> musicsFromHistoric = new ArrayList<>();
               for (String title : historicTitles) {                  
                    Music m = MusicCache.getAllMusics().stream()
                    .filter(music -> music.getMusicTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElse(null);
                    if (m != null) {
                        musicsFromHistoric.add(m);
                    }
                }
                currentMusics = new ArrayList<>(musicsFromHistoric);
                String[] titles = musicsFromHistoric.stream()
                        .map(Music::getMusicTitle)
                        .toArray(String[]::new);
                view.getjList1().setListData(titles);

        } else {
            view.getjList1().setListData(new String[0]);
        }
    }
    
    private void updateMusicLabels(Music music){
        view.setSearch_pnl_musicInfoVisibility(Boolean.TRUE);
        
        int duration = music.getDuration();
        String formattedDuration = String.format("%d:%02d", duration / 60, duration % 60);
        
        view.getLbl_musicTitle().setText(music.getMusicTitle());
        view.getLbl_musicGenre().setText(music.getGenre());
        view.getLbl_musicDuration().setText(formattedDuration);
        view.getLbl_musicLikes().setText(String.valueOf(music.getLikes()));
        view.getLbl_musicDislikes().setText(String.valueOf(music.getDeslikes()));
        view.getLbl_musicDescription().setText(music.getMusicDescription());
        view.getLbl_musicArtist().setText(music.getArtistName());
        
        try{
            BufferedImage image = byteArrayToImage(music.getMusicPhoto());
            view.getArtist_photo().setIcon(new ImageIcon(image));
        }catch(IOException e){
            CustomJDialog.showCustomDialog("Erro", "Erro ao carregar a foto do artista.");
            e.printStackTrace();
        }   
    }
    
    private void likeSelectedMusic() {
        if (selectedMusic != null) {
            try (Connection conn = new DbConnection().getConnection()) {
                UserDAO userDAO = new UserDAO(conn);
                MusicDAO musicDAO = new MusicDAO(conn);

                String interaction = userDAO.getUserInteraction(user.getUserId(), selectedMusic.getMusicId());

                if ("like".equals(interaction)) {
                    CustomJDialog.showCustomDialog("Aviso", "Você já curtiu essa música.");
                    return;
                } else if ("dislike".equals(interaction)) {
                    musicDAO.decrementDislike(selectedMusic.getMusicId());
                }

                userDAO.insertOrUpdateInteraction(user.getUserId(), selectedMusic.getMusicId(), true);
                musicDAO.incrementLike(selectedMusic.getMusicId());

                Music updatedMusic = musicDAO.getMusicById(selectedMusic.getMusicId());
                selectedMusic = updatedMusic;
                updateMusicLabels(updatedMusic);
                view.getBtt_dislike().setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/dislike.png")));
                view.getBtt_like().setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/like_green.png")));

            } catch (SQLException ex) {
                CustomJDialog.showCustomDialog("Erro!", "Erro ao curtir a música.");
                ex.printStackTrace();
            }
        } else {
            CustomJDialog.showCustomDialog("Aviso", "Nenhuma música selecionada.");
        }
    }
    
    private void dislikeSelectedMusic() {
        if (selectedMusic != null) {
            try (Connection conn = new DbConnection().getConnection()) {
                UserDAO userDAO = new UserDAO(conn);
                MusicDAO musicDAO = new MusicDAO(conn);

                String interaction = userDAO.getUserInteraction(user.getUserId(), selectedMusic.getMusicId());

                if ("dislike".equals(interaction)) {
                    CustomJDialog.showCustomDialog("Aviso", "Você já descurtiu essa música.");
                    return;
                } else if ("like".equals(interaction)) {
                    musicDAO.decrementLike(selectedMusic.getMusicId());
                }

                userDAO.insertOrUpdateInteraction(user.getUserId(), selectedMusic.getMusicId(), false);
                musicDAO.incrementDislike(selectedMusic.getMusicId());

                Music updatedMusic = musicDAO.getMusicById(selectedMusic.getMusicId());
                selectedMusic = updatedMusic;
                updateMusicLabels(updatedMusic);
                view.getBtt_like().setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/like.png")));
                view.getBtt_dislike().setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/dislike_red.png")));

            } catch (SQLException ex) {
                CustomJDialog.showCustomDialog("Erro!", "Erro ao descurtir a música.");
                ex.printStackTrace();
            }
        } else {
            CustomJDialog.showCustomDialog("Aviso", "Nenhuma música selecionada.");
        }
    }

    private void onMusicSelected(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedIndex = view.getjList1().getSelectedIndex();

            if (selectedIndex >= 0 && selectedIndex < currentMusics.size()) {
                selectedMusic = currentMusics.get(selectedIndex);
                updateMusicLabels(selectedMusic);

                user.addToHistoric(selectedMusic.getMusicTitle());
                displayHistoric();

                try (Connection conn = new DbConnection().getConnection()) {
                    UserDAO dao = new UserDAO(conn);
                    dao.updateHistoric(user);
                } catch (SQLException ex) {
                    CustomJDialog.showCustomDialog("Erro!", "Erro ao atualizar o histórico no banco de dados.");
                }
            }
        }
    }

    public void loadUserPlaylists(){
        
        try(Connection conn = new DbConnection().getConnection()){
            PlaylistDAO dao = new PlaylistDAO(conn);
            currentPlaylists = dao.getPlaylistsByUserId(user.getUserId());
            
            DefaultListModel<String> model = new DefaultListModel();
            
            for(Playlist p : currentPlaylists){
                model.addElement(p.getPlaylistName());
            }
            
            if(currentPlaylists != null){
                view.getList_playlists().setModel(model);
            }
            
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao carregar playlists");
            e.getMessage();
        }
    }
    
    public void addMusicToPlaylist(){
        
        int playlistIndex = view.getList_playlists().getSelectedIndex();
                      
        if(playlistIndex < 0){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao selecionar playlist");
            return;
        }
        
        ArrayList<String> playlistSongs = currentPlaylists.get(playlistIndex).getPlaylistSongs();        
        
        if(selectedMusic != null){        
            if(!playlistSongs.contains(selectedMusic.getMusicTitle())){
                playlistSongs.add(selectedMusic.getMusicTitle());
            }
            else{
                CustomJDialog.showCustomDialog("Aviso!", "Esta música já está na playlist.");
                return;
            }            
        }         
             
        String playlistSongsString = String.join(";", playlistSongs);        
        
        try(Connection conn = new DbConnection().getConnection()){
            PlaylistDAO dao = new PlaylistDAO(conn);
            dao.updatePlaylistSongs(currentPlaylists.get(playlistIndex).getPlaylistId(), playlistSongsString);
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao atualizar playlist");
        }
        
        CustomJDialog.showCustomDialog("Aviso!", "Musica adicionada na playlist.");
    }
    
    
    public int getAtualIndex(ArrayList<Music> m, String title){
        return m.indexOf(title);
    }
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
    }
}
