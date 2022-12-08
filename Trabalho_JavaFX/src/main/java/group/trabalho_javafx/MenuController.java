package group.trabalho_javafx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController  {

    @FXML
    void IrCadastroAcidente(ActionEvent event) throws IOException {
        HelloApplication.setRaiz("TelaCadastroAcidente.fxml", 600., 900.);
    }

    @FXML
    void IrCadastroCampeonato(ActionEvent event) throws IOException {
        HelloApplication.setRaiz("TelaCadastroCampeonato.fxml", 600., 900.);
    }

    @FXML
    void IrCadastroEquipe(ActionEvent event) throws IOException {
        HelloApplication.setRaiz("TelaCadastroEquipe.fxml", 600., 900.);
    }

    @FXML
    void IrCadastroFuncionario(ActionEvent event) throws IOException {
        HelloApplication.setRaiz("TelaCadastroFuncionario.fxml", 600., 900.);
    }

}
