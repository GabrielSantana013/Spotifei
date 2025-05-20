/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AdmDAO;
import java.sql.SQLException;
import java.sql.Connection;
import DAO.DbConnection;
import cache.MusicCache;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Adm;
import model.Artist;
import model.Music;
import static utils.ImageProcessor.processImage;
import view.AdmSettingsWindow;
import view.RegistrationWindow;
import view.customDialogs.CustomJDialog;

/**
 *
 * @author Gabriel
 */
public class AdmSettingsController {
    
    private AdmSettingsWindow view;
    private Adm adm;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private RegistrationWindow rw;
    private byte[] musicPhoto; // atributos instaciados aqui para conseguir passar os arquivos de uma função para outra
    private byte[] musicAudio;
    private int duration;

    public AdmSettingsController(AdmSettingsWindow view, Adm adm) {
        this.view = view;
        this.adm = adm;
        this.rw = new RegistrationWindow(true);
    }
     
    public void registerUser(){
        view.getBtt_atualizar().setVisible(false);
        view.getPnl_registerMusic().setVisible(false);
        view.getPnl_registerArtist().setVisible(false);
        view.getPnl_removeMusic().setVisible(false);
        
        this.rw.setVisible(true);
        this.rw.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.rw.getBtt_return().setVisible(false);
    }
    
    public void registerArtist(){
        this.rw.dispose();
        view.getPnl_registerMusic().setVisible(false);
        view.getPnl_registerArtist().setVisible(true);
        view.getBtt_atualizar().setVisible(true);
        
        for (ActionListener al : view.getBtt_atualizar().getActionListeners()) {
            view.getBtt_atualizar().removeActionListener(al);
        }
        
        view.getBtt_atualizar().addActionListener(e -> {         
            String name = view.getTxt_name().getText();
            String gender = view.getCbox_gender().getSelectedItem().toString();
            String description = view.getTxt_description().getText();
            String bDateString = view.getTxt_birthDate().getText();
            Date birthDate = null;        
            try{
                //não aceita datas inválidas
                sdf.setLenient(false);
                 birthDate = sdf.parse(bDateString);            
            }catch(ParseException ex){
                SwingUtilities.invokeLater(() -> {
                        CustomJDialog.showCustomDialog("Aviso!", "Data inválida");
                    });
            }
            
            try(Connection conn = new DbConnection().getConnection()){
                AdmDAO dao = new AdmDAO(conn);
                
                if(name == null || name.isBlank()){
                     CustomJDialog.showCustomDialog("Erro!", "Por favor preencha "
                             + "os campos obrigatórios.");
                     System.out.println(name);
                     System.out.println(gender);
                     System.out.println(description);
                     System.out.println(birthDate);
                }               
                Artist artist = new Artist(description, name, gender, birthDate);
                dao.insertArtist(artist);
                
            }catch(SQLException ex){
                CustomJDialog.showCustomDialog("Erro!", "Erro ao "
                             + "conectar com o banco de dados.");
            } 
            CustomJDialog.showCustomDialog("Aviso!", "Artista cadastrado "
                             + "com sucesso!");
    
            view.getPnl_registerArtist().setVisible(false);
            view.getBtt_atualizar().setVisible(false);
        });


    }
    
    public void registerMusic(){
        this.rw.dispose();
        view.getPnl_registerArtist().setVisible(false);
        view.getPnl_registerMusic().setVisible(true);
        view.getBtt_atualizar().setVisible(true);
        
        List<Artist> artists = new ArrayList<>();
        
        try(Connection conn = new DbConnection().getConnection()){
            
            AdmDAO dao = new AdmDAO(conn);
            artists.addAll(dao.getAllArtists());
            
            DefaultListModel<String> model = new DefaultListModel();    
            for (Artist artist : artists) {
                model.addElement(artist.getName());
            }
            view.getList_artists().setModel(model);                
        }catch(SQLException e){
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar os artistas no banco.");
            e.printStackTrace();
        }
        
        for (ActionListener al : view.getBtt_atualizar().getActionListeners()) {
            view.getBtt_atualizar().removeActionListener(al);
        }
        
        view.getBtt_atualizar().addActionListener(e -> {                  
            String musicName = view.getTxt_title().getText();
            String genre = view.getTxt_genre().getText();
            String description = view.getTxt_musicDescription().getText();
            String stringLikes = view.getTxt_likes().getText();
            String stringDislikes = view.getTxt_dislikes().getText();
            int artistId = artists.get(view.getList_artists().getSelectedIndex()).getArtistId();
            
            //regex dos ints
            if(!stringLikes.matches("\\d+") || !stringDislikes.matches("\\d+")){
                CustomJDialog.showCustomDialog("Erro!", "Likes/Dislikes com valores inválidos.");
                return;
            }
            
            int likes = Integer.parseInt(stringLikes);
            int dislikes = Integer.parseInt(stringDislikes);
            
            Music music = new Music(likes, dislikes, duration, artistId, musicName, description, genre, this.musicPhoto, this.musicAudio);
           
            try(Connection conn = new DbConnection().getConnection()){
                AdmDAO dao = new AdmDAO(conn);            
                dao.insertMusic(musicName, description, duration, genre, artistId, this.musicPhoto, this.musicAudio);
                MusicCache.insertMusic(music);
            }catch(SQLException ex){
                CustomJDialog.showCustomDialog("Erro!", "Erro ao cadastrar musica no banco.");
            }
            
            CustomJDialog.showCustomDialog("Aviso!", "Musica cadastrada com sucesso.");
            view.getPnl_registerMusic().setVisible(false);
            view.getBtt_atualizar().setVisible(false);
        });
    }
    
    
    public void excludeMusic(){
        this.rw.dispose();
        view.getPnl_registerArtist().setVisible(false);
        view.getPnl_registerMusic().setVisible(false);
        view.getPnl_removeMusic().setVisible(true);
        view.getBtt_atualizar().setVisible(true);
        view.getBtt_atualizar().setText("Excluir");

        List<Music> musics = new ArrayList<>();

        try (Connection conn = new DbConnection().getConnection()) {
            AdmDAO dao = new AdmDAO(conn);
            musics = MusicCache.getAllMusics();
            DefaultListModel<String> model = new DefaultListModel<>();
            for (Music music : musics) {
                model.addElement(music.getMusicTitle());
            }
            view.getList_musics().setModel(model);
        } catch (SQLException e) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar músicas no banco.");
            e.printStackTrace();
        }

        for (ActionListener al : view.getBtt_atualizar().getActionListeners()) {
            view.getBtt_atualizar().removeActionListener(al);
        }

        List<Music> finalMusics = musics;

        view.getBtt_atualizar().addActionListener(e -> {
            int selectedIndex = view.getList_musics().getSelectedIndex();
            if (selectedIndex < 0 || selectedIndex >= finalMusics.size()) {
                CustomJDialog.showCustomDialog("Aviso!", "Selecione uma música para excluir.");
                return;
            }

            Music selectedMusic = finalMusics.get(selectedIndex);

            try (Connection conn = new DbConnection().getConnection()) {
                AdmDAO dao = new AdmDAO(conn);
                dao.deleteMusicByTitle(selectedMusic.getMusicTitle());
                MusicCache.removeByTitle(selectedMusic.getMusicTitle());                
                
            } catch (SQLException ex) {
                CustomJDialog.showCustomDialog("Erro!", "Erro ao excluir música do banco.");
                ex.printStackTrace();
                return;
            }

            CustomJDialog.showCustomDialog("Sucesso!", "Música excluída com sucesso!");
                    view.getPnl_removeMusic().setVisible(false);
        view.getBtt_atualizar().setVisible(false);
            
        });
    }

    
    
    public void addPhoto(){      
         JFileChooser fileChooser = new JFileChooser();
        
        // Caminho para a pasta Documentos do usuário
        String caminhoDocumentos = System.getProperty("user.home") + File.separator + "Documents";
        File pastaInicial = new File(caminhoDocumentos);

        // Verifica se a pasta existe e é um diretório
        if (pastaInicial.exists() && pastaInicial.isDirectory()) {
            fileChooser = new JFileChooser(pastaInicial);
        } else {
            fileChooser = new JFileChooser(); // abre no padrão
        }
        
        // filtra apenas imagens
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filtro);

        int retorno = fileChooser.showOpenDialog(view);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            // TODO: colocar o que ta pra baixo no controller
            try {
                File inputFile = fileChooser.getSelectedFile();

                // Checa se o arquivo existe e pode ser lido
                if (!inputFile.exists()) {
                    System.err.println("Erro: Arquivo não existe: "
                            + inputFile.getAbsolutePath());
                    return;
                }

                if (!inputFile.canRead()) {
                    System.err.println("Erro: Não foi possível ler o arquivo (cheque permissões): "
                            + inputFile.getAbsolutePath());
                    return;
                }

                BufferedImage originalImage = null;
                originalImage = ImageIO.read(inputFile);

                // Checa se a imagem foi lida
                if (originalImage == null) {
                    System.err.println("Erro: Não foi possível ler a imagem."
                            + "Certifique a extensão do arquivo (JPEG, PNG)");
                    return;
                }

                // Converte para array de bytes
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "PNG", byteArrayOutputStream); // PNG para transparência
                byte[] imageData = byteArrayOutputStream.toByteArray();

                // Processa a imagem
                this.musicPhoto = processImage(imageData);
                
                
                System.out.println("Imagem processada - Tamanho final: " + this.musicPhoto.length + " bytes");                
                } catch (IOException e) {
                    System.err.println("Error processing image: " + e.getMessage());
                    e.printStackTrace();
                }
        }
    }
    
    public void addAudio(){
        
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        
        // Caminho para a pasta Documentos do usuário
        String caminhoDocumentos = System.getProperty("user.home") + File.separator + "Documents";
        File pastaInicial = new File(caminhoDocumentos);

        // Verifica se a pasta existe e é um diretório
        if (pastaInicial.exists() && pastaInicial.isDirectory()) {
            fileChooser = new JFileChooser(pastaInicial);
        } else {
            fileChooser = new JFileChooser(); // abre no padrão
        }

        // filtra apenas áudios
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Áudios", "wav");
        fileChooser.setFileFilter(filtro);
        int retorno = fileChooser.showOpenDialog(view);

        if (retorno == JFileChooser.APPROVE_OPTION) {

            File inputFile = fileChooser.getSelectedFile();

            // Checa se o arquivo existe e pode ser lido
            if (!inputFile.exists()) {
                System.err.println("Erro: Arquivo não existe: "
                        + inputFile.getAbsolutePath());
                return;
            }

            if (!inputFile.canRead()) {
                System.err.println("Erro: Não foi possível ler o arquivo (cheque permissões): "
                        + inputFile.getAbsolutePath());
                return;
            }

            try (FileInputStream fis = new FileInputStream(inputFile);
                ByteArrayOutputStream audioData = new ByteArrayOutputStream()) {

                // buffer para ler o arquivo em chunks
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = fis.read(buffer)) != -1) {
                    audioData.write(buffer, 0, bytesRead);
                }

                // Armazena o áudio como byte[]
                this.musicAudio = audioData.toByteArray();
                
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);
                    AudioFormat format = audioInputStream.getFormat();
                    long frames = audioInputStream.getFrameLength();
                    double durationInSeconds = (frames + 0.0) / format.getFrameRate();
                    this.duration = (int) Math.round(durationInSeconds);
                    view.getTxt_duration().setText(String.valueOf(this.duration) + " s");
                    audioInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                System.out.println("Áudio carregado - Tamanho: " + musicAudio.length + " bytes");

                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo de áudio: " + e.getMessage());
                    e.printStackTrace();
                }
        }
    }
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(adm.getAdmLogin());
    }
}
