package group.trabalho_javafx;

import java.io.IOException;

import group.trabalho_javafx.Exceptions.SenhaAcceptedException;
import group.trabalho_javafx.Exceptions.SenhaErroException;
import group.trabalho_javafx.Util.Notificacao;
/*import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import group.trabalho_javafx.util.Conexao;*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField campoSenha;

    @FXML
    private TextField campoUsuario;

    @FXML
    void Confirmar(ActionEvent event) throws IOException {

        /*try{

            Connection connection = Conexao.getConnection();

            String senha = campoSenha.getText();
            String usuario = campoUsuario.getText();

            Statement stm = connection.createStatement();

            System.out.println(stm);

            String comandoSql = "select * from Users where usuario = '"+usuario+"';";
            
            ResultSet resultado = stm.executeQuery(comandoSql);

            System.out.println(resultado);
            System.out.println(resultado);
            if (resultado.next()){
                if (!resultado.getString("usuario").equals("")){
                    System.out.println("1");
                    if (resultado.getString("senha").equals(senha)){
                        System.out.println("2");
                        HelloApplication.setRaiz("hello-view.fxml",400,300);
                    }
                }
            }
        } catch (Exception e) {}*/

        String senha = campoSenha.getText();
        String usuario = campoUsuario.getText();

        try {
            if (senha.equals("senha") && usuario.equals("senha")) {
                throw new SenhaAcceptedException();
            } else {
                throw new SenhaErroException();
            }
        } catch (SenhaErroException e) {
            Notificacao.MostraNotificacao("Erro de acesso.", e.getMessage(), "Usuario ou senha incorretos.");
        } catch (SenhaAcceptedException e) {
            Notificacao.MostraNotificacao("Acesso concedido.", e.getMessage(), "Senha e usuario corretos.");
            HelloApplication.setRaiz("TelaMenu.fxml",600.,900.);
        }
    }

}
