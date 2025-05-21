/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.AdmHomeController;
import view.customClasses.RoundedButton;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import model.Adm;
import view.customClasses.RoundedButton.*;
import view.customClasses.RoundedPanel;

/**
 * Janela principal da interface administrativa do Spotifei.
 * Esta janela exibe informações relevantes para o administrador, como:
 * - As 5 músicas mais bem avaliadas
 * - As 5 músicas menos avaliadas
 * - Total de usuários cadastrados
 * - Total de músicas no sistema
 * 
 * Também realiza a personalização visual da janela, como centralização e definição de ícone.
 * 
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class AdmHomeWindow extends javax.swing.JFrame {
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Dimension screenSize = toolkit.getScreenSize();
    private final int width = screenSize.width;
    private final int height = screenSize.height;
    private Adm adm;
    
     /**
     * Construtor da janela administrativa.
     * Inicializa os componentes gráficos e configura o controlador da janela.
     * Também define o tamanho da janela para tela cheia, centraliza a janela,
     * atualiza o ícone da aplicação e preenche os dados estatísticos do sistema.
     * 
     * @param adm Objeto que representa o administrador logado.
     * @throws IOException Caso ocorra erro ao carregar os recursos visuais da interface.
     */
    public AdmHomeWindow(Adm adm) throws IOException {
        initComponents();
        c =  new AdmHomeController(this, adm);
        c.fillTopFive();
        c.fillTopFiveBad();
        c.totalUsers();
        c.totalMusics();
        this.adm = adm;
        
        this.setSize(width, height);
        
        // changes window icon
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/logoSpotifei.png")).getImage());
        
        // center the window

        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        
        this.setLocation(new Point(x,y));
        c.setUserNameOnWindow();
    }
    
    public JButton getBtt_profile() {
        return btt_profile;
    }

    public void setBtt_profile(JButton btt_profile) {
        this.btt_profile = btt_profile;
    }

    public JLabel getLbl_welcome() {
        return lbl_welcome;
    }

    public void setLbl_welcome(JLabel lbl_welcome) {
        this.lbl_welcome = lbl_welcome;
    }
    
    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public JLabel getjLabel4() {
        return jLabel4;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    // label do top 5
    public JLabel getjLabel6() {
        return jLabel6;
    }

    public JLabel getjLabel7() {
        return jLabel7;
    }

    public JLabel getjLabel8() {
        return jLabel8;
    }

    public JLabel getjLabel9() {
        return jLabel9;
    }
    
    public JLabel getjLabel10() {
        return jLabel10;
    }

    public JLabel getjLabel11() {
        return jLabel11;
    }
    
    public JLabel getArtist1() {
        return artist1;
    }

    public JLabel getArtist2() {
        return artist2;
    }

    public JLabel getArtist3() {
        return artist3;
    }

    public JLabel getArtist4() {
        return artist4;
    }

    public JLabel getArtist5() {
        return artist5;
    }
    
    public JLabel getArtist6() {
        return artist6;
    }

    public JLabel getArtist7() {
        return artist7;
    }

    public JLabel getArtist8() {
        return artist8;
    }

    public JLabel getArtist9() {
        return artist9;
    }
    
    public JLabel getArtist10() {
        return artist10;
    }


    public JLabel getTitle1() {
        return title1;
    }

    public JLabel getTitle2() {
        return title2;
    }

    public JLabel getTitle3() {
        return title3;
    }

    public JLabel getTitle4() {
        return title4;
    }

    public JLabel getTitle5() {
        return title5;
    }

    public JLabel getTitle6() {
        return title6;
    }

    public JLabel getTitle7() {
        return title7;
    }

    public JLabel getTitle8() {
        return title8;
    }

    public JLabel getTitle9() {
        return title9;
    }
    
    public JLabel getTitle10() {
        return title10;
    }

    public JLabel getLbl_numberMusics() {
        return lbl_numberMusics;
    }

    public JLabel getLbl_numberUsers() {
        return lbl_numberUsers;
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
        home_pnl_leftSide = new javax.swing.JPanel();
        home_pnl_titleLogo = new javax.swing.JPanel();
        icon_logo = new javax.swing.JLabel();
        lbl_title = new javax.swing.JLabel();
        home_pnl_options = new javax.swing.JPanel();
        home_pnl_homeOpt = new javax.swing.JPanel();
        icon_home = new javax.swing.JLabel();
        lbl_home = new javax.swing.JLabel();
        home_pnl_configsOpt = new javax.swing.JPanel();
        icon_config = new javax.swing.JLabel();
        lbl_config = new javax.swing.JLabel();
        home_pnl_topSide = new javax.swing.JPanel();
        home_pnl_botSide = new javax.swing.JPanel();
        pnl_numbers = new javax.swing.JPanel();
        lbl_numberUsers = new javax.swing.JLabel();
        lbl_numberMusics = new javax.swing.JLabel();
        lbl_users = new javax.swing.JLabel();
        lbl_musics = new javax.swing.JLabel();
        home_pnl_inside = new javax.swing.JPanel();
        btt_profile = new RoundedButton("user_name");
        home_pnl_welcome = new javax.swing.JPanel();
        spacing1 = new javax.swing.JPanel();
        lbl_welcome = new javax.swing.JLabel();
        icon_wave = new javax.swing.JLabel();
        spacing2 = new javax.swing.JPanel();
        home_pnl_topMusicsLike = new RoundedPanel();
        music1 = new RoundedPanel();
        top1 = new javax.swing.JLabel();
        title1 = new javax.swing.JLabel();
        artist1 = new javax.swing.JLabel();
        photo1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        music2 = new RoundedPanel();
        top2 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();
        artist2 = new javax.swing.JLabel();
        photo2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        music3 = new RoundedPanel();
        top3 = new javax.swing.JLabel();
        title3 = new javax.swing.JLabel();
        artist3 = new javax.swing.JLabel();
        photo3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        music4 = new RoundedPanel();
        top4 = new javax.swing.JLabel();
        title4 = new javax.swing.JLabel();
        artist4 = new javax.swing.JLabel();
        photo4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        music5 = new RoundedPanel();
        top5 = new javax.swing.JLabel();
        title5 = new javax.swing.JLabel();
        artist5 = new javax.swing.JLabel();
        photo5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        home_pnl_topMusicsDislike = new RoundedPanel();
        music6 = new RoundedPanel();
        top6 = new javax.swing.JLabel();
        title6 = new javax.swing.JLabel();
        artist6 = new javax.swing.JLabel();
        photo6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        music7 = new RoundedPanel();
        top7 = new javax.swing.JLabel();
        title7 = new javax.swing.JLabel();
        artist7 = new javax.swing.JLabel();
        photo7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        music8 = new RoundedPanel();
        top8 = new javax.swing.JLabel();
        title8 = new javax.swing.JLabel();
        artist8 = new javax.swing.JLabel();
        photo8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        music9 = new RoundedPanel();
        top9 = new javax.swing.JLabel();
        title9 = new javax.swing.JLabel();
        artist9 = new javax.swing.JLabel();
        photo9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        music10 = new RoundedPanel();
        top10 = new javax.swing.JLabel();
        title10 = new javax.swing.JLabel();
        artist10 = new javax.swing.JLabel();
        photo10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spotifei");
        setResizable(false);

        home_pnl_all.setBackground(new java.awt.Color(28, 28, 28));
        home_pnl_all.setMaximumSize(new java.awt.Dimension(1024, 1024));
        home_pnl_all.setMinimumSize(new java.awt.Dimension(1024, 1024));
        home_pnl_all.setPreferredSize(new java.awt.Dimension(1024, 1024));

        home_pnl_leftSide.setBackground(new java.awt.Color(28, 28, 28));

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

        home_pnl_configsOpt.setBackground(new java.awt.Color(28, 28, 28));
        home_pnl_configsOpt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home_pnl_configsOpt.setPreferredSize(new java.awt.Dimension(65, 58));
        home_pnl_configsOpt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_pnl_configsOptMouseClicked(evt);
            }
        });

        icon_config.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/configIcon.png"))); // NOI18N
        home_pnl_configsOpt.add(icon_config);

        lbl_config.setFont(new Font("Gotham Black", Font.PLAIN, 16));
        lbl_config.setForeground(new java.awt.Color(168, 170, 170));
        lbl_config.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_config.setText("Gerenciar");
        home_pnl_configsOpt.add(lbl_config);

        home_pnl_options.add(home_pnl_configsOpt);

        javax.swing.GroupLayout home_pnl_leftSideLayout = new javax.swing.GroupLayout(home_pnl_leftSide);
        home_pnl_leftSide.setLayout(home_pnl_leftSideLayout);
        home_pnl_leftSideLayout.setHorizontalGroup(
            home_pnl_leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_leftSideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(home_pnl_leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(home_pnl_options, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home_pnl_titleLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        home_pnl_leftSideLayout.setVerticalGroup(
            home_pnl_leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_leftSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home_pnl_titleLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(home_pnl_options, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        home_pnl_topSide.setBackground(new java.awt.Color(28, 28, 28));

        javax.swing.GroupLayout home_pnl_topSideLayout = new javax.swing.GroupLayout(home_pnl_topSide);
        home_pnl_topSide.setLayout(home_pnl_topSideLayout);
        home_pnl_topSideLayout.setHorizontalGroup(
            home_pnl_topSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        home_pnl_topSideLayout.setVerticalGroup(
            home_pnl_topSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        home_pnl_botSide.setBackground(new java.awt.Color(28, 28, 28));

        pnl_numbers.setOpaque(false);

        lbl_numberUsers.setFont(new Font("Gotham Black", Font.PLAIN, 16));
        lbl_numberUsers.setForeground(new java.awt.Color(168, 168, 168));
        lbl_numberUsers.setText("numero_usuarios");

        lbl_numberMusics.setFont(new Font("Gotham Black", Font.PLAIN, 16));
        lbl_numberMusics.setForeground(new java.awt.Color(168, 168, 168));
        lbl_numberMusics.setText("numero_musicas");

        lbl_users.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_users.setForeground(new java.awt.Color(96, 96, 96));
        lbl_users.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_users.setText("Número total de usuários:");

        lbl_musics.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        lbl_musics.setForeground(new java.awt.Color(96, 96, 96));
        lbl_musics.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_musics.setText("Número total de músicas:");
        lbl_musics.setToolTipText("");

        javax.swing.GroupLayout pnl_numbersLayout = new javax.swing.GroupLayout(pnl_numbers);
        pnl_numbers.setLayout(pnl_numbersLayout);
        pnl_numbersLayout.setHorizontalGroup(
            pnl_numbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_numbersLayout.createSequentialGroup()
                .addGroup(pnl_numbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_musics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_users, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_numbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_numberMusics)
                    .addComponent(lbl_numberUsers))
                .addContainerGap())
        );
        pnl_numbersLayout.setVerticalGroup(
            pnl_numbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_numbersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_numbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_numberUsers)
                    .addComponent(lbl_users))
                .addGap(18, 18, 18)
                .addGroup(pnl_numbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_numberMusics)
                    .addComponent(lbl_musics))
                .addContainerGap())
        );

        javax.swing.GroupLayout home_pnl_botSideLayout = new javax.swing.GroupLayout(home_pnl_botSide);
        home_pnl_botSide.setLayout(home_pnl_botSideLayout);
        home_pnl_botSideLayout.setHorizontalGroup(
            home_pnl_botSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, home_pnl_botSideLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnl_numbers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        home_pnl_botSideLayout.setVerticalGroup(
            home_pnl_botSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_botSideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_numbers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        home_pnl_inside.setBackground(new java.awt.Color(18, 18, 18));

        ((RoundedButton) btt_profile).setTextAlignment(TextAlign.LEFT);
        btt_profile.setBackground(new java.awt.Color(185, 192, 198));
        btt_profile.setFont(new java.awt.Font("Gotham", Font.PLAIN, 12));
        btt_profile.setForeground(new java.awt.Color(28, 28, 28));
        btt_profile.setText("user_name");
        btt_profile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(28, 28, 28), 1, true));
        btt_profile.setBorderPainted(false);
        btt_profile.setPreferredSize(new Dimension(200,30));
        btt_profile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btt_profile.setPreferredSize(new java.awt.Dimension(48, 20));
        ((RoundedButton) btt_profile).setCornerRadiusVertical(80);
        ((RoundedButton) btt_profile).setCornerRadiusHorizontal(40);
        btt_profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_profileActionPerformed(evt);
            }
        });

        home_pnl_welcome.setBackground(new java.awt.Color(18, 18, 18));
        home_pnl_welcome.setLayout(new javax.swing.BoxLayout(home_pnl_welcome, javax.swing.BoxLayout.LINE_AXIS));

        spacing1.setBackground(new java.awt.Color(18, 18, 18));
        spacing1.setPreferredSize(new java.awt.Dimension(200, 100));

        javax.swing.GroupLayout spacing1Layout = new javax.swing.GroupLayout(spacing1);
        spacing1.setLayout(spacing1Layout);
        spacing1Layout.setHorizontalGroup(
            spacing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );
        spacing1Layout.setVerticalGroup(
            spacing1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        home_pnl_welcome.add(spacing1);

        lbl_welcome.setFont(new java.awt.Font("Gotham Black", Font.PLAIN, 18));
        lbl_welcome.setForeground(new java.awt.Color(168, 170, 170));
        lbl_welcome.setText("Bem-vindo(a), administrador user_name!");
        lbl_welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        home_pnl_welcome.add(lbl_welcome);

        icon_wave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/waveEmoji.png"))); // NOI18N
        home_pnl_welcome.add(icon_wave);

        spacing2.setBackground(new java.awt.Color(18, 18, 18));
        spacing2.setPreferredSize(new java.awt.Dimension(200, 100));

        javax.swing.GroupLayout spacing2Layout = new javax.swing.GroupLayout(spacing2);
        spacing2.setLayout(spacing2Layout);
        spacing2Layout.setHorizontalGroup(
            spacing2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );
        spacing2Layout.setVerticalGroup(
            spacing2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        home_pnl_welcome.add(spacing2);

        ((RoundedPanel) home_pnl_topMusicsLike).setHover(0);
        home_pnl_topMusicsLike.setBackground(new java.awt.Color(54, 54, 54));
        home_pnl_topMusicsLike.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        home_pnl_topMusicsLike.setMaximumSize(new java.awt.Dimension(847, 354));
        home_pnl_topMusicsLike.setMinimumSize(new java.awt.Dimension(847, 354));
        home_pnl_topMusicsLike.setPreferredSize(new java.awt.Dimension(847, 354));
        java.awt.GridBagLayout home_pnl_topMusicsLayout = new java.awt.GridBagLayout();
        home_pnl_topMusicsLayout.columnWidths = new int[] {20, 20, 20};
        home_pnl_topMusicsLayout.columnWeights = new double[] {5.0, 5.0, 5.0, 5.0, 5.0};
        home_pnl_topMusicsLike.setLayout(home_pnl_topMusicsLayout);

        ((RoundedPanel) music1).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music1).setHoverColor(new Color(32,32,32));
        music1.setBackground(new java.awt.Color(18, 18, 18));
        music1.setMaximumSize(new java.awt.Dimension(200, 300));
        music1.setMinimumSize(new java.awt.Dimension(200, 300));
        music1.setPreferredSize(new java.awt.Dimension(200, 300));

        top1.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top1.setForeground(new java.awt.Color(168, 168, 168));
        top1.setText("1.");

        title1.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title1.setForeground(new java.awt.Color(168, 168, 168));
        title1.setText("title");

        artist1.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist1.setForeground(new java.awt.Color(168, 168, 168));
        artist1.setText("artist");

        photo1.setBackground(new java.awt.Color(18, 18, 18));
        photo1.setOpaque(false);
        photo1.setLayout(new java.awt.GridBagLayout());
        photo1.add(jLabel1, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music1Layout = new javax.swing.GroupLayout(music1);
        music1.setLayout(music1Layout);
        music1Layout.setHorizontalGroup(
            music1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addGroup(music1Layout.createSequentialGroup()
                        .addGroup(music1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title1)
                            .addComponent(artist1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(music1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top1)
                    .addContainerGap(185, Short.MAX_VALUE)))
        );
        music1Layout.setVerticalGroup(
            music1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title1)
                .addGap(18, 18, 18)
                .addComponent(artist1)
                .addGap(18, 18, 18))
            .addGroup(music1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top1)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsLike.add(music1, new java.awt.GridBagConstraints());

        ((RoundedPanel) music2).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music2).setHoverColor(new Color(32,32,32));
        music2.setBackground(new java.awt.Color(18, 18, 18));
        music2.setMaximumSize(new java.awt.Dimension(200, 300));
        music2.setMinimumSize(new java.awt.Dimension(200, 300));
        music2.setPreferredSize(new java.awt.Dimension(200, 300));

        top2.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top2.setForeground(new java.awt.Color(168, 168, 168));
        top2.setText("2.");

        title2.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title2.setForeground(new java.awt.Color(168, 168, 168));
        title2.setText("title");

        artist2.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist2.setForeground(new java.awt.Color(168, 168, 168));
        artist2.setText("artist");

        photo2.setBackground(new java.awt.Color(18, 18, 18));
        photo2.setOpaque(false);
        photo2.setLayout(new java.awt.GridBagLayout());
        photo2.add(jLabel2, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music2Layout = new javax.swing.GroupLayout(music2);
        music2.setLayout(music2Layout);
        music2Layout.setHorizontalGroup(
            music2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(music2Layout.createSequentialGroup()
                        .addGroup(music2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artist2)
                            .addComponent(title2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(photo2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(music2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top2)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        music2Layout.setVerticalGroup(
            music2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title2)
                .addGap(18, 18, 18)
                .addComponent(artist2)
                .addGap(18, 18, 18))
            .addGroup(music2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top2)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsLike.add(music2, new java.awt.GridBagConstraints());

        ((RoundedPanel) music3).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music3).setHoverColor(new Color(32,32,32));
        music3.setBackground(new java.awt.Color(18, 18, 18));
        music3.setMaximumSize(new java.awt.Dimension(200, 300));
        music3.setMinimumSize(new java.awt.Dimension(200, 300));
        music3.setPreferredSize(new java.awt.Dimension(200, 300));

        top3.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top3.setForeground(new java.awt.Color(168, 168, 168));
        top3.setText("3.");

        title3.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title3.setForeground(new java.awt.Color(168, 168, 168));
        title3.setText("title");

        artist3.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist3.setForeground(new java.awt.Color(168, 168, 168));
        artist3.setText("artist");

        photo3.setBackground(new java.awt.Color(18, 18, 18));
        photo3.setOpaque(false);
        photo3.setLayout(new java.awt.GridBagLayout());
        photo3.add(jLabel3, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music3Layout = new javax.swing.GroupLayout(music3);
        music3.setLayout(music3Layout);
        music3Layout.setHorizontalGroup(
            music3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(music3Layout.createSequentialGroup()
                        .addGroup(music3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title3)
                            .addComponent(artist3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(photo3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(music3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top3)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        music3Layout.setVerticalGroup(
            music3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo3, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title3)
                .addGap(18, 18, 18)
                .addComponent(artist3)
                .addGap(18, 18, 18))
            .addGroup(music3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top3)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsLike.add(music3, new java.awt.GridBagConstraints());

        ((RoundedPanel) music4).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music4).setHoverColor(new Color(32,32,32));
        music4.setBackground(new java.awt.Color(18, 18, 18));
        music4.setMaximumSize(new java.awt.Dimension(200, 300));
        music4.setMinimumSize(new java.awt.Dimension(200, 300));
        music4.setPreferredSize(new java.awt.Dimension(200, 300));

        top4.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top4.setForeground(new java.awt.Color(168, 168, 168));
        top4.setText("4.");

        title4.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title4.setForeground(new java.awt.Color(168, 168, 168));
        title4.setText("title");

        artist4.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist4.setForeground(new java.awt.Color(168, 168, 168));
        artist4.setText("artist");

        photo4.setBackground(new java.awt.Color(18, 18, 18));
        photo4.setOpaque(false);
        photo4.setLayout(new java.awt.GridBagLayout());
        photo4.add(jLabel4, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music4Layout = new javax.swing.GroupLayout(music4);
        music4.setLayout(music4Layout);
        music4Layout.setHorizontalGroup(
            music4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(music4Layout.createSequentialGroup()
                        .addGroup(music4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title4)
                            .addComponent(artist4))
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addComponent(photo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(music4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top4)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        music4Layout.setVerticalGroup(
            music4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo4, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title4)
                .addGap(18, 18, 18)
                .addComponent(artist4)
                .addGap(18, 18, 18))
            .addGroup(music4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top4)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsLike.add(music4, new java.awt.GridBagConstraints());

        ((RoundedPanel) music5).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music5).setHoverColor(new Color(32,32,32));
        music5.setBackground(new java.awt.Color(18, 18, 18));
        music5.setMaximumSize(new java.awt.Dimension(200, 300));
        music5.setMinimumSize(new java.awt.Dimension(200, 300));
        music5.setPreferredSize(new java.awt.Dimension(200, 300));

        top5.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top5.setForeground(new java.awt.Color(168, 168, 168));
        top5.setText("5.");

        title5.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title5.setForeground(new java.awt.Color(168, 168, 168));
        title5.setText("title");

        artist5.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist5.setForeground(new java.awt.Color(168, 168, 168));
        artist5.setText("artist");

        photo5.setBackground(new java.awt.Color(18, 18, 18));
        photo5.setOpaque(false);
        photo5.setLayout(new java.awt.GridBagLayout());
        photo5.add(jLabel5, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music5Layout = new javax.swing.GroupLayout(music5);
        music5.setLayout(music5Layout);
        music5Layout.setHorizontalGroup(
            music5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(music5Layout.createSequentialGroup()
                        .addGroup(music5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title5)
                            .addComponent(artist5))
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addComponent(photo5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(music5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top5)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        music5Layout.setVerticalGroup(
            music5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo5, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title5)
                .addGap(18, 18, 18)
                .addComponent(artist5)
                .addGap(18, 18, 18))
            .addGroup(music5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top5)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsLike.add(music5, new java.awt.GridBagConstraints());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/fire.png"))); // NOI18N

        jLabel6.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        jLabel6.setForeground(new java.awt.Color(168, 168, 168));
        jLabel6.setText("Top 5 músicas mais curtidas");

        ((RoundedPanel) home_pnl_topMusicsDislike).setHover(0);
        home_pnl_topMusicsDislike.setBackground(new java.awt.Color(54, 54, 54));
        home_pnl_topMusicsDislike.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        home_pnl_topMusicsDislike.setMaximumSize(new java.awt.Dimension(847, 354));
        home_pnl_topMusicsDislike.setMinimumSize(new java.awt.Dimension(847, 354));
        home_pnl_topMusicsDislike.setPreferredSize(new java.awt.Dimension(847, 354));
        java.awt.GridBagLayout home_pnl_topMusicsDislikeLayout = new java.awt.GridBagLayout();
        home_pnl_topMusicsDislikeLayout.columnWidths = new int[] {20, 20, 20};
        home_pnl_topMusicsDislikeLayout.columnWeights = new double[] {5.0, 5.0, 5.0, 5.0, 5.0};
        home_pnl_topMusicsDislike.setLayout(home_pnl_topMusicsDislikeLayout);

        ((RoundedPanel) music6).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music6).setHoverColor(new Color(32,32,32));
        music6.setBackground(new java.awt.Color(18, 18, 18));
        music6.setMaximumSize(new java.awt.Dimension(200, 300));
        music6.setMinimumSize(new java.awt.Dimension(200, 300));

        top6.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top6.setForeground(new java.awt.Color(168, 168, 168));
        top6.setText("1.");

        title6.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title6.setForeground(new java.awt.Color(168, 168, 168));
        title6.setText("title");

        artist6.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist6.setForeground(new java.awt.Color(168, 168, 168));
        artist6.setText("artist");

        photo6.setBackground(new java.awt.Color(18, 18, 18));
        photo6.setOpaque(false);
        photo6.setLayout(new java.awt.GridBagLayout());
        photo6.add(jLabel7, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music6Layout = new javax.swing.GroupLayout(music6);
        music6.setLayout(music6Layout);
        music6Layout.setHorizontalGroup(
            music6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photo6, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addGroup(music6Layout.createSequentialGroup()
                        .addGroup(music6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title6)
                            .addComponent(artist6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(music6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top6)
                    .addContainerGap(185, Short.MAX_VALUE)))
        );
        music6Layout.setVerticalGroup(
            music6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo6, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title6)
                .addGap(18, 18, 18)
                .addComponent(artist6)
                .addGap(18, 18, 18))
            .addGroup(music6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top6)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsDislike.add(music6, new java.awt.GridBagConstraints());

        ((RoundedPanel) music7).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music7).setHoverColor(new Color(32,32,32));
        music7.setBackground(new java.awt.Color(18, 18, 18));
        music7.setMaximumSize(new java.awt.Dimension(200, 300));
        music7.setMinimumSize(new java.awt.Dimension(200, 300));

        top7.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top7.setForeground(new java.awt.Color(168, 168, 168));
        top7.setText("2.");

        title7.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title7.setForeground(new java.awt.Color(168, 168, 168));
        title7.setText("title");

        artist7.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist7.setForeground(new java.awt.Color(168, 168, 168));
        artist7.setText("artist");

        photo7.setBackground(new java.awt.Color(18, 18, 18));
        photo7.setOpaque(false);
        photo7.setLayout(new java.awt.GridBagLayout());
        photo7.add(jLabel8, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music7Layout = new javax.swing.GroupLayout(music7);
        music7.setLayout(music7Layout);
        music7Layout.setHorizontalGroup(
            music7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(music7Layout.createSequentialGroup()
                        .addGroup(music7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artist7)
                            .addComponent(title7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(photo7, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(music7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top7)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        music7Layout.setVerticalGroup(
            music7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo7, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title7)
                .addGap(18, 18, 18)
                .addComponent(artist7)
                .addGap(18, 18, 18))
            .addGroup(music7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top7)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsDislike.add(music7, new java.awt.GridBagConstraints());

        ((RoundedPanel) music8).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music8).setHoverColor(new Color(32,32,32));
        music8.setBackground(new java.awt.Color(18, 18, 18));
        music8.setMaximumSize(new java.awt.Dimension(200, 300));
        music8.setMinimumSize(new java.awt.Dimension(200, 300));

        top8.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top8.setForeground(new java.awt.Color(168, 168, 168));
        top8.setText("3.");

        title8.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title8.setForeground(new java.awt.Color(168, 168, 168));
        title8.setText("title");

        artist8.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist8.setForeground(new java.awt.Color(168, 168, 168));
        artist8.setText("artist");

        photo8.setBackground(new java.awt.Color(18, 18, 18));
        photo8.setOpaque(false);
        photo8.setLayout(new java.awt.GridBagLayout());
        photo8.add(jLabel9, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music8Layout = new javax.swing.GroupLayout(music8);
        music8.setLayout(music8Layout);
        music8Layout.setHorizontalGroup(
            music8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(music8Layout.createSequentialGroup()
                        .addGroup(music8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title8)
                            .addComponent(artist8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(photo8, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(music8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top8)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        music8Layout.setVerticalGroup(
            music8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo8, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title8)
                .addGap(18, 18, 18)
                .addComponent(artist8)
                .addGap(18, 18, 18))
            .addGroup(music8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top8)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsDislike.add(music8, new java.awt.GridBagConstraints());

        ((RoundedPanel) music9).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music9).setHoverColor(new Color(32,32,32));
        music9.setBackground(new java.awt.Color(18, 18, 18));
        music9.setMaximumSize(new java.awt.Dimension(200, 300));
        music9.setMinimumSize(new java.awt.Dimension(200, 300));

        top9.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top9.setForeground(new java.awt.Color(168, 168, 168));
        top9.setText("4.");

        title9.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title9.setForeground(new java.awt.Color(168, 168, 168));
        title9.setText("title");

        artist9.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist9.setForeground(new java.awt.Color(168, 168, 168));
        artist9.setText("artist");

        photo9.setBackground(new java.awt.Color(18, 18, 18));
        photo9.setOpaque(false);
        photo9.setLayout(new java.awt.GridBagLayout());
        photo9.add(jLabel10, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music9Layout = new javax.swing.GroupLayout(music9);
        music9.setLayout(music9Layout);
        music9Layout.setHorizontalGroup(
            music9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(music9Layout.createSequentialGroup()
                        .addGroup(music9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title9)
                            .addComponent(artist9))
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addComponent(photo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(music9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top9)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        music9Layout.setVerticalGroup(
            music9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo9, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title9)
                .addGap(18, 18, 18)
                .addComponent(artist9)
                .addGap(18, 18, 18))
            .addGroup(music9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top9)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsDislike.add(music9, new java.awt.GridBagConstraints());

        ((RoundedPanel) music10).setNormalColor(new Color(18,18,18));
        ((RoundedPanel) music10).setHoverColor(new Color(32,32,32));
        music10.setBackground(new java.awt.Color(18, 18, 18));
        music10.setMaximumSize(new java.awt.Dimension(200, 300));
        music10.setMinimumSize(new java.awt.Dimension(200, 300));

        top10.setFont(new Font("Gotham Black", Font.PLAIN, 18));
        top10.setForeground(new java.awt.Color(168, 168, 168));
        top10.setText("5.");

        title10.setFont(new Font("Gotham Black", Font.PLAIN, 14));
        title10.setForeground(new java.awt.Color(168, 168, 168));
        title10.setText("title");

        artist10.setFont(new Font("Gotham Black", Font.PLAIN, 12));
        artist10.setForeground(new java.awt.Color(168, 168, 168));
        artist10.setText("artist");

        photo10.setBackground(new java.awt.Color(18, 18, 18));
        photo10.setOpaque(false);
        photo10.setLayout(new java.awt.GridBagLayout());
        photo10.add(jLabel11, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout music10Layout = new javax.swing.GroupLayout(music10);
        music10.setLayout(music10Layout);
        music10Layout.setHorizontalGroup(
            music10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(music10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(music10Layout.createSequentialGroup()
                        .addGroup(music10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title10)
                            .addComponent(artist10))
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addComponent(photo10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(music10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top10)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        music10Layout.setVerticalGroup(
            music10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photo10, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title10)
                .addGap(18, 18, 18)
                .addComponent(artist10)
                .addGap(18, 18, 18))
            .addGroup(music10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(music10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(top10)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        home_pnl_topMusicsDislike.add(music10, new java.awt.GridBagConstraints());

        jLabel12.setFont(new Font("Gotham Black", Font.PLAIN, 22));
        jLabel12.setForeground(new java.awt.Color(168, 168, 168));
        jLabel12.setText("Top 5 músicas menos curtidas");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/images/sad-face.png"))); // NOI18N

        javax.swing.GroupLayout home_pnl_insideLayout = new javax.swing.GroupLayout(home_pnl_inside);
        home_pnl_inside.setLayout(home_pnl_insideLayout);
        home_pnl_insideLayout.setHorizontalGroup(
            home_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_insideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(home_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(home_pnl_insideLayout.createSequentialGroup()
                        .addComponent(home_pnl_welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_profile, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(home_pnl_topMusicsLike, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home_pnl_topMusicsDislike, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(home_pnl_insideLayout.createSequentialGroup()
                        .addGroup(home_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(home_pnl_insideLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(home_pnl_insideLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        home_pnl_insideLayout.setVerticalGroup(
            home_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_insideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(home_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(home_pnl_welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btt_profile, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(home_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(home_pnl_topMusicsLike, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(home_pnl_insideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(home_pnl_topMusicsDislike, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout home_pnl_allLayout = new javax.swing.GroupLayout(home_pnl_all);
        home_pnl_all.setLayout(home_pnl_allLayout);
        home_pnl_allLayout.setHorizontalGroup(
            home_pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home_pnl_botSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(home_pnl_allLayout.createSequentialGroup()
                .addComponent(home_pnl_leftSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(home_pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(home_pnl_inside, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home_pnl_topSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        home_pnl_allLayout.setVerticalGroup(
            home_pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_pnl_allLayout.createSequentialGroup()
                .addGroup(home_pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(home_pnl_allLayout.createSequentialGroup()
                        .addComponent(home_pnl_topSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(home_pnl_inside, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(home_pnl_leftSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(home_pnl_botSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btt_profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_profileActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btt_profileActionPerformed

    private void home_pnl_homeOptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pnl_homeOptMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_home_pnl_homeOptMouseClicked

    private void home_pnl_configsOptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pnl_configsOptMouseClicked
        // TODO add your handling code here:
        AdmSettingsWindow asw = new AdmSettingsWindow(this.adm);
        asw.setVisible(rootPaneCheckingEnabled);
        this.setVisible(false);
    }//GEN-LAST:event_home_pnl_configsOptMouseClicked


    private AdmHomeController c;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel artist1;
    private javax.swing.JLabel artist10;
    private javax.swing.JLabel artist2;
    private javax.swing.JLabel artist3;
    private javax.swing.JLabel artist4;
    private javax.swing.JLabel artist5;
    private javax.swing.JLabel artist6;
    private javax.swing.JLabel artist7;
    private javax.swing.JLabel artist8;
    private javax.swing.JLabel artist9;
    private javax.swing.JButton btt_profile;
    private javax.swing.JPanel home_pnl_all;
    private javax.swing.JPanel home_pnl_botSide;
    private javax.swing.JPanel home_pnl_configsOpt;
    private javax.swing.JPanel home_pnl_homeOpt;
    private javax.swing.JPanel home_pnl_inside;
    private javax.swing.JPanel home_pnl_leftSide;
    private javax.swing.JPanel home_pnl_options;
    private javax.swing.JPanel home_pnl_titleLogo;
    private javax.swing.JPanel home_pnl_topMusicsDislike;
    private javax.swing.JPanel home_pnl_topMusicsLike;
    private javax.swing.JPanel home_pnl_topSide;
    private javax.swing.JPanel home_pnl_welcome;
    private javax.swing.JLabel icon_config;
    private javax.swing.JLabel icon_home;
    private javax.swing.JLabel icon_logo;
    private javax.swing.JLabel icon_wave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbl_config;
    private javax.swing.JLabel lbl_home;
    private javax.swing.JLabel lbl_musics;
    private javax.swing.JLabel lbl_numberMusics;
    private javax.swing.JLabel lbl_numberUsers;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JLabel lbl_users;
    private javax.swing.JLabel lbl_welcome;
    private javax.swing.JPanel music1;
    private javax.swing.JPanel music10;
    private javax.swing.JPanel music2;
    private javax.swing.JPanel music3;
    private javax.swing.JPanel music4;
    private javax.swing.JPanel music5;
    private javax.swing.JPanel music6;
    private javax.swing.JPanel music7;
    private javax.swing.JPanel music8;
    private javax.swing.JPanel music9;
    private javax.swing.JPanel photo1;
    private javax.swing.JPanel photo10;
    private javax.swing.JPanel photo2;
    private javax.swing.JPanel photo3;
    private javax.swing.JPanel photo4;
    private javax.swing.JPanel photo5;
    private javax.swing.JPanel photo6;
    private javax.swing.JPanel photo7;
    private javax.swing.JPanel photo8;
    private javax.swing.JPanel photo9;
    private javax.swing.JPanel pnl_numbers;
    private javax.swing.JPanel spacing1;
    private javax.swing.JPanel spacing2;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title10;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title5;
    private javax.swing.JLabel title6;
    private javax.swing.JLabel title7;
    private javax.swing.JLabel title8;
    private javax.swing.JLabel title9;
    private javax.swing.JLabel top1;
    private javax.swing.JLabel top10;
    private javax.swing.JLabel top2;
    private javax.swing.JLabel top3;
    private javax.swing.JLabel top4;
    private javax.swing.JLabel top5;
    private javax.swing.JLabel top6;
    private javax.swing.JLabel top7;
    private javax.swing.JLabel top8;
    private javax.swing.JLabel top9;
    // End of variables declaration//GEN-END:variables
}
