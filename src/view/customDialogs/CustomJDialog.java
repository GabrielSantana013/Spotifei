/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.customDialogs;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CustomJDialog {

    public static void showCustomDialog(String title, String message) {
        // create a custom dialog (JDialog)
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(null);  // center the dialog
        dialog.setResizable(false);
        dialog.setIconImage(new javax.swing.ImageIcon(CustomJDialog.class.getResource("/view/assets/images/logoSpotifei.png")).getImage());

        
        // create a JPanel for the dialog content
        JPanel panel = new JPanel();
        panel.setBackground(new Color(28, 28, 28));  // dark background
        panel.setLayout(new BorderLayout());
        
        // create a JLabel for the message
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));  // custom font
        messageLabel.setForeground(new Color(255, 255, 255));  // white text
        panel.add(messageLabel, BorderLayout.CENTER);
        
        // add an OK button to close the dialog
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 14));
        okButton.setBackground(new Color(110, 110, 110));  // custom button color
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(e -> dialog.dispose());
        panel.add(okButton, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
}
