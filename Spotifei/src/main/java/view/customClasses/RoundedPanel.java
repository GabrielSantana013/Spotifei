package view.customClasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe {@code RoundedPanel} representa um painel JPanel com cantos arredondados e
 * efeito de hover que altera suavemente a cor de fundo quando o mouse está sobre o painel.
 * <p>
 * A cor padrão do fundo é {@link #normalColor} e, quando o mouse entra no painel,
 * a cor transita para {@link #hoverColor}. Quando o mouse sai, a cor volta para a normal.
 * O raio dos cantos arredondados pode ser configurado individualmente para horizontal e vertical.
 * </p>
 * <p>
 * A animação do hover é feita através de um {@link Timer} que faz uma interpolação suave entre as cores.
 * </p>
 * 
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class RoundedPanel extends JPanel {
    
    private Color normalColor = new Color(185, 192, 192);    
    private Color hoverColor = new Color(142, 150, 150);   
    private Color currentBackground = normalColor;    
    private Timer hoverTimer;    
    private boolean hovering = false;  
    private int cornerRadiusHorizontal = 30;    
    private int cornerRadiusVertical = 30;    
    private int hover = 1;

    /**
     * Construtor padrão que inicializa o painel com fundo transparente,
     * configura as cores e adiciona os listeners para detectar hover do mouse.
     */
    public RoundedPanel() {
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
     * Define a cor padrão do fundo do painel.
     * @param normalColor a nova cor normal de fundo
     */
    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }

    /**
     * Define a cor do fundo quando o mouse estiver em hover.
     * @param hoverColor a nova cor de hover
     */
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    /**
     * Retorna o raio dos cantos arredondados na horizontal.
     * @return raio horizontal dos cantos
     */
    public int getCornerRadiusHorizontal() {
        return cornerRadiusHorizontal;
    }

    /**
     * Define o raio dos cantos arredondados na horizontal.
     * @param cornerRadiusHorizontal novo raio horizontal dos cantos
     */
    public void setCornerRadiusHorizontal(int cornerRadiusHorizontal) {
        this.cornerRadiusHorizontal = cornerRadiusHorizontal;
    }

    /**
     * Retorna o raio dos cantos arredondados na vertical.
     * @return raio vertical dos cantos
     */
    public int getCornerRadiusVertical() {
        return cornerRadiusVertical;
    }

    /**
     * Define o raio dos cantos arredondados na vertical.
     * @param cornerRadiusVertical novo raio vertical dos cantos
     */
    public void setCornerRadiusVertical(int cornerRadiusVertical) {
        this.cornerRadiusVertical = cornerRadiusVertical;
    }

    /**
     * Habilita ou desabilita a animação de hover.
     * @param setHover 1 para habilitar animação, 0 para desabilitar
     */
    public void setHover(int setHover) {
        this.hover = setHover;
    }

    /**
     * Método sobrescrito para pintar o componente com cantos arredondados.
     * A cor de fundo é atualizada conforme o estado de hover.
     * 
     * @param g o contexto gráfico para pintura
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // respeita opacidade e outras propriedades

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // pinta o fundo com cantos arredondados
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                cornerRadiusHorizontal, cornerRadiusVertical);

        g2.dispose();
    }

    /**
     * Realiza a interpolação linear entre duas cores baseado na razão dada.
     * @param c1 primeira cor
     * @param c2 segunda cor
     * @param ratio valor entre 0.0 e 1.0 que determina o peso de cada cor
     * @return a cor resultante da interpolação
     */
    private Color blendColors(Color c1, Color c2, float ratio) {
        float ir = 1.0f - ratio;
        int r = (int) (c1.getRed() * ir + c2.getRed() * ratio);
        int g = (int) (c1.getGreen() * ir + c2.getGreen() * ratio);
        int b = (int) (c1.getBlue() * ir + c2.getBlue() * ratio);
        return new Color(r, g, b);
    }

    /**
     * Inicia a animação que faz a transição suave entre a cor normal e a cor de hover.
     * O {@link Timer} atualiza a cor de fundo periodicamente até que a animação seja concluída.
     */
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
