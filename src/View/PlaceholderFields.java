/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class PlaceholderFields extends JTextField {

    private String placeholderText;
    private final Color placeholderColor = new Color(167,167,167);
    private final Color inputColor= new Color(168,170,170);
    private Insets padding; // Padding for the placeholder text

    // Constructor for JTextField or JTextArea with custom colors and padding
    public PlaceholderFields(String placeholderText, Insets padding) {
        super(); // Call the constructor of JTextComponent (parent class)
        this.placeholderText = placeholderText;
        this.padding = padding;

        setText(placeholderText);
        setForeground(placeholderColor);
        
        // Set padding
        setMargin(padding);
        
        initialize();
    }

    private void initialize() {
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholderText)) {
                    setText("");
                    setForeground(inputColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholderText);
                    setForeground(placeholderColor);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().equals(placeholderText) && getForeground().equals(placeholderColor)) {
            // Apply custom padding for the placeholder text
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(placeholderColor);

            // Apply the padding by adjusting the x and y positions
            g2d.drawString(placeholderText, padding.left, padding.top + g.getFontMetrics().getAscent());
        }
    }
}