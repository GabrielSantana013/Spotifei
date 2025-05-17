/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AdmDAO;
import java.sql.SQLException;
import java.sql.Connection;
import DAO.DbConnection;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Adm;
import model.Artist;
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

    public AdmSettingsController(AdmSettingsWindow view, Adm adm) {
        this.view = view;
        this.adm = adm;     
    }
     
    public void registerUser(){   
        view.getPnl_registerMusic().setVisible(false);
        view.getPnl_registerUser().setVisible(false);
        RegistrationWindow rw = new RegistrationWindow(true);
        rw.setVisible(true);
        rw.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        rw.getBtt_return().setVisible(false);
    }
    
    public void registerArtist(){
        view.getPnl_registerMusic().setVisible(false);
        view.getPnl_registerUser().setVisible(true);
        
        for (ActionListener al : view.getBtt_cadastrar().getActionListeners()) {
            view.getBtt_cadastrar().removeActionListener(al);
        }
        
        view.getBtt_cadastrar().addActionListener(e -> {         
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
            
        });
    }
    
    public void registerMusic(){
        view.getPnl_registerUser().setVisible(false);
        view.getPnl_registerMusic().setVisible(true);
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
        
        for (ActionListener al : view.getBtt_cadastrar().getActionListeners()) {
            view.getBtt_cadastrar().removeActionListener(al);
        }
        
        view.getBtt_cadastrar().addActionListener(e -> {      
            System.out.println("ADM está cadastrando uma nova musga.");
            String musicName = view.getTxt_title().getText();
            String genre = view.getTxt_genre().getText();
            String description = view.getTxt_musicDescription().getText();
            String stringLikes = view.getTxt_likes().getText();
            String stringDislikes = view.getTxt_dislikes().getText();
            int artistId = artists.get(view.getList_artists().getSelectedIndex()).getArtistId();
            //int duration
            //byte musicPhoto
            //byte musicAudio
            
            //regex dos ints
            if(!stringLikes.matches("\\d+") || !stringDislikes.matches("\\d+")){
                CustomJDialog.showCustomDialog("Erro!", "Likes/Dislikes com valores inválidos.");
                return;
            }
            
            int likes = Integer.parseInt(stringLikes);
            int dislikes = Integer.parseInt(stringDislikes);
            
            //Descomenta essa parte pra upar a música no banco
            /*Music music = new Music(likes, dislikes, duration, artistId, musicName, description, genre, musicPhoto, musicAudio);
           
            try(Connection conn = new DbConnection().getConnection()){
                AdmDAO dao = new AdmDAO(conn);            
                dao.insertMusic(musicName, description, duration, genre, artistId, musicPhoto, musicAudio);
            }catch(SQLException ex){
                CustomJDialog.showCustomDialog("Erro!", "Erro ao cadastrar musica no banco.");
            }
            */
            
            //printa o artista selecionado pra debuggar
            System.out.println(artists.get(view.getList_artists().getSelectedIndex()).getName());
            
            CustomJDialog.showCustomDialog("Aviso!", "Musica cadastrada com sucesso.");
            view.getPnl_registerMusic().setVisible(false);
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
                File arquivoSelecionado = fileChooser.getSelectedFile();
                System.out.println("Arquivo selecionado: " + arquivoSelecionado.getAbsolutePath());

                // Example: Read an image file
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
                            + "Certifique a extensão do arquivo (JPEG, PNG, BMP, GIF)");
                    return;
                }

                // Converte para array de bytes
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "PNG", byteArrayOutputStream); // PNG para transparência
                byte[] imageData = byteArrayOutputStream.toByteArray();

                // Processa a imagem
                byte[] processedImage = null;
                processedImage = processImage(imageData);
                System.out.println("Imagem processada - Tamnho final: " + processedImage.length + " bytes");

                // Salva a imagem nova num arquivo para verificar
                File outputFile = new File(arquivoSelecionado.getAbsolutePath());
                ByteArrayInputStream inputStream = new ByteArrayInputStream(processedImage);
                BufferedImage resultImage = null;

                resultImage = ImageIO.read(inputStream);

                ImageIO.write(resultImage, "PNG", outputFile);

                System.out.println("Imagem processada e salva em: " + outputFile.getAbsolutePath());
                } catch (IOException e) {
                    System.err.println("Error processing image: " + e.getMessage());
                    e.printStackTrace();
                }
            // TODO: essa função foi criada por IA, tem que arrumar no utils.imageProcessor e na hora que chamar tbm
            // Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/yourdb", "username", "password");
            // storeImageInDatabase(connection, 1, processedImage);
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
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Áudios", "mp3", "wav", "ogg", "AAC");
        fileChooser.setFileFilter(filtro);
        int retorno = fileChooser.showOpenDialog(view);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            System.out.println("Arquivo selecionado: " + arquivoSelecionado.getAbsolutePath());
        }
    }
    
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(adm.getAdmLogin());
    }
}
