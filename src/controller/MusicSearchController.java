package controller;

import DAO.DbConnection;
import DAO.MusicDAO;
import DAO.PlaylistDAO;
import DAO.UserDAO;
import cache.MusicCache;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import model.Music;
import model.Playlist;
import model.User;
import static utils.ImageProcessor.byteArrayToImage;
import view.SearchWindow;
import view.customDialogs.CustomJDialog;

/**
 * Controlador responsável por gerenciar a lógica da busca, seleção e reprodução de músicas
 * na interface SearchWindow, assim como a interação do usuário com playlists e histórico.
 */
public class MusicSearchController {

    private SearchWindow view;
    private ArrayList<Music> currentMusics;
    private ArrayList<Playlist> currentPlaylists;
    private User user;
    private Music selectedMusic;

    // Controle de reprodução da música
    private volatile boolean isPlaying = false;
    private volatile boolean stopRequested = false;
    private SourceDataLine audioLine;
    private Thread musicThread;

    /**
     * Construtor da classe MusicSearchController.
     *
     * @param view Interface gráfica da busca de músicas (SearchWindow).
     * @param user Usuário atual logado no sistema.
     */
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

    /**
     * Realiza a busca de músicas filtrando por título, artista ou gênero.
     * Atualiza a lista de músicas exibida na interface.
     * Exibe o histórico caso o campo de busca esteja vazio.
     */
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

    /**
     * Exibe na interface as músicas do histórico do usuário.
     * Atualiza a lista de músicas atualmente exibidas.
     */
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

    /**
     * Atualiza os campos da interface com os dados da música selecionada.
     *
     * @param music Música selecionada.
     */
    private void updateMusicLabels(Music music) {
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

        view.getPnl_data().setVisible(true);
        view.getLbl_musicTitle1().setText(music.getMusicTitle());
        view.getLbl_musicArtist1().setText(music.getArtistName());

        try {
            BufferedImage image = byteArrayToImage(music.getMusicPhoto());
            view.getArtist_photo().setIcon(new ImageIcon(image));
            view.getArtist_photo().setVisible(true);
        } catch (IOException e) {
            CustomJDialog.showCustomDialog("Erro", "Erro ao carregar a foto do artista.");
            e.printStackTrace();
        }
    }

    /**
     * Realiza a ação de curtir a música selecionada.
     * Atualiza a base de dados e a interface com os novos valores.
     */
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

            } catch (SQLException ex) {
                CustomJDialog.showCustomDialog("Erro!", "Erro ao curtir a música.");
                ex.printStackTrace();
            }
        } else {
            CustomJDialog.showCustomDialog("Aviso", "Nenhuma música selecionada.");
        }
    }

    /**
     * Realiza a ação de descurtir a música selecionada.
     * Atualiza a base de dados e a interface com os novos valores.
     */
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

            } catch (SQLException ex) {
                CustomJDialog.showCustomDialog("Erro!", "Erro ao descurtir a música.");
                ex.printStackTrace();
            }
        } else {
            CustomJDialog.showCustomDialog("Aviso", "Nenhuma música selecionada.");
        }
    }

    /**
     * Evento chamado quando uma música é selecionada na lista.
     * Atualiza a interface com as informações da música, registra o histórico do usuário e atualiza o banco.
     *
     * @param e Evento de seleção na lista.
     */
        private void onMusicSelected(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            
            view.getPnl_player().setVisible(true);
            view.getPnl_likes().setVisible(true);
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

    /**
     * Inicia a reprodução da música selecionada.
     * Executa a reprodução em uma thread separada para não travar a interface.
     */
    public void playMusic() {        
        if (isPlaying) {
            // Se já estiver tocando, solicite parada
            stopRequested = true;
            if (audioLine != null) {
                audioLine.stop();
                audioLine.close();
            }

            view.getBtt_play().setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/play.png")));
            isPlaying = false;
            return;
        }

        view.getBtt_play().setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/pause.png")));
        isPlaying = true;
        stopRequested = false;

        musicThread = new Thread(() -> {
            try {
                byte[] audioData = null;
                
                audioData = selectedMusic.getMusicAudio();
                

                // Cria um InputStream com os dados
                ByteArrayInputStream audio = null;
                try{
                    audio = new ByteArrayInputStream(audioData);
                } catch(NullPointerException e) {
                    CustomJDialog.showCustomDialog("Aviso!", "Nenhum áudio foi cadastrado para essa música.");
                }
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio);
                
                // Pega o formato e abre a linha para reprodução
                AudioFormat format = audioStream.getFormat();
                long totalFrames = audioStream.getFrameLength();
                float frameRate = format.getFrameRate();
                int totalSeconds = (int) (totalFrames / frameRate);
                
                view.getSlider_duration().setMinimum(0);
                view.getSlider_duration().setMaximum(totalSeconds);
                view.getSlider_duration().setValue(0);                

                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                audioLine = (SourceDataLine) AudioSystem.getLine(info);
                audioLine.open(format);
                audioLine.start();

                // Buffer para reprodução
                byte[] buffer = new byte[4096];
                int bytesRead;
                int totalBytesRead = 0;
                int bytesPerSecond = (int) (format.getFrameSize() * format.getFrameRate());

                // Toca o áudio enquanto não for solicitado parar
                while (!stopRequested && (bytesRead = audioStream.read(buffer)) != -1) {
                    audioLine.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;

                    int currentSecond = totalBytesRead / bytesPerSecond;

                    // Atualiza o slider na EDT
                    final int sliderValue = currentSecond;
                    SwingUtilities.invokeLater(() -> view.getSlider_duration().setValue(sliderValue));
                }

                // Finaliza
                audioLine.drain();
                audioLine.stop();
                audioLine.close();
                audioStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isPlaying = false;
                stopRequested = false;
                SwingUtilities.invokeLater(() -> {
                    view.getBtt_play().setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/play.png")));
                });
            }
        });

        musicThread.start();
    }

    /**
     * Para a reprodução da música atual.
     */
    public void stopMusic() {
        stopRequested = true;
        if (audioLine != null && audioLine.isOpen()) {
            audioLine.stop();
            audioLine.close();
        }
        if (musicThread != null && musicThread.isAlive()) {
            try {
                musicThread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        SwingUtilities.invokeLater(() -> view.getPnl_player().setVisible(false));
    }

    /**
     * Adiciona a música selecionada a uma playlist selecionada.
     * Atualiza a base de dados e informa o usuário.
     */
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

    /**
     * Carrega as playlists do usuário e atualiza o combo box da interface.
     */
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
    
        /**
         * Atualiza o texto do botão de perfil na janela para exibir o login do administrador atual.
         */
        public void setUserNameOnWindow(){
            view.getBtt_profile().setText(user.getUserLogin());
        }
}
