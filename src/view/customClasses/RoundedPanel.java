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

public class RoundedPanel extends JPanel {
    private Color normalColor = new Color(185, 192, 192); // cor padrão
    private Color hoverColor = new Color(142, 150, 150);  // cor de hover
    private Color currentBackground = normalColor;
    private Timer hoverTimer;
    private boolean hovering = false;
    private int cornerRadiusHorizontal = 30;
    private int cornerRadiusVertical = 30;
    private int hover = 1;

    public RoundedPanel() {
        setOpaque(false);
        setBackground(normalColor);

        // listener para hover
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

    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
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

    public void setHover(int setHover) {
        this.hover = setHover;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // importante para respeitar a opacidade

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // fundo arredondado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                cornerRadiusHorizontal, cornerRadiusVertical);

        g2.dispose();
    }

    // TODO: review this, don't think it's necessary
//    @Override
//    protected void paintBorder(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//
//        g2.setColor(getBackground().darker());
//        g2.setStroke(new BasicStroke(1));
//        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1,
//                cornerRadiusHorizontal, cornerRadiusVertical);
//
//        g2.dispose();
//    }

    private Color blendColors(Color c1, Color c2, float ratio) {
        float ir = 1.0f - ratio;
        int r = (int) (c1.getRed() * ir + c2.getRed() * ratio);
        int g = (int) (c1.getGreen() * ir + c2.getGreen() * ratio);
        int b = (int) (c1.getBlue() * ir + c2.getBlue() * ratio);
        return new Color(r, g, b);
    }

    private void startHoverAnimation() {
        if (hoverTimer != null && hoverTimer.isRunning()) {
            hoverTimer.stop();
        }

        hoverTimer = new Timer(15, new ActionListener() {
            float progress = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 0.15f;
                if (progress > 1f) progress = 1f;

                currentBackground = blendColors(
                        hovering ? normalColor : hoverColor,
                        hovering ? hoverColor : normalColor,
                        progress
                );
                setBackground(currentBackground);
                repaint();

                if (progress >= 1f) {
                    hoverTimer.stop();
                }
            }
        });

        hoverTimer.start();
    }
}
