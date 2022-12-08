package group.trabalho_javafx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroAcidenteController {

    @FXML
    private TextField campoDanoAcidente;

    @FXML
    private TextField campoDataAcidente;

    @FXML
    private TextField campoHorarioAcidente;

    @FXML
    private TextArea campoRelatorioAcidente;

    @FXML
    private TextField campoTittuloAcidente;

    @FXML
    private TableColumn<?, ?> colunaDanosAcidente;

    @FXML
    private TableColumn<?, ?> colunaDataHorario;

    @FXML
    private TableColumn<?, ?> colunaDeletarAcidente;

    @FXML
    private TableColumn<?, ?> colunaDescricaoDano;

    @FXML
    private TableColumn<?, ?> colunaEditarAcidente;

    @FXML
    private TableColumn<?, ?> colunaRelatorioAcidente;

    @FXML
    private TableColumn<?, ?> colunaTituoAcidente;

    @FXML
    void VoltarMenu(ActionEvent event) throws IOException {
        HelloApplication.setRaiz("TelaMenu.fxml", 600.,900.);
    }

    @FXML
    void botaoAdicionarDano(ActionEvent event) {

    }

    @FXML
    void botaoCadastrarAcidente(ActionEvent event) {

    }

}
