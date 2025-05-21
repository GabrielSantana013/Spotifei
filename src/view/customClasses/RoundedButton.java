package view.customClasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe que representa um botão personalizado com cantos arredondados,
 * cores personalizáveis para estado normal e hover, e alinhamento de texto configurável.
 * Inclui uma animação suave de transição de cor ao passar o mouse sobre o botão.
 * 
 * <p>O botão pode ter alinhamento do texto configurado como {@link TextAlign#LEFT},
 * {@link TextAlign#CENTER} ou {@link TextAlign#RIGHT}.</p>
 * 
 * <p>A animação de hover utiliza um timer para interpolar suavemente
 * entre as cores normal e hover, criando um efeito visual agradável.</p>
 * 
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class RoundedButton extends JButton {

    /** Cor de fundo padrão do botão (estado normal). */
    private Color normalColor = new Color(185, 192, 192);

    /** Cor de fundo do botão quando o mouse está sobre ele (hover). */
    private Color hoverColor = new Color(142, 150, 150);

    /** Cor de fundo atual usada na pintura do botão, varia com a animação. */
    private Color currentBackground = normalColor;

    /** Timer que controla a animação de transição entre cores no hover. */
    private Timer hoverTimer;

    /** Indica se o mouse está atualmente sobre o botão. */
    private boolean hovering = false;

    /** Raio horizontal para o arredondamento dos cantos do botão. */
    private int cornerRadiusHorizontal = 30;

    /** Raio vertical para o arredondamento dos cantos do botão. */
    private int cornerRadiusVertical = 30;

    /** Flag para habilitar/desabilitar a animação de hover (1 para ativar). */
    private int hover = 1;

    /**
     * Enumeração que define as opções de alinhamento do texto no botão.
     */
    public enum TextAlign {
        LEFT, CENTER, RIGHT
    }

    /** Alinhamento atual do texto no botão. */
    private TextAlign textAlign = TextAlign.CENTER;

    /**
     * Construtor que cria um botão com texto e configurações padrão,
     * incluindo cantos arredondados e animação de hover.
     * 
     * @param text texto exibido no botão
     */
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setBackground(normalColor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
                if (hover == 1)
                    startHoverAnimation();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                if (hover == 1)
                    startHoverAnimation();
            }
        });
    }

    /**
     * Retorna o raio horizontal do arredondamento dos cantos do botão.
     * 
     * @return raio horizontal dos cantos
     */
    public int getCornerRadiusHorizontal() {
        return cornerRadiusHorizontal;
    }

    /**
     * Define o raio horizontal do arredondamento dos cantos do botão.
     * 
     * @param cornerRadiusHorizontal novo raio horizontal dos cantos
     */
    public void setCornerRadiusHorizontal(int cornerRadiusHorizontal) {
        this.cornerRadiusHorizontal = cornerRadiusHorizontal;
    }

    /**
     * Retorna o raio vertical do arredondamento dos cantos do botão.
     * 
     * @return raio vertical dos cantos
     */
    public int getCornerRadiusVertical() {
        return cornerRadiusVertical;
    }

    /**
     * Define o raio vertical do arredondamento dos cantos do botão.
     * 
     * @param cornerRadiusVertical novo raio vertical dos cantos
     */
    public void setCornerRadiusVertical(int cornerRadiusVertical) {
        this.cornerRadiusVertical = cornerRadiusVertical;
    }

    /**
     * Define se a animação de hover está ativada (1 para ativar, 0 para desativar).
     * 
     * @param hover valor para ativar ou desativar a animação de hover
     */
    public void setHover(int hover) {
        this.hover = hover;
    }

    /**
     * Define a cor de fundo do botão no estado normal.
     * 
     * @param normalColor cor de fundo normal
     */
    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }

    /**
     * Define a cor de fundo do botão quando o mouse está sobre ele (hover).
     * 
     * @param hoverColor cor de fundo no estado hover
     */
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    /**
     * Define o alinhamento do texto dentro do botão.
     * 
     * @param align alinhamento do texto (LEFT, CENTER ou RIGHT)
     */
    public void setTextAlignment(TextAlign align) {
        this.textAlign = align;
        switch (align) {
            case LEFT -> setHorizontalAlignment(SwingConstants.LEFT);
            case CENTER -> setHorizontalAlignment(SwingConstants.CENTER);
            case RIGHT -> setHorizontalAlignment(SwingConstants.RIGHT);
        }
    }

    /**
     * Pinta o componente do botão, incluindo fundo arredondado e texto alinhado.
     * 
     * @param g contexto gráfico para pintura
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // desenha o fundo arredondado com a cor atual
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                cornerRadiusHorizontal, cornerRadiusVertical);

        // desenha o texto com alinhamento configurado
        String text = getText();
        FontMetrics fm = g2.getFontMetrics(getFont());
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int x;
        int y = (getHeight() + textHeight) / 2 - 2; // centraliza verticalmente

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

    /**
     * Pinta a borda arredondada do botão.
     * 
     * @param g contexto gráfico para pintura
     */
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

    /**
     * Faz interpolação (blend) entre duas cores baseado em uma razão (ratio).
     * 
     * @param c1 cor inicial
     * @param c2 cor final
     * @param ratio valor entre 0.0 e 1.0 que determina o peso da cor final
     * @return cor interpolada resultante
     */
    private Color blendColors(Color c1, Color c2, float ratio) {
        float ir = 1.0f - ratio;
        int r = (int) (c1.getRed() * ir + c2.getRed() * ratio);
        int g = (int) (c1.getGreen() * ir + c2.getGreen() * ratio);
        int b = (int) (c1.getBlue() * ir + c2.getBlue() * ratio);
        return new Color(r, g, b);
    }

    /**
     * Inicia a animação de transição de cor quando o mouse entra ou sai do botão.
     * A cor do fundo vai mudando suavemente do normal para a cor de hover ou vice-versa.
     */
    private void startHoverAnimation() {
        if (hoverTimer != null && hoverTimer.isRunning()) {
            hoverTimer.stop();
        }

        hoverTimer = new Timer(15, new ActionListener() {
            float progress = 0; // progresso da animação, de 0 a 1

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
