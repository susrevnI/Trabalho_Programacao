package group.trabalho_javafx.Util;

import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Notificacao {

    public static void NotificacaoLoginAcess(String titulo, String cabecalho, String conteudo){

        Alert notificacao = new Alert(AlertType.INFORMATION);

        notificacao.setTitle(titulo);
        notificacao.setHeaderText(cabecalho);
        notificacao.setContentText(conteudo);
        
        notificacao.showAndWait();
    }

    public static void NotificacaoLoginError(String titulo, String cabecalho, String conteudo){

        Alert notificacao = new Alert(AlertType.ERROR);

        notificacao.setTitle(titulo);
        notificacao.setHeaderText(cabecalho);
        notificacao.setContentText(conteudo);
        
        notificacao.showAndWait();
    }
    
    public static void MostraNotificacao(String titulo, String cabecalho, String conteudo){

        Alert notificacao = new Alert(AlertType.INFORMATION);

        notificacao.setTitle(titulo);
        notificacao.setHeaderText(cabecalho);
        notificacao.setContentText(conteudo);
        
        notificacao.show();
    }

    public static Optional<ButtonType> MostraConfirmacao(String titulo, String cabecalho, String conteudo){

        Alert confirmacao = new Alert(AlertType.CONFIRMATION);

        confirmacao.setTitle(titulo);
        confirmacao.setHeaderText(cabecalho);
        confirmacao.setContentText(conteudo);

        return confirmacao.showAndWait();
    }
}
