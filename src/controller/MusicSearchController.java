package controller;

import DAO.DbConnection;
import DAO.MusicDAO;
import DAO.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Music;
import model.User;
import view.SearchWindow;
import view.customDialogs.CustomJDialog;

public class MusicSearchController {

    private SearchWindow view;
    private ArrayList<Music> currentMusics; // Armazena as músicas exibidas no JList
    private User user;

    public MusicSearchController(SearchWindow view, User user) {
        this.view = view;
        this.user = user;
        this.currentMusics = new ArrayList<>();
        
        displayHistoric();

        //Criando um evento da barra de pesquisa
        this.view.getjList1().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = view.getjList1().getSelectedIndex();
                    
                    //verifica se o intervalo é válido
                    if (selectedIndex >= 0 && selectedIndex < currentMusics.size()) {
                        Music selectedMusic = currentMusics.get(selectedIndex);
                        
                        updateMusicLabels(selectedMusic);
                        
                        // Atualiza o histórico
                        user.addToHistoric(selectedMusic.getMusicTitle());
                        displayHistoric();

                        // Atualiza no banco
                        try (Connection conn = new DbConnection().getConnection()) {
                            UserDAO dao = new UserDAO(conn);
                            dao.updateHistoric(user);
                        } catch (SQLException ex) {
                            CustomJDialog.showCustomDialog("Erro!", "Erro ao atualizar o historico no banco de dados.");
                        }
                        
                        //debugg
                        for(String m : user.getHistoric()){
                            System.out.println(m);
                        }
                    }
                    
                }
            }
        });
        
        //evento do botão LIKE
        this.view.getBtt_like().addActionListener(e -> {
            System.out.println("Clicou like");
            int selectedIndex = view.getjList1().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < currentMusics.size()) {
                Music selectedMusic = currentMusics.get(selectedIndex);
                try (Connection conn = new DbConnection().getConnection()) {
                    UserDAO userDAO = new UserDAO(conn);
                    MusicDAO musicDAO = new MusicDAO(conn);

                    userDAO.insertOrUpdateLike(user.getUserId(), selectedMusic.getMusicId(), true); // true = like
                    musicDAO.updateLikeCount(selectedMusic.getMusicId(), 1);

                    // Atualiza o objeto local e os labels
                    Music updatedMusic = musicDAO.getMusicById(selectedMusic.getMusicId());
                    currentMusics.set(selectedIndex, updatedMusic);
                    updateMusicLabels(updatedMusic);

                } catch (SQLException ex) {
                    CustomJDialog.showCustomDialog("Erro!", "Erro ao curtir a música.");
                }
            }
        });
        
        //evento do botão Deslike
        this.view.getBtt_dislike().addActionListener(e -> {
            System.out.println("Clicou deslike");
            int selectedIndex = view.getjList1().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < currentMusics.size()) {
                Music selectedMusic = currentMusics.get(selectedIndex);
                try (Connection conn = new DbConnection().getConnection()) {
                    UserDAO userDAO = new UserDAO(conn);
                    MusicDAO musicDAO = new MusicDAO(conn);

                    userDAO.insertOrUpdateLike(user.getUserId(), selectedMusic.getMusicId(), false); // false = dislike
                    musicDAO.updateDislikeCount(selectedMusic.getMusicId(), 1);

                    // Atualiza o objeto local e os labels
                    Music updatedMusic = musicDAO.getMusicById(selectedMusic.getMusicId());
                    currentMusics.set(selectedIndex, updatedMusic);
                    updateMusicLabels(updatedMusic);

                } catch (SQLException ex) {
                    CustomJDialog.showCustomDialog("Erro!", "Erro ao descurtir a música.");
                }
            }
        });
    }

    public void searchMusic() {
        String musicName = view.getSearch_name().getText();

        if (musicName == null || musicName.trim().isEmpty()) {            
            displayHistoric();
            return;
        }

        try (Connection conn = new DbConnection().getConnection()) {
            MusicDAO musicDAO = new MusicDAO(conn);
            List<Music> musics = musicDAO.searchMusic(musicName);

            if (musics.isEmpty()) {
                CustomJDialog.showCustomDialog("Resultado", "Nenhuma música encontrada.");
                view.getjList1().setListData(new String[0]);
                currentMusics.clear(); // limpa também a lista atual
                
                displayHistoric();                
                
            } else {
                currentMusics = new ArrayList<>(musics); // salva as músicas encontradas

                String[] titles = currentMusics.stream()
                        .map(Music::getMusicTitle)
                        .toArray(String[]::new);
                view.getjList1().setListData(titles);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar músicas no banco de dados.");
        }
    }
    
    
    private void displayHistoric() {
        List<String> historicTitles = user.getHistoric();

        if (historicTitles != null && !historicTitles.isEmpty()) {
            List<Music> musicsFromHistoric = new ArrayList<>();

            try (Connection conn = new DbConnection().getConnection()) {
                MusicDAO musicDAO = new MusicDAO(conn);

                for (String title : historicTitles) {
                    Music m = musicDAO.getMusicByTitle(title);
                    if (m != null) {
                        musicsFromHistoric.add(m);
                    }
                }
                currentMusics = new ArrayList<>(musicsFromHistoric); // atualiza com músicas do histórico
                String[] titles = musicsFromHistoric.stream()
                        .map(Music::getMusicTitle)
                        .toArray(String[]::new);
                view.getjList1().setListData(titles);

            } catch (SQLException e) {
                e.printStackTrace();
                CustomJDialog.showCustomDialog("Erro!", "Erro ao carregar histórico do banco de dados.");
            }
        } else {
            view.getjList1().setListData(new String[0]); // Exibe lista vazia
        }
    }
    
    private void updateMusicLabels(Music music) {
        int duration = music.getDuration();
        String formattedDuration = String.format("%d:%02d", duration / 60, duration % 60);

        view.getLbl_musicTitle().setText(music.getMusicTitle());
        view.getLbl_musicGenre().setText(music.getGenre());
        view.getLbl_musicDuration().setText(formattedDuration);
        view.getLbl_musicLikes().setText(String.valueOf(music.getLikes()));
        view.getLbl_musicDislikes().setText(String.valueOf(music.getDeslikes()));
        view.getLbl_musicDescription().setText(music.getMusicDescription());
        view.getLbl_musicArtist().setText(music.getArtistName());
    }


        
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
    }
}
