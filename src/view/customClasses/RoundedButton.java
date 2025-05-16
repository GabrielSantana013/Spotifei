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
public class RoundedButton extends JButton {
    private Color normalColor = new Color(185, 192, 192); // default background
    private Color hoverColor = new Color(142, 150, 150);  // hover color
    private Color currentBackground = normalColor;
    private Timer hoverTimer;
    private boolean hovering = false;
    private int cornerRadiusHorizontal = 30;
    private int cornerRadiusVertical = 30;
    private int hover = 1;

    
    // enum for alignment
    public enum TextAlign {
        LEFT, CENTER, RIGHT
    }

    private TextAlign textAlign = TextAlign.CENTER;
    
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setBackground(normalColor);
        
        // listener for mouse
        addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            hovering = true;
            if(hover == 1)
                startHoverAnimation();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            hovering = false;
            if(hover == 1)
                startHoverAnimation();
        }
    });
    }

    public int getCornerRadiusHorizontal() {
        return cornerRadiusHorizontal;
    }

    public void setCornerRadiusHorizontal(int cornerRadiusHorizontal) {
        this.cornerRadiusHorizontal = cornerRadiusHorizontal;
    }

    public int getCornerRadiusVertical() {
        return cornerRadiusVertical;
    }

    public void setCornerRadiusVertical(int cornerRadiusVertical) {
        this.cornerRadiusVertical = cornerRadiusVertical;
    }

    public void setHover(int hover) {
        this.hover = hover;
    }

    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }
    
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }
    
    // set the text alignment
    public void setTextAlignment(TextAlign align) {
        this.textAlign = align;
        switch (align) {
            case LEFT -> {
                setHorizontalAlignment(SwingConstants.LEFT);
//                setMargin(new Insets(0, 15, 0, 5));
            }
            case CENTER -> {
                setHorizontalAlignment(SwingConstants.CENTER);
//                setMargin(new Insets(0, 0, 0, 0));
            }
            case RIGHT -> {
                setHorizontalAlignment(SwingConstants.RIGHT);
//                setMargin(new Insets(0, 5, 0, 30));
            }
        }
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // draw background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                cornerRadiusHorizontal, cornerRadiusVertical);

        // draw text manually
        String text = getText();
        FontMetrics fm = g2.getFontMetrics(getFont());
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int x;
        int y = (getHeight() + textHeight) / 2 - 2; // vertical centering

        switch (textAlign) {
            case LEFT -> x = 15;
            case CENTER -> x = (getWidth() - textWidth) / 2;
            case RIGHT -> x = getWidth() - textWidth - 15;
            default -> x = (getWidth() - textWidth) / 2;
        }

        g2.setFont(getFont());
        g2.setColor(getForeground());
        g2.drawString(text, x, y);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1,
                cornerRadiusHorizontal, cornerRadiusVertical);

        g2.dispose();
    }
    
    // interpolation between the two colors
    private Color blendColors(Color c1, Color c2, float ratio) {
        // ir shows how much of the first color we want; vice-versa
        float ir = 1.0f - ratio; // inverse ratio
        int r = (int) (c1.getRed() * ir + c2.getRed() * ratio);
        int g = (int) (c1.getGreen() * ir + c2.getGreen() * ratio);
        int b = (int) (c1.getBlue() * ir + c2.getBlue() * ratio);
        return new Color(r, g, b);
    }

    // animation using timer
    private void startHoverAnimation() {
        // checks if mouse still on hovering
        if (hoverTimer != null && hoverTimer.isRunning()) {
            hoverTimer.stop();
        }

        // sets a new listener every 15ms (around 60 FPS)
        hoverTimer = new Timer(15, new ActionListener() {
            float progress = 0; // represents the progress of animation, 0 -> 1

            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 0.15f;
                if (progress > 1f) progress = 1f;

                // checks if mouse is on or off
                currentBackground = blendColors(
                    hovering ? normalColor : hoverColor,
                    hovering ? hoverColor : normalColor,
                    progress
                );
                // the color is updated on every timer tick so it slowly fades over time.
                setBackground(currentBackground);
                repaint();

                // stop timer when progress reach 1 (100%)
                if (progress >= 1f) {
                    hoverTimer.stop();
                }
            }
        });

        hoverTimer.start();
    }

}
