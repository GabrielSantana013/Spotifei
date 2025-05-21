package view.customDialogs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Classe utilitária para exibir um diálogo personalizado simples com título, mensagem e botão OK.
 * <p>
 * O diálogo é modal, centralizado na tela, com tamanho fixo e um estilo customizado
 * de cores e fontes, incluindo um ícone personalizado.
 * </p>
 * 
 * <p>
 * Exemplo de uso:
 * <pre>{@code
 * CustomJDialog.showCustomDialog("Aviso", "Operação realizada com sucesso!");
 * }</pre>
 * </p>
 * 
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class CustomJDialog {

    /**
     * Exibe um diálogo modal simples com o título e mensagem fornecidos.
     * O diálogo possui fundo escuro, texto branco centralizado e um botão OK para fechá-lo.
     * 
     * @param title título a ser exibido na barra do diálogo
     * @param message mensagem a ser exibida no corpo do diálogo
     */
    public static void showCustomDialog(String title, String message) {
        // cria o diálogo
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(null);  // centraliza na tela
        dialog.setResizable(false);
        
        // define o ícone do diálogo (imagem do recurso)
        dialog.setIconImage(new javax.swing.ImageIcon(CustomJDialog.class.getResource("/view/assets/images/logoSpotifei.png")).getImage());

        // painel principal com fundo escuro e layout BorderLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(28, 28, 28));
        panel.setLayout(new BorderLayout());

        // label para mensagem centralizada, com fonte Arial negrito 16 e texto branco
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(new Color(255, 255, 255));
        panel.add(messageLabel, BorderLayout.CENTER);

        // botão OK estilizado para fechar o diálogo
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 14));
        okButton.setBackground(new Color(110, 110, 110));
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(e -> dialog.dispose());
        panel.add(okButton, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
