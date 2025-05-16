/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.customDialogs.CustomJDialog;
import auth.Authenticator;
import auth.UserAuthenticator;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.*;
import model.Adm;
import model.User;
import view.AdmHomeWindow;
import view.HomeWindow;
import view.LoginWindow;
import java.sql.SQLException;

public class LoginController {

    private LoginWindow view;
    private Authenticator<User> userAuthenticator;
    private Authenticator<Adm> admAuthenticator;

    public LoginController(LoginWindow view) {
        this.view = view;
        this.userAuthenticator = new UserAuthenticator();
    }

    public void login() throws IOException{
        String login = view.getTxt_login().getText();
        char[] passwordChars = view.getTxt_password().getPassword(); // pega a senha
        String password = new String(passwordChars); // converte pra string

            try {           
                User user = userAuthenticator.authenticate(login, password);
                if (user != null) {
                    if (user.isAdm()) {
                        Adm adm = Adm.fromUser(user);
                        SwingUtilities.invokeLater(() -> {
                            CustomJDialog.showCustomDialog("Aviso!", "Administrador logado com sucesso!");
                        });
                        System.out.println(adm); // debug
                        view.setVisible(false);
                        AdmHomeWindow admHomeWin = new AdmHomeWindow(adm); // ou passe o adm se necessário
                        admHomeWin.setVisible(true);
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            CustomJDialog.showCustomDialog("Aviso!", "Usuário logado com sucesso!");
                        });

                        System.out.println(user); // debug
                        view.setVisible(false);
                        HomeWindow homeWin = new HomeWindow(user);
                        homeWin.setVisible(true);
                    }
                    return;
                }
            }catch(IOException e){
                    e.printStackTrace();}
            catch(SQLException e){
                    e.printStackTrace();}
            finally {
            // limpa o array de senha por segurança
            Arrays.fill(passwordChars, '\0');
        }
        // Se não for user nem admin
        SwingUtilities.invokeLater(() -> {
            CustomJDialog.showCustomDialog("Aviso!", "Usuário e/ou senha inválidos!");
        });
    }
}


