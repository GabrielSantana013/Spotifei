/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.customClasses;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Extensão de {@link JFormattedTextField} que adiciona suporte a texto placeholder
 * com cores customizáveis e comportamento para mudar o texto e a cor ao focar e desfocar o campo.
 * 
 * <p>Possui suporte a padding para o placeholder e troca de cores para estado normal,
 * hover (focado) e texto de entrada.</p>
 * 
 * <p>Autores: Pedro Schneider, Gabriel Santana Dias</p>
 */
public class PlaceholderFields extends JFormattedTextField {

    private String placeholderText;
    private Color placeholderColor = new Color(168,168,168);
    private Color backgroundColor = new Color(51,51,51);
    private Color hoverBackgroundColor = new Color(64,64,64);
    private Color inputColor= new Color(236, 239, 241);
    private Insets padding; // padding para o texto placeholder

    /**
     * Construtor para criar um campo com placeholder e padding personalizados.
     * O campo inicia com o texto do placeholder e cor configurada.
     * 
     * @param placeholderText texto a ser exibido como placeholder quando o campo estiver vazio
     * @param padding espaço interno (margem) ao redor do texto
     */
    public PlaceholderFields(String placeholderText, Insets padding) {
        super();
        this.placeholderText = placeholderText;
        this.padding = padding;

        setText(placeholderText);
        setForeground(placeholderColor);

        setBorder(BorderFactory.createEmptyBorder());

        initialize();
    }

    /**
     * Construtor para criar um campo formatado com placeholder e padding personalizados.
     * 
     * @param formatter formatter para o campo formatado
     * @param placeholderText texto placeholder exibido quando o campo estiver vazio
     * @param padding espaço interno (margem) ao redor do texto
     */
    public PlaceholderFields(AbstractFormatter formatter, String placeholderText, Insets padding) {
        super(formatter);
        this.placeholderText = placeholderText;
        this.padding = padding;

        setText(placeholderText);
        setForeground(placeholderColor);

        setBorder(BorderFactory.createEmptyBorder());

        initialize();
    }

    /**
     * Define a cor do texto do placeholder.
     * 
     * @param placeholderColor cor do placeholder
     */
    public void setPlaceholderColor(Color placeholderColor) {
        this.placeholderColor = placeholderColor;
    }

    /**
     * Define a cor de fundo normal do campo.
     * 
     * @param backgroundColor cor de fundo normal
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Define a cor de fundo quando o campo está focado (hover).
     * 
     * @param hoverBackgroundColor cor de fundo no foco
     */
    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    /**
     * Define a cor do texto de entrada (quando o usuário digita).
     * 
     * @param inputColor cor do texto digitado
     */
    public void setInputColor(Color inputColor) {
        this.inputColor = inputColor;
    }

    /**
     * Verifica se o campo está efetivamente vazio, considerando placeholders
     * e caracteres de máscara, caso seja um campo formatado.
     * 
     * @return {@code true} se o campo estiver vazio ou conter apenas o placeholder; {@code false} caso contrário
     */
    private boolean isEffectivelyEmpty() {
        String text = getText();

        // para campos formatados com MaskFormatter
        if (getFormatter() instanceof javax.swing.text.MaskFormatter mf) {
            char placeholderChar = mf.getPlaceholderCharacter();

            // verifica se texto possui dígitos ou letras reais
            for (char c : text.toCharArray()) {
                if (Character.isDigit(c)) return false;
                if (Character.isLetter(c)) return false;
                if (c != placeholderChar && c != ' ' && c != '/') return false;
            }
            return true;
        }

        // para campos normais, verifica se é vazio após trim
        return text.trim().isEmpty();
    }

    /**
     * Inicializa os listeners para comportamento de foco, troca de cor
     * e texto placeholder.
     */
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

    /**
     * Sobrescreve a pintura do componente para aplicar padding
     * e desenhar o fundo e o texto com anti-aliasing.
     * 
     * @param g contexto gráfico para pintura
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // pinta fundo
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        // ativa anti-aliasing para o texto
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // aplica translação para o padding
        g2.translate(padding.left, padding.top);

        // limita a área de desenho ao tamanho descontando o padding
        Shape oldClip = g2.getClip();
        g2.setClip(0, 0, getWidth() - padding.left - padding.right,
                getHeight() - padding.top - padding.bottom);

        // chama paintComponent original para desenhar texto e cursor
        g2.setColor(getForeground());
        super.paintComponent(g2);

        g2.setClip(oldClip);
        g2.dispose();
    }
}
