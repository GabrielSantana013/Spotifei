/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.customDialogs.CustomJDialog;
import DAO.DbConnection;
import DAO.UserDAO;
import auth.Authenticator;
import auth.UserAuthenticator;
import auth.AdmAuthenticator;
import java.sql.SQLException;
import javax.swing.*;
import model.Adm;
import model.User;
import view.HomeWindow;
import view.LoginWindow;

public class LoginController {

    private LoginWindow view;
    private Authenticator<User> userAuthenticator;
    private Authenticator<Adm> admAuthenticator;

    public LoginController(LoginWindow view) {
        this.view = view;
        this.userAuthenticator = new UserAuthenticator();
        this.admAuthenticator = new AdmAuthenticator();
    }

    public void login() {
        String login = view.getTxt_login().getText();
        String password = view.getTxt_password().getText();

        try {
           //tenta autenticar como user
            User user = userAuthenticator.authenticate(login, password);
            if (user != null) {
                SwingUtilities.invokeLater(() -> {
                    CustomJDialog.showCustomDialog("Aviso!", "Usuário logado com sucesso!");
                });

                System.out.println(user); // debug
                view.setVisible(false);
                HomeWindow homeWin = new HomeWindow(user); // janela do usuário               
                homeWin.setVisible(true);
                return;
            }

            //tenta autenticar como administrador
            Adm adm = admAuthenticator.authenticate(login, password);
            if (adm != null) {
                SwingUtilities.invokeLater(() -> {
                    CustomJDialog.showCustomDialog("Aviso!", "Administrador "
                            + "logado com sucesso!");
                });

                System.out.println(adm); // debug
                view.setVisible(false);
                HomeWindow homeWin = new HomeWindow(null); // ou HomeWindowAdm futuramente
                homeWin.setVisible(true);
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> {
                CustomJDialog.showCustomDialog("Erro!", "Erro de conexão com o "
                        + "banco de dados.\nEntre em contato com os administradores.");
            });
            return;
        }

        // Se não for user nem admin
        SwingUtilities.invokeLater(() -> {
            CustomJDialog.showCustomDialog("Aviso!", "Usuário e/ou senha inválidos!");
        });
    }
}

