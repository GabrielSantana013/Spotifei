/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.assets.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class FontLoader {

    public static void registerFont(String resourcePath) {
        // registra as fontes utilizadas
        try {
            InputStream is = FontLoader.class.getResourceAsStream(resourcePath);
            if (is == null) {
                System.err.println("Font file not found: " + resourcePath);
                return;
            }

            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            System.out.println("Registered font: " + font.getFontName());

        } catch (IOException | FontFormatException e) {
            System.err.println("Error loading font: " + resourcePath);
            e.printStackTrace();
        }
    }

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