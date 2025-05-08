package controller;

import DAO.DbConnection;
import DAO.MusicDAO;
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

        //Criando um evento
        this.view.getjList1().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = view.getjList1().getSelectedIndex();
                    
                    //verifica se o intervalo é válido
                    if (selectedIndex >= 0 && selectedIndex < currentMusics.size()) {
                        Music selectedMusic = currentMusics.get(selectedIndex);

                        // Atualiza os labels com os dados da música
                        view.getLbl_musicTitle().setText(selectedMusic.getMusicTitle());
                        view.getLbl_musicGenre().setText(selectedMusic.getGenre());
                        view.getLbl_musicDuration().setText(String.valueOf(selectedMusic.getDuration()));
                        view.getLbl_musicLikes().setText(String.valueOf(selectedMusic.getLikes()));
                        view.getLbl_musicDislikes().setText(String.valueOf(selectedMusic.getDeslikes()));
                        view.getLbl_musicDescription().setText(selectedMusic.getMusicDescription());
                        view.getLbl_musicArtist().setText(selectedMusic.getArtistName());
                    }
                }
            }
        });
    }

    public void searchMusic() {
        String musicName = view.getSearch_name().getText();

        if (musicName == null || musicName.trim().isEmpty()) {
            CustomJDialog.showCustomDialog("Aviso!", "Digite o nome de uma música para buscar.");
            return;
        }

        try (Connection conn = new DbConnection().getConnection()) {
            MusicDAO musicDAO = new MusicDAO(conn);
            List<Music> musics = musicDAO.searchMusic(musicName);

            if (musics.isEmpty()) {
                CustomJDialog.showCustomDialog("Resultado", "Nenhuma música encontrada.");
                view.getjList1().setListData(new String[0]);
                currentMusics.clear(); // limpa também a lista atual
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
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
    }
}
