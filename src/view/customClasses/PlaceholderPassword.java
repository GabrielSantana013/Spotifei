/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.customClasses;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderPassword extends JPasswordField {

    private String placeholderText;
    private Color placeholderColor = new Color(168, 168, 168);
    private Color backgroundColor = new Color(51, 51, 51);
    private Color hoverBackgroundColor = new Color(64, 64, 64);
    private Color inputColor = new Color(236, 239, 241);
    private Insets padding;

    private boolean showingPlaceholder;

    public PlaceholderPassword(String placeholderText, Insets padding) {
        super();
        this.placeholderText = placeholderText;
        this.padding = padding;

        setEchoChar((char) 0);
        setText(placeholderText);
        setForeground(placeholderColor);
        showingPlaceholder = true;

        setBorder(BorderFactory.createEmptyBorder());
        initialize();
    }

    public void setPlaceholderColor(Color placeholderColor) {
        this.placeholderColor = placeholderColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public void setInputColor(Color inputColor) {
        this.inputColor = inputColor;
    }

    private boolean isEffectivelyEmpty() {
        char[] password = getPassword();
        return password.length == 0 || (showingPlaceholder && new String(password).equals(placeholderText));
    }

    private void initialize() {
        setBackground(backgroundColor);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(hoverBackgroundColor);
                if (showingPlaceholder) {
                    setText("");
                    setEchoChar('•'); // typical echo character
                    setForeground(inputColor);
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(backgroundColor);
                if (isEffectivelyEmpty()) {
                    setEchoChar((char) 0);
                    setText(placeholderText);
                    setForeground(placeholderColor);
                    showingPlaceholder = true;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // set background
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        // anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // translate for padding
        g2.translate(padding.left, padding.top);

        // clip within padding bounds
        Shape oldClip = g2.getClip();
        g2.setClip(0, 0, getWidth() - padding.left - padding.right,
                getHeight() - padding.top - padding.bottom);

        // draw text
        g2.setColor(getForeground());
        super.paintComponent(g2);

        g2.setClip(oldClip);
        g2.dispose();
    }

    @Override
    public char[] getPassword() {
        if (showingPlaceholder) {
            return new char[0];
        }
        return super.getPassword();
    }
}