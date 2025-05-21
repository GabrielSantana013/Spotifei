/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.DbConnection;
import DAO.UserDAO;
import cache.MusicCache;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Music;
import model.User;
import static utils.ImageProcessor.byteArrayToImage;
import view.HomeWindow;
import view.customDialogs.CustomJDialog;

/**
 * Controlador responsável por gerenciar a interação entre a
 * {@link HomeWindow} e os dados do {@link User} conectado,
 * incluindo atualização da interface gráfica com informações do usuário,
 * músicas curtidas e descurtidas, e exibição do top 5 de músicas.
 * 
 * @author Gabriel
 */
public class HomeController{
    
    private HomeWindow view;
    private User user;

    /**
     * Construtor que inicializa o controlador com a view e o usuário atual.
     * 
     * @param view a janela principal da aplicação
     * @param user o usuário logado
     * @throws IOException caso ocorra erro ao processar imagens
     */
    public HomeController(HomeWindow view, User user) throws IOException{
        this.view = view;
        this.user = user;        
        
    }
    
    /**
     * Atualiza a interface da view para exibir o nome do usuário
     * e uma mensagem de boas-vindas personalizada.
     */
    public void setUserNameOnWindow(){
        view.getBtt_profile().setText(user.getUserLogin());
        view.getLbl_welcome().setText("Bem-vindo(a), " + user.getName());
    }
    
    /**
     * Busca e atualiza as listas de músicas curtidas e descurtidas do usuário
     * no banco de dados, exibindo as quantidades e os títulos nas listas da view.
     * Em caso de erro de banco, exibe um diálogo informando o problema.
     */
    public void setLikesAndDislikes() {
        try (Connection conn = new DbConnection().getConnection()) {
            UserDAO dao = new UserDAO(conn);
            user.setLikedMusics(dao.getLikedMusics(user.getUserId()));
            user.setDislikedMusics(dao.getDislikedMusics(user.getUserId()));
        } catch (SQLException ex) {
            CustomJDialog.showCustomDialog("Erro!", "Erro ao buscar músicas no banco.");
            return;
        }

        List<Music> liked = user.getLikedMusics();
        List<Music> disliked = user.getDislikedMusics();

        view.getNum_likes().setText("Likes - " + (liked != null ? liked.size() : "nenhuma") + " músicas curtidas");
        view.getNum_dislikes().setText("Dislikes - " + (disliked != null ? disliked.size() : "nenhuma") + " músicas descurtidas");

        if (liked != null && !liked.isEmpty()) {
            String[] likedTitles = liked.stream()
                .map(Music::getMusicTitle)
                .toArray(String[]::new);
            view.getListLikes().setListData(likedTitles);
        } else {
            view.getListLikes().setListData(new String[0]);
        }

        if (disliked != null && !disliked.isEmpty()) {
            String[] dislikedTitles = disliked.stream()
                .map(Music::getMusicTitle)
                .toArray(String[]::new);
            view.getListDislikes().setListData(dislikedTitles);
        } else {
            view.getListDislikes().setListData(new String[0]);
        }
    }
    
    /**
     * Preenche a seção do top 5 músicas da view com as músicas mais curtidas,
     * exibindo título, artista e a imagem da capa convertida do array de bytes.
     * 
     * @throws IOException caso ocorra erro no processamento das imagens das músicas
     */
    public void fillTopFive() throws IOException {
        List<Music> topFive = MusicCache.getAllMusics().stream()
            .sorted((m1, m2) -> Integer.compare(m2.getLikes(), m1.getLikes())) // ordem decrescente
            .limit(5)
            .toList();

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
            titles.get(i).setText(m.getMusicTitle());
            artists.get(i).setText(m.getArtistName());
            BufferedImage image = byteArrayToImage(m.getMusicPhoto());
            images.get(i).setIcon(new ImageIcon(image));
        }
    }

    /**
     * Retorna o usuário atualmente logado e gerenciado pelo controlador.
     * 
     * @return o usuário logado
     */
    public User getUser() {
        return user;
    }
}
