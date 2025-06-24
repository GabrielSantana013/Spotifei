/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auth;

import DAO.DbConnection;
import DAO.UserDAO;
import model.User;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Classe responsável por autenticar usuários com base em login e senha.
 * Implementa a interface {@code Authenticator<User>}.
 * 
 * Utiliza a camada DAO para verificar as credenciais fornecidas contra o banco de dados.
 * Em caso de sucesso, retorna um objeto {@code User} preenchido com os dados do usuário.
 * 
 * @author Gabriel
 */
public class UserAuthenticator implements Authenticator<User> {

    /**
     * Autentica um usuário com base nas credenciais fornecidas.
     * 
     * @param login O login do usuário.
     * @param password A senha do usuário.
     * @return Um objeto {@code User} se a autenticação for bem-sucedida, ou {@code null} caso contrário.
     */
    @Override
    public User authenticate(String login, String password) {
        try {
            Connection conn = new DbConnection().getConnection();
            UserDAO dao = new UserDAO(conn);
            ResultSet result = dao.login(new User(login, password));

            if (result.next()) {
                return User.fromResultSet(result);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
