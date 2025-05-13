/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package spotifei;

import view.LoginWindow;
import view.assets.fonts.FontLoader;

/**
 *
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FontLoader.registerAllFonts(); // registra todas as fontes
        
        LoginWindow lw = new LoginWindow();
        lw.setVisible(true);
    }
    
}
