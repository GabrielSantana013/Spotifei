/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.customClasses;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class PlaceholderFields extends JFormattedTextField {

    private String placeholderText;
    private Color placeholderColor = new Color(168,168,168);
    private Color backgroundColor = new Color(51,51,51);
    private Color hoverBackgroundColor = new Color(64,64,64);
    private Color inputColor= new Color(236, 239, 241);
    private Insets padding; // padding for the placeholder text

    // constructor for JTextField or JTextArea with custom colors and padding
    public PlaceholderFields(String placeholderText, Insets padding) {
        super(); // call the constructor of JTextComponent (parent class)
        this.placeholderText = placeholderText;
        this.padding = padding;
        
        setText(placeholderText);
        setForeground(placeholderColor);
        
        setBorder(BorderFactory.createEmptyBorder());
        
        initialize();
    }
    
    // constructor for formatted text field
    public PlaceholderFields(AbstractFormatter formatter, String placeholderText, Insets padding) {
        super(formatter);
        this.placeholderText = placeholderText;
        this.padding = padding;
        
        setText(placeholderText);
        setForeground(placeholderColor);
        
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
    String text = getText();

    // for formatted fields: check if it only contains the placeholder character or separators
    if (getFormatter() instanceof javax.swing.text.MaskFormatter mf) {
        char placeholderChar = mf.getPlaceholderCharacter();

        // strip out all non-placeholder characters (e.g., '/', spaces)
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) return false;
            if (Character.isLetter(c)) return false;
            if (c != placeholderChar && c != ' ' && c != '/') return false;
        }
        return true;
    }

    // for normal fields: just trim and check
    return text.trim().isEmpty();
}
    
    private void initialize() {
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(hoverBackgroundColor);
                if (getText().equals(placeholderText)) {
                    setText("");
                    setForeground(inputColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(backgroundColor);
                if (isEffectivelyEmpty()) {
                    setText(placeholderText);
                    setForeground(placeholderColor);
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

        // set anti-aliasing (improves text quality)
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // translate for padding
        g2.translate(padding.left, padding.top);

        // create a temporary graphics context with clipped padding area
        Shape oldClip = g2.getClip();
        g2.setClip(0, 0, getWidth() - padding.left - padding.right,
                getHeight() - padding.top - padding.bottom);

        // let the UI delegate draw the text within this clipped/padded area
        g2.setColor(getForeground());
        super.paintComponent(g2);

        g2.setClip(oldClip);
        g2.dispose();
    }
}