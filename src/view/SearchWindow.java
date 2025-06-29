/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.MusicSearchController;
import view.customClasses.RoundedButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import model.User;
import view.customClasses.*;
import view.customClasses.RoundedButton.TextAlign;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class SearchWindow extends javax.swing.JFrame {
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Dimension screenSize = toolkit.getScreenSize();
    private final int width = screenSize.width;
    private final int height = screenSize.height;
    private User user;
    
    /**
     * Creates new form HomeWindow
     */
    public SearchWindow(User user) {
        initComponents();
        c = new MusicSearchController(this, user);
        c.loadUserPlaylists();
        this.user = user;

        this.setSize(width, height);
        
        // changes window icon
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/logoSpotifei.png")).getImage());
        
        // center the window
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        
        this.setLocation(new Point(x,y));
        
        SwingUtilities.invokeLater(() -> {
            list_musics.requestFocusInWindow();
        });
        c.setUserNameOnWindow();        
    }

    public JTextField getSearch_name() {
        return search_name;
    }

    public void setSearch_name(JTextField search_name) {
        this.search_name = search_name;
    }

    public JList<String> getjList1() {
        return list_musics;
    }

    public void setjList1(JList<String> jList1) {
        this.list_musics = jList1;
    }

    public JLabel getLbl_musicArtist() {
        return lbl_musicArtist;
    }

    public void setLbl_musicArtist(JLabel lbl_musicArtist) {
        this.lbl_musicArtist = lbl_musicArtist;
    }

    public JTextArea getLbl_musicDescription() {
        return lbl_musicDescription;
    }

    public void setLbl_musicDescription(JTextArea lbl_musicDescription) {
        this.lbl_musicDescription = lbl_musicDescription;
    }

    public JLabel getLbl_musicDislikes() {
        return lbl_musicDislikes;
    }

    public void setLbl_musicDislikes(JLabel lbl_musicDislikes) {
        this.lbl_musicDislikes = lbl_musicDislikes;
    }

    public JLabel getLbl_musicDuration() {
        return lbl_musicDuration;
    }

    public void setLbl_musicDuration(JLabel lbl_musicDuration) {
        this.lbl_musicDuration = lbl_musicDuration;
    }

    public JLabel getLbl_musicGenre() {
        return lbl_musicGenre;
    }

    public void setLbl_musicGenre(JLabel lbl_musicGenre) {
        this.lbl_musicGenre = lbl_musicGenre;
    }

    public JLabel getLbl_musicLikes() {
        return lbl_musicLikes;
    }

    public void setLbl_musicLikes(JLabel lbl_musicLikes) {
        this.lbl_musicLikes = lbl_musicLikes;
    }

    public JLabel getLbl_musicTitle() {
        return lbl_musicTitle;
    }

    public void setLbl_musicTitle(JLabel lbl_musicTitle) {
        this.lbl_musicTitle = lbl_musicTitle;
    }

    public JButton getBtt_profile() {
        return btt_profile;
    }

    public void setBtt_profile(JButton btt_profile) {
        this.btt_profile = btt_profile;
    }

    public JButton getBtt_dislike() {
        return btt_dislike;
    }

    public void setBtt_dislike(JButton btt_dislike) {
        this.btt_dislike = btt_dislike;
    }

    public JButton getBtt_like() {
        return btt_like;
    }

    public void setBtt_like(JButton btt_like) {
        this.btt_like = btt_like;
    }

    public void setSearch_pnl_musicInfoVisibility(Boolean flag) {
        this.search_pnl_musicInfo.setVisible(flag);
    }

    public JList<String> getList_playlists() {
        return list_playlists;
    }

    public void setList_playlists(JList<String> list_playlists) {
        this.list_playlists = list_playlists;
    }

    public JButton getBtt_addPlaylist() {
        return btt_addPlaylist;
    }

    public void setBtt_addPlaylist(JButton btt_addPlaylist) {
        this.btt_addPlaylist = btt_addPlaylist;
    }

    public JLabel getArtist_photo() {
        return artist_photo;
    }

    public void setArtist_photo(JLabel artist_photo) {
        this.artist_photo = artist_photo;
    }

    public JButton getBtt_play() {
        return btt_play;
    }

    public void setBtt_play(JButton btt_play) {
        this.btt_play = btt_play;
    }

    public JSlider getSlider_duration() {
        return slider_duration;
    }

    public void setSlider_duration(JSlider slider_duration) {
        this.slider_duration = slider_duration;
    }

    public JPanel getPnl_likes() {
        return pnl_likes;
    }

    public void setPnl_likes(JPanel pnl_likes) {
        this.pnl_likes = pnl_likes;
    }

    public JPanel getPnl_player() {
        return pnl_player;
    }

    public void setPnl_player(JPanel pnl_player) {
        this.pnl_player = pnl_player;
    }

    public JLabel getLbl_musicArtist1() {
        return lbl_musicArtist1;
    }

    public JLabel getLbl_musicTitle1() {
        return lbl_musicTitle1;
    }

    public JPanel getPnl_data() {
        return pnl_data;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home_pnl_all = new javax.swing.JPanel();
        search_pnl_leftSide = new javax.swing.JPanel();
        home_pnl_titleLogo = new javax.swing.JPanel();
        icon_logo = new javax.swing.JLabel();
        lbl_title = new javax.swing.JLabel();
        home_pnl_options = new javax.swing.JPanel();
        home_pnl_homeOpt = new javax.swing.JPanel();
        icon_home = new javax.swing.JLabel();
        lbl_home = new javax.swing.JLabel();
        home_pnl_searchOpt = new javax.swing.JPanel();
        icon_search = new javax.swing.JLabel();
        lbl_search = new javax.swing.JLabel();
        home_pnl_playlistOpt = new javax.swing.JPanel();
        icon_playlist = new javax.swing.JLabel();
        lbl_playlist = new javax.swing.JLabel();
        search_pnl_topSide = new javax.swing.JPanel();
        search_pnl_botSide = new javax.swing.JPanel();
        pnl_all = new javax.swing.JPanel();
        artist_photo = new javax.swing.JLabel();
        pnl_data = new javax.swing.JPanel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl_musicTitle1 = new javax.swing.JLabel();
        lbl_musicArtist1 = new javax.swing.JLabel();
        pnl_player = new javax.swing.JPanel();
        player_top = new javax.swing.JPanel();
        btt_play = new javax.swing.JButton();
        pnl_likes = new javax.swing.JPanel();
        lbl_musicLikes = new javax.swing.JLabel();
        btt_like = new javax.swing.JButton();
        pnl_dislikes = new javax.swing.JPanel();
        lbl_musicDislikes = new javax.swing.JLabel();
        btt_dislike = new javax.swing.JButton();
        slider_duration = new javax.swing.JSlider();
        search_pnl_inside = new javax.swing.JPanel();
        btt_profile = new RoundedButton("user_name");
        search_pnl_bar = new RoundedPanel();
        search_name = new PlaceholderFields("Digite o nome da música...", new Insets(0, 15, 0, 0));
        icon1 = new javax.swing.JLabel();
        scroll_musics = new javax.swing.JScrollPane();
        list_musics = new javax.swing.JList<>();
        search_pnl_musicInfo = new javax.swing.JPanel();
        pnl2 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl_musicTitle = new javax.swing.JLabel();
        lbl_musicArtist = new javax.swing.JLabel();
        pnl3 = new javax.swing.JPanel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl_musicDuration = new javax.swing.JLabel();
        lbl_musicGenre = new javax.swing.JLabel();
        musicDesc_scroll = new javax.swing.JScrollPane();
        lbl_musicDescription = new javax.swing.JTextArea();
        pnl4 = new javax.swing.JPanel();
        btt_addPlaylist = new RoundedButton("Adicionar à playlist");
        scroll_playlists = new javax.swing.JScrollPane();
        list_playlists = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spotifei");
        setResizable(false);

        home_pnl_all.setBackground(new java.awt.Color(28, 28, 28));

        search_pnl_leftSide.setBackground(new java.awt.Color(28, 28, 28));

        home_pnl_titleLogo.setBackground(new java.awt.Color(28, 28, 28));

        icon_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/logoSpotifei.png"))); // NOI18N
        icon_logo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbl_title.setFont(new Font("Gotham Black", Font.PLAIN, 24));
        lbl_title.setForeground(new java.awt.Color(30, 215, 96));
        lbl_title.setText("Spotifei");

        javax.swing.GroupLayout home_pnl_titleLogoLayout = new javax.swing.GroupLayout(home_pnl_titleLogo);
        home_pnl_titleLogo.setLayout(home_pnl_titleLogoLayout);
        home_pnl_titleLogoLayout.setHorizontalGroup(
            home_pnl_titleLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_titleLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_title)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        home_pnl_titleLogoLayout.setVerticalGroup(
            home_pnl_titleLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_titleLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(home_pnl_titleLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon_logo)
                    .addGroup(home_pnl_titleLogoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbl_title)))
                .addContainerGap())
        );

        home_pnl_options.setBackground(new java.awt.Color(28, 28, 28));
        home_pnl_options.setPreferredSize(new java.awt.Dimension(0, 348));
        home_pnl_options.setLayout(new javax.swing.BoxLayout(home_pnl_options, javax.swing.BoxLayout.Y_AXIS));

        home_pnl_homeOpt.setBackground(new java.awt.Color(28, 28, 28));
        home_pnl_homeOpt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home_pnl_homeOpt.setPreferredSize(new java.awt.Dimension(65, 58));
        home_pnl_homeOpt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_pnl_homeOptMouseClicked(evt);
            }
        });

        icon_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/homeIcon.png"))); // NOI18N
        home_pnl_homeOpt.add(icon_home);

        lbl_home.setFont(new Font("Gotham Black", Font.PLAIN, 16));
        lbl_home.setForeground(new java.awt.Color(168, 170, 170));
        lbl_home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_home.setText("Home");
        home_pnl_homeOpt.add(lbl_home);

        home_pnl_options.add(home_pnl_homeOpt);

        home_pnl_searchOpt.setBackground(new java.awt.Color(28, 28, 28));
        home_pnl_searchOpt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home_pnl_searchOpt.setPreferredSize(new java.awt.Dimension(65, 58));
        home_pnl_searchOpt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_pnl_searchOptMouseClicked(evt);
            }
        });

        icon_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/searchIcon.png"))); // NOI18N
        home_pnl_searchOpt.add(icon_search);

        lbl_search.setFont(new Font("Gotham Black", Font.PLAIN, 16));
        lbl_search.setForeground(new java.awt.Color(168, 170, 170));
        lbl_search.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_search.setText("Buscar");
        home_pnl_searchOpt.add(lbl_search);

        home_pnl_options.add(home_pnl_searchOpt);

        home_pnl_playlistOpt.setBackground(new java.awt.Color(28, 28, 28));
        home_pnl_playlistOpt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home_pnl_playlistOpt.setPreferredSize(new java.awt.Dimension(65, 58));
        home_pnl_playlistOpt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_pnl_playlistOptMouseClicked(evt);
            }
        });

        icon_playlist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/playlistIcon.png"))); // NOI18N
        home_pnl_playlistOpt.add(icon_playlist);

        lbl_playlist.setFont(new Font("Gotham Black", Font.PLAIN, 16));
        lbl_playlist.setForeground(new java.awt.Color(168, 170, 170));
        lbl_playlist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_playlist.setText("Playlists");
        home_pnl_playlistOpt.add(lbl_playlist);

        home_pnl_options.add(home_pnl_playlistOpt);

        javax.swing.GroupLayout search_pnl_leftSideLayout = new javax.swing.GroupLayout(search_pnl_leftSide);
        search_pnl_leftSide.setLayout(search_pnl_leftSideLayout);
        search_pnl_leftSideLayout.setHorizontalGroup(
            search_pnl_leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_pnl_leftSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(search_pnl_leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(home_pnl_options, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home_pnl_titleLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        search_pnl_leftSideLayout.setVerticalGroup(
            search_pnl_leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_pnl_leftSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home_pnl_titleLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(home_pnl_options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        search_pnl_topSide.setBackground(new java.awt.Color(28, 28, 28));

        javax.swing.GroupLayout search_pnl_topSideLayout = new javax.swing.GroupLayout(search_pnl_topSide);
        search_pnl_topSide.setLayout(search_pnl_topSideLayout);
        search_pnl_topSideLayout.setHorizontalGroup(
            search_pnl_topSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        search_pnl_topSideLayout.setVerticalGroup(
            search_pnl_topSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        search_pnl_botSide.setBackground(new java.awt.Color(28, 28, 28));

        pnl_all.setOpaque(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5);
        flowLayout1.setAlignOnBaseline(true);
        pnl_all.setLayout(flowLayout1);

        artist_photo.setVisible(false);
        artist_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/music_photo_test4.png"))); // NOI18N
        artist_photo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        artist_photo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        pnl_all.add(artist_photo);

        pnl_data.setBackground(new java.awt.Color(18, 18, 18));
        pnl_data.setOpaque(false);
        pnl_data.setPreferredSize(new java.awt.Dimension(256, 144));
        pnl_data.setVisible(false);

        lbl6.setFont(new Font("Gotham Black", Font.PLAIN, 20));
        lbl6.setText("Título");

        lbl7.setFont(new Font("Gotham Black", Font.PLAIN, 20));
        lbl7.setText("Artista");

        lbl_musicTitle1.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        lbl_musicTitle1.setForeground(new java.awt.Color(168, 168, 168));

        lbl_musicArtist1.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        lbl_musicArtist1.setForeground(new java.awt.Color(168, 168, 168));

        javax.swing.GroupLayout pnl_dataLayout = new javax.swing.GroupLayout(pnl_data);
        pnl_data.setLayout(pnl_dataLayout);
        pnl_dataLayout.setHorizontalGroup(
            pnl_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_musicArtist1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_musicTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl_dataLayout.createSequentialGroup()
                        .addGroup(pnl_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl6)
                            .addComponent(lbl7))
                        .addGap(0, 203, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_dataLayout.setVerticalGroup(
            pnl_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_musicTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_musicArtist1)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pnl_all.add(pnl_data);

        pnl_player.setBackground(new java.awt.Color(28, 28, 28));
        pnl_player.setVisible(false);

        player_top.setOpaque(false);

        btt_play.setFocusPainted(false);
        btt_play.setContentAreaFilled(false);
        btt_play.setBorderPainted(false);
        btt_play.setBackground(new java.awt.Color(28, 28, 28));
        btt_play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/play.png"))); // NOI18N
        btt_play.setBorder(null);
        btt_play.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btt_play.setOpaque(true);
        btt_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_playActionPerformed(evt);
            }
        });

        pnl_likes.setOpaque(false);
        pnl_likes.setVisible(false);

        lbl_musicLikes.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_musicLikes.setForeground(new java.awt.Color(168, 168, 168));
        lbl_musicLikes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        btt_like.setFocusPainted(false);
        btt_like.setContentAreaFilled(false);
        btt_like.setBorderPainted(false);
        btt_like.setBackground(new java.awt.Color(28, 28, 28));
        btt_like.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/like.png"))); // NOI18N
        btt_like.setBorder(null);
        btt_like.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnl_likesLayout = new javax.swing.GroupLayout(pnl_likes);
        pnl_likes.setLayout(pnl_likesLayout);
        pnl_likesLayout.setHorizontalGroup(
            pnl_likesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_likesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_musicLikes, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btt_like))
        );
        pnl_likesLayout.setVerticalGroup(
            pnl_likesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_likesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnl_likesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btt_like)
                    .addComponent(lbl_musicLikes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnl_dislikes.setOpaque(false);

        lbl_musicDislikes.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_musicDislikes.setForeground(new java.awt.Color(168, 168, 168));
        lbl_musicDislikes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btt_dislike.setFocusPainted(false);
        btt_dislike.setContentAreaFilled(false);
        btt_dislike.setBorderPainted(false);
        btt_dislike.setBackground(new java.awt.Color(28, 28, 28));
        btt_dislike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/dislike.png"))); // NOI18N
        btt_dislike.setBorder(null);
        btt_dislike.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnl_dislikesLayout = new javax.swing.GroupLayout(pnl_dislikes);
        pnl_dislikes.setLayout(pnl_dislikesLayout);
        pnl_dislikesLayout.setHorizontalGroup(
            pnl_dislikesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dislikesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btt_dislike)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_musicDislikes, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_dislikesLayout.setVerticalGroup(
            pnl_dislikesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dislikesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnl_dislikesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btt_dislike, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_musicDislikes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout player_topLayout = new javax.swing.GroupLayout(player_top);
        player_top.setLayout(player_topLayout);
        player_topLayout.setHorizontalGroup(
            player_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player_topLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnl_likes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btt_play)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl_dislikes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        player_topLayout.setVerticalGroup(
            player_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_dislikes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, player_topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(btt_play, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl_likes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        slider_duration.setUI(new BasicSliderUI(slider_duration) {
            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Cor e forma do thumb
                g2.setColor(new Color(54, 54, 54));
                int thumbSize = 16;
                int x = thumbRect.x + (thumbRect.width - thumbSize) / 2;
                int y = thumbRect.y + (thumbRect.height - thumbSize) / 2;
                g2.fillOval(x, y, thumbSize, thumbSize);
                g2.dispose();
            }

            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int trackHeight = 4;
                int y = trackRect.y + (trackRect.height - trackHeight) / 2;

                g2.setColor(new Color(30, 215, 96));
                g2.fillRoundRect(trackRect.x, y, trackRect.width, trackHeight, 10, 10);
                g2.dispose();
            }

            @Override
            public void paintFocus(Graphics g) {
                // Não desenha nada ao focar
            }
        });

        // Remover fundo e borda
        slider_duration.setOpaque(false);
        slider_duration.setBackground(new Color(0,0,0,0)); // transparente
        slider_duration.setFocusable(false);
        slider_duration.setBackground(new java.awt.Color(28, 28, 28));
        slider_duration.setValue(0);
        slider_duration.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15));
        slider_duration.setEnabled(false);
        slider_duration.setMaximumSize(new java.awt.Dimension(300, 50));
        slider_duration.setMinimumSize(new java.awt.Dimension(300, 50));
        slider_duration.setPreferredSize(new java.awt.Dimension(300, 50));

        javax.swing.GroupLayout pnl_playerLayout = new javax.swing.GroupLayout(pnl_player);
        pnl_player.setLayout(pnl_playerLayout);
        pnl_playerLayout.setHorizontalGroup(
            pnl_playerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_playerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnl_playerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(slider_duration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player_top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnl_playerLayout.setVerticalGroup(
            pnl_playerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_playerLayout.createSequentialGroup()
                .addComponent(player_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(slider_duration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_all.add(pnl_player);

        javax.swing.GroupLayout search_pnl_botSideLayout = new javax.swing.GroupLayout(search_pnl_botSide);
        search_pnl_botSide.setLayout(search_pnl_botSideLayout);
        search_pnl_botSideLayout.setHorizontalGroup(
            search_pnl_botSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_pnl_botSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_all, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                .addContainerGap())
        );
        search_pnl_botSideLayout.setVerticalGroup(
            search_pnl_botSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, search_pnl_botSideLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnl_all, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        search_pnl_inside.setBackground(new java.awt.Color(18, 18, 18));
        search_pnl_inside.setPreferredSize(new java.awt.Dimension(48, 20));

        ((RoundedButton) btt_profile).setTextAlignment(TextAlign.LEFT);
        btt_profile.setBackground(new java.awt.Color(185, 192, 198));
        btt_profile.setFont(new java.awt.Font("Gotham", Font.PLAIN, 12));
        btt_profile.setForeground(new java.awt.Color(28, 28, 28));
        btt_profile.setText("user_name");
        btt_profile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(28, 28, 28), 1, true));
        btt_profile.setBorderPainted(false);
        btt_profile.setPreferredSize(new Dimension(200,30));
        btt_profile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ((RoundedButton) btt_profile).setCornerRadiusVertical(80);
        ((RoundedButton) btt_profile).setCornerRadiusHorizontal(40);
        btt_profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_profileActionPerformed(evt);
            }
        });

        search_pnl_bar.setBackground(new java.awt.Color(51, 51, 51));
        search_pnl_bar.setPreferredSize(new java.awt.Dimension(289, 0));

        ((PlaceholderFields) search_name).setHoverBackgroundColor(new Color(51,51,51));
        search_name.setBackground(new java.awt.Color(51, 51, 51));
        search_name.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        search_name.setForeground(new java.awt.Color(168, 168, 168));
        search_name.setBorder(null);
        search_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_nameActionPerformed(evt);
            }
        });

        icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/searchIcon_gray_small.png"))); // NOI18N

        javax.swing.GroupLayout search_pnl_barLayout = new javax.swing.GroupLayout(search_pnl_bar);
        search_pnl_bar.setLayout(search_pnl_barLayout);
        search_pnl_barLayout.setHorizontalGroup(
            search_pnl_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_pnl_barLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon1)
                .addContainerGap())
        );
        search_pnl_barLayout.setVerticalGroup(
            search_pnl_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_pnl_barLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search_name, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(icon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ((RoundedPanel) search_pnl_bar).setCornerRadiusHorizontal(80);
        ((RoundedPanel) search_pnl_bar).setCornerRadiusHorizontal(40);
        // mesma cor de bg e hover para "desativar" a animação
        ((RoundedPanel) search_pnl_bar).setNormalColor(new Color(51,51,51));
        ((RoundedPanel) search_pnl_bar).setHoverColor(new Color(51,51,51));

        scroll_musics.setBackground(new java.awt.Color(60, 63, 65));
        scroll_musics.setBorder(null);
        scroll_musics.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(60,63,65);
                this.trackColor = new Color(18,18,18);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            // barra de scroll mais larga
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(12, super.getPreferredSize(c).height);
            }

            // arredonda o botão
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.translate(thumbBounds.x, thumbBounds.y);
                g2.setColor(thumbColor);
                int arc = 10;
                g2.fillRoundRect(0, 0, thumbBounds.width, thumbBounds.height, arc, arc);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(trackColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
        });

        scroll_musics.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(60,63,65);
                this.trackColor = new Color(18,18,18);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            // barra de scroll mais larga
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(super.getPreferredSize(c).width, 12);
            }

            // arredonda o botão
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.translate(thumbBounds.x, thumbBounds.y);
                g2.setColor(thumbColor);
                int arc = 10;
                g2.fillRoundRect(0, 0, thumbBounds.width, thumbBounds.height, arc, arc);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(trackColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
        });
        scroll_musics.setForeground(new java.awt.Color(58, 58, 58));

        list_musics.setBackground(new java.awt.Color(18, 18, 18));
        list_musics.setBorder(null);
        list_musics.setFont(new Font("Gotham Light", Font.PLAIN, 14));
        list_musics.setForeground(new java.awt.Color(236, 239, 241));
        list_musics.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_musics.setSelectionBackground(new java.awt.Color(100, 165, 135));
        scroll_musics.setViewportView(list_musics);

        search_pnl_musicInfo.setBackground(new java.awt.Color(18, 18, 18));
        search_pnl_musicInfo.setLayout(new java.awt.GridLayout(1, 0));
        search_pnl_musicInfo.setVisible(false);

        pnl2.setBackground(new java.awt.Color(18, 18, 18));

        lbl1.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl1.setText("Título");

        lbl2.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl2.setText("Artista");

        lbl_musicTitle.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_musicTitle.setForeground(new java.awt.Color(168, 168, 168));

        lbl_musicArtist.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_musicArtist.setForeground(new java.awt.Color(168, 168, 168));

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_musicArtist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_musicTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl1)
                            .addComponent(lbl2))
                        .addGap(0, 230, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(lbl1)
                .addGap(18, 18, 18)
                .addComponent(lbl_musicTitle)
                .addGap(18, 18, 18)
                .addComponent(lbl2)
                .addGap(18, 18, 18)
                .addComponent(lbl_musicArtist)
                .addGap(107, 107, 107))
        );

        search_pnl_musicInfo.add(pnl2);

        pnl3.setBackground(new java.awt.Color(18, 18, 18));

        lbl3.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl3.setText("Gênero");

        lbl4.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl4.setText("Decrição");

        lbl5.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl5.setText("Duração");

        lbl_musicDuration.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_musicDuration.setForeground(new java.awt.Color(168, 168, 168));

        lbl_musicGenre.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_musicGenre.setForeground(new java.awt.Color(168, 168, 168));

        musicDesc_scroll.setBackground(new java.awt.Color(18, 18, 18));
        musicDesc_scroll.setBorder(null);
        musicDesc_scroll.setForeground(new java.awt.Color(168, 168, 168));
        musicDesc_scroll.setToolTipText("");

        lbl_musicDescription.setBackground(new java.awt.Color(18, 18, 18));
        lbl_musicDescription.setColumns(20);
        lbl_musicDescription.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_musicDescription.setForeground(new java.awt.Color(168, 168, 168));
        lbl_musicDescription.setLineWrap(true);
        lbl_musicDescription.setRows(4);
        lbl_musicDescription.setWrapStyleWord(true);
        lbl_musicDescription.setDisabledTextColor(new java.awt.Color(168, 168, 168));
        lbl_musicDescription.setEnabled(false);
        lbl_musicDescription.setSelectedTextColor(new java.awt.Color(168, 168, 168));
        musicDesc_scroll.setViewportView(lbl_musicDescription);

        musicDesc_scroll.setBorder(null);
        musicDesc_scroll.setViewportBorder(null);
        musicDesc_scroll.setOpaque(false);
        musicDesc_scroll.getViewport().setOpaque(false);
        musicDesc_scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(60,63,65);
                this.trackColor = new Color(18,18,18);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            // barra de scroll mais larga
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(12, super.getPreferredSize(c).height);
            }

            // arredonda o botão
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.translate(thumbBounds.x, thumbBounds.y);
                g2.setColor(thumbColor);
                int arc = 10;
                g2.fillRoundRect(0, 0, thumbBounds.width, thumbBounds.height, arc, arc);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(trackColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
        });

        musicDesc_scroll.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(60,63,65);
                this.trackColor = new Color(18,18,18);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            // barra de scroll mais larga
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(super.getPreferredSize(c).width, 12);
            }

            // arredonda o botão
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.translate(thumbBounds.x, thumbBounds.y);
                g2.setColor(thumbColor);
                int arc = 10;
                g2.fillRoundRect(0, 0, thumbBounds.width, thumbBounds.height, arc, arc);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(trackColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
        });

        javax.swing.GroupLayout pnl3Layout = new javax.swing.GroupLayout(pnl3);
        pnl3.setLayout(pnl3Layout);
        pnl3Layout.setHorizontalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(musicDesc_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl3)
                    .addComponent(lbl_musicGenre)
                    .addComponent(lbl4)
                    .addComponent(lbl5)
                    .addComponent(lbl_musicDuration))
                .addGap(0, 230, Short.MAX_VALUE))
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl3Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(lbl3)
                .addGap(18, 18, 18)
                .addComponent(lbl_musicGenre)
                .addGap(18, 18, 18)
                .addComponent(lbl4)
                .addGap(18, 18, 18)
                .addComponent(musicDesc_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl5)
                .addGap(18, 18, 18)
                .addComponent(lbl_musicDuration)
                .addGap(14, 14, 14))
        );

        search_pnl_musicInfo.add(pnl3);

        pnl4.setBackground(new java.awt.Color(18, 18, 18));
        pnl4.setPreferredSize(new java.awt.Dimension(96, 320));

        btt_addPlaylist.setBackground(new java.awt.Color(185, 192, 198));
        btt_addPlaylist.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        btt_addPlaylist.setForeground(new java.awt.Color(28, 28, 28));
        btt_addPlaylist.setText("Adicionar à playlist");
        btt_addPlaylist.setBorder(null);
        btt_addPlaylist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        scroll_playlists.setBackground(new java.awt.Color(60, 63, 65));
        scroll_playlists.setBorder(null);
        scroll_playlists.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(60,63,65);
                this.trackColor = new Color(18,18,18);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            // barra de scroll mais larga
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(12, super.getPreferredSize(c).height);
            }

            // arredonda o botão
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.translate(thumbBounds.x, thumbBounds.y);
                g2.setColor(thumbColor);
                int arc = 10;
                g2.fillRoundRect(0, 0, thumbBounds.width, thumbBounds.height, arc, arc);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(trackColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
        });

        scroll_playlists.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(60,63,65);
                this.trackColor = new Color(18,18,18);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            // barra de scroll mais larga
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(super.getPreferredSize(c).width, 12);
            }

            // arredonda o botão
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.translate(thumbBounds.x, thumbBounds.y);
                g2.setColor(thumbColor);
                int arc = 10;
                g2.fillRoundRect(0, 0, thumbBounds.width, thumbBounds.height, arc, arc);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(trackColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }
        });
        scroll_playlists.setForeground(new java.awt.Color(58, 58, 58));

        list_playlists.setBackground(new java.awt.Color(54, 54, 54));
        list_playlists.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        list_playlists.setFont(new Font("Gotham Light", Font.PLAIN, 14));
        list_playlists.setForeground(new java.awt.Color(236, 239, 241));
        list_playlists.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "playlists" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        list_playlists.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_playlists.setSelectionBackground(new java.awt.Color(100, 165, 135));
        scroll_playlists.setViewportView(list_playlists);

        jLabel1.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Suas playlists");

        javax.swing.GroupLayout pnl4Layout = new javax.swing.GroupLayout(pnl4);
        pnl4.setLayout(pnl4Layout);
        pnl4Layout.setHorizontalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btt_addPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scroll_playlists, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnl4Layout.setVerticalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl4Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll_playlists, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btt_addPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        search_pnl_musicInfo.add(pnl4);

        javax.swing.GroupLayout search_pnl_insideLayout = new javax.swing.GroupLayout(search_pnl_inside);
        search_pnl_inside.setLayout(search_pnl_insideLayout);
        search_pnl_insideLayout.setHorizontalGroup(
            search_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_pnl_insideLayout.createSequentialGroup()
                .addGroup(search_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(search_pnl_insideLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addGroup(search_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scroll_musics, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(search_pnl_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                        .addGap(212, 212, 212)
                        .addComponent(btt_profile, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(search_pnl_insideLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(search_pnl_musicInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        search_pnl_insideLayout.setVerticalGroup(
            search_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_pnl_insideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(search_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btt_profile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_pnl_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll_musics, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(search_pnl_musicInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout home_pnl_allLayout = new javax.swing.GroupLayout(home_pnl_all);
        home_pnl_all.setLayout(home_pnl_allLayout);
        home_pnl_allLayout.setHorizontalGroup(
            home_pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(search_pnl_botSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(home_pnl_allLayout.createSequentialGroup()
                .addComponent(search_pnl_leftSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(home_pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search_pnl_inside, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                    .addComponent(search_pnl_topSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        home_pnl_allLayout.setVerticalGroup(
            home_pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_allLayout.createSequentialGroup()
                .addGroup(home_pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(home_pnl_allLayout.createSequentialGroup()
                        .addComponent(search_pnl_topSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_pnl_inside, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
                    .addComponent(search_pnl_leftSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_pnl_botSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home_pnl_all, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home_pnl_all, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void home_pnl_homeOptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pnl_homeOptMouseClicked
        // TODO add your handling code here:
        try{
            HomeWindow hw = new HomeWindow(user);
            hw.setVisible(rootPaneCheckingEnabled);
            c.stopMusic();
            this.setVisible(false);
        } catch(IOException i){
            i.printStackTrace();
        }
    }//GEN-LAST:event_home_pnl_homeOptMouseClicked

    private void home_pnl_searchOptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pnl_searchOptMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_home_pnl_searchOptMouseClicked

    private void home_pnl_playlistOptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pnl_playlistOptMouseClicked
        // TODO add your handling code here:
        PlaylistsWindow pw = new PlaylistsWindow(user);
        pw.setVisible(rootPaneCheckingEnabled);
        c.stopMusic();
        this.setVisible(false);
    }//GEN-LAST:event_home_pnl_playlistOptMouseClicked

    private void btt_profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_profileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btt_profileActionPerformed

    private void search_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_nameActionPerformed
        // TODO add your handling code here:
        c.searchMusic();
    }//GEN-LAST:event_search_nameActionPerformed

    private void btt_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_playActionPerformed
        // TODO add your handling code here:
        c.playMusic();
    }//GEN-LAST:event_btt_playActionPerformed
    
    private MusicSearchController c;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel artist_photo;
    private javax.swing.JButton btt_addPlaylist;
    private javax.swing.JButton btt_dislike;
    private javax.swing.JButton btt_like;
    private javax.swing.JButton btt_play;
    private javax.swing.JButton btt_profile;
    private javax.swing.JPanel home_pnl_all;
    private javax.swing.JPanel home_pnl_homeOpt;
    private javax.swing.JPanel home_pnl_options;
    private javax.swing.JPanel home_pnl_playlistOpt;
    private javax.swing.JPanel home_pnl_searchOpt;
    private javax.swing.JPanel home_pnl_titleLogo;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon_home;
    private javax.swing.JLabel icon_logo;
    private javax.swing.JLabel icon_playlist;
    private javax.swing.JLabel icon_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl_home;
    private javax.swing.JLabel lbl_musicArtist;
    private javax.swing.JLabel lbl_musicArtist1;
    private javax.swing.JTextArea lbl_musicDescription;
    private javax.swing.JLabel lbl_musicDislikes;
    private javax.swing.JLabel lbl_musicDuration;
    private javax.swing.JLabel lbl_musicGenre;
    private javax.swing.JLabel lbl_musicLikes;
    private javax.swing.JLabel lbl_musicTitle;
    private javax.swing.JLabel lbl_musicTitle1;
    private javax.swing.JLabel lbl_playlist;
    private javax.swing.JLabel lbl_search;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JList<String> list_musics;
    private javax.swing.JList<String> list_playlists;
    private javax.swing.JScrollPane musicDesc_scroll;
    private javax.swing.JPanel player_top;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl_all;
    private javax.swing.JPanel pnl_data;
    private javax.swing.JPanel pnl_dislikes;
    private javax.swing.JPanel pnl_likes;
    private javax.swing.JPanel pnl_player;
    private javax.swing.JScrollPane scroll_musics;
    private javax.swing.JScrollPane scroll_playlists;
    private javax.swing.JTextField search_name;
    private javax.swing.JPanel search_pnl_bar;
    private javax.swing.JPanel search_pnl_botSide;
    private javax.swing.JPanel search_pnl_inside;
    private javax.swing.JPanel search_pnl_leftSide;
    private javax.swing.JPanel search_pnl_musicInfo;
    private javax.swing.JPanel search_pnl_topSide;
    private javax.swing.JSlider slider_duration;
    // End of variables declaration//GEN-END:variables
}
