package group.trabalho_javafx.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection connection;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String SERVIDOR = "127.0.0.1";
    private static final String PORTA = "3306";
    private static final String BANCO = "Corrida_Carros";
    private static final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA
            + "/" + BANCO;
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public Conexao(){}

    public static synchronized Connection getConnection(){
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL,USUARIO,SENHA);
                System.out.println("Conexão realizada com sucesso!");
            } catch (ClassNotFoundException e) {
                System.out.println("Erro ao carregar o crive de conexão\n");
            } catch (SQLException e) {
                System.out.println("Não foi possível estabelecer conexão com o banco de dados\n");
            }
        }
        return connection;
    }
}