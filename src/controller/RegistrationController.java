/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UserDAO;
import DAO.DbConnection;
import model.User;
import view.RegistrationWindow;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.SwingUtilities;
import view.LoginWindow;
import view.customDialogs.CustomJDialog;

/**
 * Controlador responsável por gerenciar a lógica de cadastro de novos usuários
 * no sistema Spotifei. Realiza a coleta e validação dos dados informados na interface
 * gráfica {@link RegistrationWindow}, realiza a persistência no banco de dados através
 * do {@link UserDAO} e redireciona o fluxo para a próxima janela conforme o tipo de cadastro.
 * 
 * Suporta tanto cadastros realizados por usuários comuns quanto por administradores.
 * 
 * @author Pedro Schneider, Gabriel Santana Dias
 */
public class RegistrationController {

    private RegistrationWindow view;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private boolean isAdm;

    /**
     * Construtor da classe RegistrationController.
     *
     * @param view  A janela de cadastro associada a este controlador.
     * @param isAdm Indica se o cadastro está sendo feito por um administrador.
     */
    public RegistrationController(RegistrationWindow view, boolean isAdm) {
        this.view = view;
        this.isAdm = isAdm;
    }

    /**
     * Realiza o processo de cadastro de um novo usuário:
     * <ul>
     *   <li>Obtém os dados inseridos na interface gráfica.</li>
     *   <li>Valida a data de nascimento.</li>
     *   <li>Cria um objeto {@link User} com os dados fornecidos.</li>
     *   <li>Insere o usuário no banco de dados por meio do {@link UserDAO}.</li>
     *   <li>Exibe mensagens de sucesso ou erro utilizando {@link CustomJDialog}.</li>
     *   <li>Redireciona para a tela de login (caso não seja administrador).</li>
     * </ul>
     */
    public void saveUser() {

        String userLogin = view.getTxt_login().getText();
        String userPassword = view.getTxt_password().getText();    
        String name = view.getTxt_name().getText();
        String gender = view.getCbox_gender().getSelectedItem().toString();        
        String bDateString = view.getTxt_birthDate().getText();
        Date birthDate = null;

        try {
            // não aceita datas inválidas
            sdf.setLenient(false);
            birthDate = sdf.parse(bDateString);            
        } catch (ParseException e) {
            SwingUtilities.invokeLater(() -> {
                CustomJDialog.showCustomDialog("Aviso!", "Data inválida");
            });
        }

        User user = new User(userLogin, userPassword, name, gender, birthDate);       
        DbConnection connection = new DbConnection();

        try {
            Connection conn = connection.getConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);

            SwingUtilities.invokeLater(() -> {
                CustomJDialog.showCustomDialog("Cadastro", "Usuário cadastrado com sucesso!");
            });

            if (this.isAdm) {
                view.setVisible(false);
            } else {
                LoginWindow lw = new LoginWindow();
                lw.setVisible(true);
                view.setVisible(false);
            }

        } catch (SQLException e) {
            SwingUtilities.invokeLater(() -> {
                CustomJDialog.showCustomDialog("ERRO!", "Erro de conexão com o banco de dados.\nEntre em contato com os administradores.");
            });
        }
    }

}
