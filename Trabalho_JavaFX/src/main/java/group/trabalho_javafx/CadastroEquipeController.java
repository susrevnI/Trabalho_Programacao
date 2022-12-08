package group.trabalho_javafx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class CadastroEquipeController {

    @FXML
    private TableColumn<?, ?> ColunaCorredorEquipe;

    @FXML
    private TextField campoCnpjEquipe;

    @FXML
    private TextField campoNomeEquipe;

    @FXML
    private TableColumn<?, ?> colunaCarroEquipe;

    @FXML
    private TableColumn<?, ?> colunaCnpjEquipe;

    @FXML
    private TableColumn<?, ?> colunaCpfIntegr;

    @FXML
    private TableColumn<?, ?> colunaDeletarEquipe;

    @FXML
    private TableColumn<?, ?> colunaDeletarIntegr;

    @FXML
    private TableColumn<?, ?> colunaEditarIntegr;

    @FXML
    private TableColumn<?, ?> colunaEditraEqupe;

    @FXML
    private TableColumn<?, ?> colunaEmailIntegr;

    @FXML
    private TableColumn<?, ?> colunaFuncaoIntegr;

    @FXML
    private TableColumn<?, ?> colunaLiderEquipe;

    @FXML
    private TableColumn<?, ?> colunaNomeEquipe;

    @FXML
    private TableColumn<?, ?> colunaNomeIntegr;

    @FXML
    private TableColumn<?, ?> colunaNumIntegrEquipe;

    @FXML
    private TableColumn<?, ?> colunaTelefoneIntegr;

    @FXML
    void ValtarMenu(ActionEvent event) throws IOException {
        HelloApplication.setRaiz("TelaMenu.fxml", 600.,900.);
    }

    @FXML
    void botaoCadastrarCarro(ActionEvent event) {

    }

    @FXML
    void botaoCadastrarEquipe(ActionEvent event) {

    }

    @FXML
    void botaoCadastrarMembro(ActionEvent event) {

    }

    @FXML
    void botaoVerCarro(ActionEvent event) {

    }

}
