package controller;

import DAO.DbConnection;
import DAO.MusicDAO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Adm;
import model.Music;
import view.AdmHomeWindow;
import view.customDialogs.CustomJDialog;
import static utils.ImageProcessor.byteArrayToImage;

/**
 * Controlador para a janela principal do administrador (AdmHomeWindow).
 * Responsável por interagir com a camada DAO para obter dados do banco,
 * atualizar a interface com informações relevantes, como músicas mais/menos curtidas,
 * total de usuários, total de músicas, e o nome do administrador logado.
 * 
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class AdmHomeController {
    
    private AdmHomeWindow view;
    private Adm adm;

    /**
     * Construtor do controlador, inicializa a view e o administrador atual.
     * 
     * @param view a janela principal do administrador
     * @param adm o administrador logado
     * @throws IOException caso ocorra erro na manipulação de imagens ou arquivos
     */
    public AdmHomeController(AdmHomeWindow view, Adm adm) throws IOException {
        this.view = view;
        this.adm = adm;        
    }
    
    /**
     * Define o nome do usuário administrador na interface gráfica,
     * atualizando o botão de perfil e o rótulo de boas-vindas.
     */
    public void setUserNameOnWindow() {
        view.getBtt_profile().setText(adm.getAdmLogin());
        view.getLbl_welcome().setText("Bem-vindo(a), administrador ");
    }
 
    /**
     * Preenche a interface com as 5 músicas mais curtidas do banco de dados.
     * Atualiza os títulos, nomes dos artistas e imagens nas labels correspondentes.
     * 
     * @throws IOException caso haja erro na conversão de bytes para imagem
     */
    public void fillTopFive() throws IOException {
        ArrayList<Music> topFive = new ArrayList<>();
        
        try (Connection conn = new DbConnection().getConnection()) {
            MusicDAO dao = new MusicDAO(conn);
            topFive = dao.getTop5MostLikedMusics();
        } catch (SQLException e) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar o top 5 no banco.");
        }
        
        List<JLabel> titles = Arrays.asList(
            view.getTitle1(),
            view.getTitle2(),
            view.getTitle3(),
            view.getTitle4(),
            view.getTitle5()
        );
        
        List<JLabel> artists = Arrays.asList(
            view.getArtist1(),
            view.getArtist2(),
            view.getArtist3(),
            view.getArtist4(),
            view.getArtist5()
        );
        
        List<JLabel> images = Arrays.asList(
            view.getjLabel1(),
            view.getjLabel2(),
            view.getjLabel3(),
            view.getjLabel4(),
            view.getjLabel5()
        );

        for (int i = 0; i < topFive.size(); i++) {
            Music m = topFive.get(i);
            
            String title = m.getMusicTitle();
            titles.get(i).setText(title);
            String artist = m.getArtistName();
            artists.get(i).setText(artist);
            BufferedImage image = byteArrayToImage(m.getMusicPhoto());
            images.get(i).setIcon(new ImageIcon(image));
        }
    }

    /**
     * Preenche a interface com as 5 músicas menos curtidas do banco de dados.
     * Atualiza os títulos, nomes dos artistas e imagens nas labels correspondentes.
     * 
     * @throws IOException caso haja erro na conversão de bytes para imagem
     */
    public void fillTopFiveBad() throws IOException {
        ArrayList<Music> topFive = new ArrayList<>();
        
        try (Connection conn = new DbConnection().getConnection()) {
            MusicDAO dao = new MusicDAO(conn);
            topFive = dao.getTop5LessLikedMusics();
        } catch (SQLException e) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar o top 5 no banco.");
        }
        
        List<JLabel> titles = Arrays.asList(
            view.getTitle6(),
            view.getTitle7(),
            view.getTitle8(),
            view.getTitle9(),
            view.getTitle10()
        );
        
        List<JLabel> artists = Arrays.asList(
            view.getArtist6(),
            view.getArtist7(),
            view.getArtist8(),
            view.getArtist9(),
            view.getArtist10()
        );
        
        List<JLabel> images = Arrays.asList(
            view.getjLabel7(),
            view.getjLabel8(),
            view.getjLabel9(),
            view.getjLabel10(),
            view.getjLabel11()
        );

        for (int i = 0; i < topFive.size(); i++) {
            Music m = topFive.get(i);
            
            String title = m.getMusicTitle();
            titles.get(i).setText(title);
            String artist = m.getArtistName();
            artists.get(i).setText(artist);
            BufferedImage image = byteArrayToImage(m.getMusicPhoto());
            images.get(i).setIcon(new ImageIcon(image));
        }
    }
    
    /**
     * Obtém e exibe o total de usuários cadastrados no sistema.
     * Atualiza o label correspondente na interface gráfica.
     */
    public void totalUsers() {
        int users = 0;
        
        try (Connection conn = new DbConnection().getConnection()) {
            MusicDAO dao = new MusicDAO(conn);
            users = dao.getTotalUsers();
        } catch (SQLException e) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar usuários.");
        }
        
        view.getLbl_numberUsers().setText(String.valueOf(users));
    }
    
    /**
     * Obtém e exibe o total de músicas cadastradas no sistema.
     * Atualiza o label correspondente na interface gráfica.
     */
    public void totalMusics() {
        int musics = 0;
        
        try (Connection conn = new DbConnection().getConnection()) {
            MusicDAO dao = new MusicDAO(conn);
            musics = dao.getTotalMusics();
        } catch (SQLException e) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar músicas.");
        }
        
        view.getLbl_numberMusics().setText(String.valueOf(musics));
    }
    
    /**
     * Retorna o objeto administrador associado a este controlador.
     * 
     * @return o administrador atual
     */
    public Adm getAdm() {
        return adm;
    }
}
