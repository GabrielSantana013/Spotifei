package view.customClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Extensão de {@link JPasswordField} que adiciona suporte a texto placeholder,
 * com cores personalizadas e padding configurável.
 * 
 * <p>Quando o campo está vazio, exibe o texto placeholder visível, sem mascarar.
 * Ao focar, limpa o placeholder e ativa o eco de caracteres para ocultar a senha.</p>
 * 
 * <p>Autores: Pedro Schneider, Gabriel Santana Dias</p>
 */
public class PlaceholderPassword extends JPasswordField {

    private String placeholderText;
    private Color placeholderColor = new Color(168, 168, 168);
    private Color backgroundColor = new Color(51, 51, 51);
    private Color hoverBackgroundColor = new Color(64, 64, 64);
    private Color inputColor = new Color(236, 239, 241);
    private Insets padding;

    private boolean showingPlaceholder;

    /**
     * Construtor que cria o campo de senha com texto placeholder e padding.
     * O placeholder é exibido inicialmente e o campo não mascara os caracteres.
     * 
     * @param placeholderText texto a ser exibido como placeholder quando o campo estiver vazio
     * @param padding espaçamento interno (margem) ao redor do texto
     */
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

    /**
     * Define a cor do texto do placeholder.
     * 
     * @param placeholderColor cor do texto placeholder
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
     * Define a cor de fundo quando o campo está focado.
     * 
     * @param hoverBackgroundColor cor de fundo no foco
     */
    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    /**
     * Define a cor do texto digitado.
     * 
     * @param inputColor cor do texto digitado
     */
    public void setInputColor(Color inputColor) {
        this.inputColor = inputColor;
    }

    /**
     * Verifica se o campo está efetivamente vazio, ou seja, se o placeholder está
     * sendo mostrado ou se o usuário não digitou nada.
     * 
     * @return {@code true} se o campo estiver vazio ou mostrando placeholder, {@code false} caso contrário
     */
    private boolean isEffectivelyEmpty() {
        char[] password = getPassword();
        return password.length == 0 || (showingPlaceholder && new String(password).equals(placeholderText));
    }

    /**
     * Inicializa listeners para controle de foco e comportamento do placeholder,
     * além da troca das cores de fundo e texto.
     */
    private void initialize() {
        setBackground(backgroundColor);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(hoverBackgroundColor);
                if (showingPlaceholder) {
                    setText("");
                    setEchoChar('•'); // caractere típico para ocultar senha
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

    /**
     * Sobrescreve a pintura do componente para aplicar padding e
     * melhorar qualidade do texto com anti-aliasing.
     * 
     * @param g contexto gráfico para pintura
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // pinta o fundo
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        // ativa anti-aliasing para o texto
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // aplica a translação para considerar o padding
        g2.translate(padding.left, padding.top);

        // limita a área de desenho ao tamanho descontando o padding
        Shape oldClip = g2.getClip();
        g2.setClip(0, 0, getWidth() - padding.left - padding.right,
                getHeight() - padding.top - padding.bottom);

        // desenha o componente original (texto, cursor)
        g2.setColor(getForeground());
        super.paintComponent(g2);

        g2.setClip(oldClip);
        g2.dispose();
    }

    /**
     * Retorna o conteúdo da senha, mas retorna array vazio caso o placeholder
     * esteja sendo exibido, para evitar confundir placeholder com senha digitada.
     * 
     * @return array de caracteres da senha digitada, ou vazio se estiver mostrando placeholder
     */
    @Override
    public char[] getPassword() {
        if (showingPlaceholder) {
            return new char[0];
        }
        return super.getPassword();
    }
}
