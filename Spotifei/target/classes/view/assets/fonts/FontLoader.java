package view.assets.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe utilitária para carregar e registrar fontes customizadas
 * no ambiente gráfico da aplicação.
 * 
 * <p>As fontes devem estar disponíveis no classpath no caminho informado,
 * normalmente dentro da pasta resources.</p>
 * 
 * @author Pedro Schneider
 */
public class FontLoader {

    /**
     * Registra uma fonte TrueType ou OpenType a partir do caminho
     * do recurso dentro do classpath.
     * 
     * @param resourcePath caminho do arquivo de fonte (.ttf ou .otf) relativo ao classpath,
     *                     por exemplo "/view/assets/fonts/Gotham-Black.otf"
     */
    public static void registerFont(String resourcePath) {
        try {
            InputStream is = FontLoader.class.getResourceAsStream(resourcePath);
            if (is == null) {
                System.err.println("Font file not found: " + resourcePath);
                return;
            }

            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            //System.out.println("Registered font: " + font.getFontName());

        } catch (IOException | FontFormatException e) {
            System.err.println("Error loading font: " + resourcePath);
            e.printStackTrace();
        }
    }

    /**
     * Registra todas as fontes definidas no método.
     * Este método chama internamente {@link #registerFont(String)} para
     * cada arquivo de fonte listado.
     */
    public static void registerAllFonts() {
        registerFont("/view/assets/fonts/Gotham-Black.otf");
        registerFont("/view/assets/fonts/Gotham-Bold.otf");
        registerFont("/view/assets/fonts/Gotham-BookItalic.otf");
        registerFont("/view/assets/fonts/Gotham-Light.otf");
        registerFont("/view/assets/fonts/Gotham-Thin.otf");
        registerFont("/view/assets/fonts/Gotham-ThinItalic.otf");
        registerFont("/view/assets/fonts/Gotham-UltraItalic.otf");
        registerFont("/view/assets/fonts/Gotham-Bold.ttf");
        registerFont("/view/assets/fonts/Gotham-BoldItalic.ttf");
        registerFont("/view/assets/fonts/Gotham-Book.ttf");
        registerFont("/view/assets/fonts/Gotham-BookItalic.ttf");
        registerFont("/view/assets/fonts/Gotham-BookItalic.otf");
        registerFont("/view/assets/fonts/Gotham-Light.ttf");
        registerFont("/view/assets/fonts/Gotham-Light.otf");
        registerFont("/view/assets/fonts/Gotham-LightItalic.ttf");
        registerFont("/view/assets/fonts/Gotham-Medium.ttf");
        registerFont("/view/assets/fonts/Gotham-MediumItalic.ttf");
        registerFont("/view/assets/fonts/Gotham-Medium_1.ttf");
    }
}
