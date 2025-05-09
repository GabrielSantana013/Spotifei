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

        //Criando um evento
        this.view.getjList1().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = view.getjList1().getSelectedIndex();
                    
                    //verifica se o intervalo é válido
                    if (selectedIndex >= 0 && selectedIndex < currentMusics.size()) {
                        Music selectedMusic = currentMusics.get(selectedIndex);
                        
                        //Exibe a música formatada
                        int musicDuration = selectedMusic.getDuration();
                        int minutes = musicDuration / 60;
                        int seconds = musicDuration % 60;
                        String formattedDuration = String.format("%d:%02d", minutes, seconds);
                        
                        // Atualiza os labels com os dados da música
                        view.getLbl_musicTitle().setText(selectedMusic.getMusicTitle());
                        view.getLbl_musicGenre().setText(selectedMusic.getGenre());
                        view.getLbl_musicDuration().setText(formattedDuration);
                        view.getLbl_musicLikes().setText(String.valueOf(selectedMusic.getLikes()));
                        view.getLbl_musicDislikes().setText(String.valueOf(selectedMusic.getDeslikes()));
                        view.getLbl_musicDescription().setText(selectedMusic.getMusicDescription());
                        view.getLbl_musicArtist().setText(selectedMusic.getArtistName());
                        
                        // Atualiza o histórico
                        user.addToHistoric(selectedMusic.getMusicTitle());

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
                
//                if (user.getHistoric() != null && !user.getHistoric().isEmpty()) {                    
//                    //converte o ArrayList pra String[] por causa do jList
//                    String[] historyArray = user.getHistoric().toArray(new String[0]);
//                    view.getjList1().setListData(historyArray);
//                    System.out.println("Atualizando JList com histórico:");
//                    for (String s : historyArray) {
//                        System.out.println(s);
//                    }
//                } else {
//                    System.out.println("piru");
//                    view.getjList1().setListData(new String[0]); // exibe lista vazia
//                }
                
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
        if (user.getHistoric() != null && !user.getHistoric().isEmpty()) {
            // Converte o ArrayList para String[] por causa do jList
            String[] historyArray = user.getHistoric().toArray(new String[0]);
            view.getjList1().setListData(historyArray);

            System.out.println("Atualizando JList com histórico:");
            for (String s : historyArray) {
                System.out.println(s);
            }
        } else {
            view.getjList1().setListData(new String[0]); // Exibe lista vazia
        }
    }
        
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
    }
}
