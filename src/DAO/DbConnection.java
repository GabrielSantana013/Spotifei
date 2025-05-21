package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe responsável por estabelecer uma conexão com o banco de dados
 * utilizando as configurações especificadas no arquivo {@code config.properties}.
 * 
 * O arquivo {@code config.properties} deve estar localizado dentro do pacote {@code DAO}
 * e conter as seguintes propriedades:
 * <ul>
 *   <li>{@code db.url} – URL do banco de dados</li>
 *   <li>{@code db.user} – nome de usuário</li>
 *   <li>{@code db.password} – senha</li>
 * </ul>
 * 
 * Exemplo de uso:
 * <pre>{@code
 * DbConnection db = new DbConnection();
 * Connection conn = db.getConnection();
 * }</pre>
 * 
 * @author gabas
 */
public class DbConnection {
    
    /**
     * Estabelece uma conexão com o banco de dados a partir das informações
     * fornecidas no arquivo {@code config.properties}.
     * 
     * @return objeto {@code Connection} ativo com o banco de dados,
     *         ou {@code null} caso ocorra erro ao carregar as propriedades.
     * @throws SQLException se ocorrer um erro durante a conexão com o banco.
     */
    public Connection getConnection() throws SQLException {
        Properties prop = new Properties();
        try {
            InputStream input = DbConnection.class.getClassLoader()
                .getResourceAsStream("DAO/config.properties");
            prop.load(input);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        String url = prop.getProperty("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");
        
        Connection conexao = DriverManager.getConnection(url, user, password);
        if (conexao != null && !conexao.isClosed()) {
            System.out.println("DbConnection works");
        } else {
            System.out.println("DbConnection isn't working");
        }
        return conexao;       
    }
}
