/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package auth;
import java.sql.SQLException;

/**
 *
 * Uma interface genérica para autenticação de entidades com base em credenciais de login.
 * <p>
 * As implementações dessa interface devem fornecer a lógica necessária para validar o login
 * e a senha de um usuário ou administrador, retornando a entidade autenticada caso as credenciais
 * sejam válidas, ou lançando uma exceção caso contrário.
 * 
 * @author Gabriel
 * 
 */
public interface Authenticator<T> {
    
    T authenticate(String login, String password) throws SQLException;
    
}
