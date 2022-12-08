package group.trabalho_javafx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Arrays;

import group.trabalho_javafx.Exceptions.AtributoException;
import group.trabalho_javafx.Util.Listas;
import group.trabalho_javafx.Util.Notificacao;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class CadastroFuncionarioController implements Initializable {

    @FXML
    private TextField campoCnpjFunc;

    @FXML
    private TextField campoCpfFunc;

    @FXML
    private TextField campoDataNascFunc;

    @FXML
    private TextField campoEmailFunc;

    @FXML
    private TextField campoNomeFunc;

    @FXML
    private TextField campoTelefoneFunc;

    @FXML
    private TableColumn<Funcionario, String> colunaCnpjFunc;

    @FXML
    private TableColumn<Funcionario, String> colunaCpfFunc;

    @FXML
    private TableColumn<Funcionario, String> colunaDataNascFunc;

    @FXML
    private TableColumn<Funcionario, Funcionario> colunaDeletarFunc;

    @FXML
    private TableColumn<Funcionario, Funcionario> colunaEditarFunc;
    
    @FXML
    private TableColumn<Funcionario, String> colunaEmailFunc;

    @FXML
    private TableColumn<Funcionario, String> colunaNomeFunc;

    @FXML
    private TableColumn<Funcionario, String> colunaTelefoneFunc;

    @FXML
    private AnchorPane anchorCadastros;

    @FXML
    private Tab tabCadastroFunc;

    @FXML
    private Tab tabConsultaFunc;

    @FXML
    private TabPane tabPaneFunc;

    @FXML
    private TableView<Funcionario> tabelaFunc;

    private ObservableList<Funcionario> obsLista;

    private Funcionario funcionario;

    @FXML
    void VoltarMenu(ActionEvent event) throws IOException {
        HelloApplication.setRaiz("TelaMenu.fxml", 600.,900.);
    }

    @FXML
    void botaoSalvarFunc(ActionEvent event) {

        try {

        String nome = campoNomeFunc.getText().trim();
        String dataNasc = campoDataNascFunc.getText().trim();
        String cpf = campoCpfFunc.getText().trim();
        String cnpj = campoCnpjFunc.getText().trim();
        String email = campoEmailFunc.getText().trim();
        String telefone = campoTelefoneFunc.getText().trim();

        String[] vetorAtributos = new String[]{nome,dataNasc,cpf,cnpj,email,telefone};
        List<String> listaAtributos = Arrays.asList(vetorAtributos);

        if (listaAtributos.contains("")){

            String atributosFalta = "";

            TextField[] vetorFieldAtributos = new TextField[]{campoNomeFunc,campoDataNascFunc,campoCpfFunc,campoCnpjFunc,campoEmailFunc,campoTelefoneFunc};

            for (String elemento : listaAtributos) {
                if (elemento.equals("")){
                    atributosFalta += "> " + vetorFieldAtributos[listaAtributos.indexOf(elemento)].getPromptText() + "\n";
                    listaAtributos.set(listaAtributos.indexOf(elemento), null);
                }
            }

            throw new AtributoException(atributosFalta);
        }

        if (funcionario == null) {
            funcionario = new Funcionario(nome, dataNasc, cpf, cnpj, email, telefone);
            Listas.listaFuncionarios.add(funcionario);
        } else {
            int posicao = Listas.listaFuncionarios.indexOf(funcionario);
            funcionario = new Funcionario(nome, dataNasc, cpf, cnpj, email, telefone);
            Listas.listaFuncionarios.set(posicao, funcionario);
        }

        limparFormulario();

        atualizaTabela();

        funcionario = null;
        
        Notificacao.MostraNotificacao("Cadastro realizado com sucesso!!",null,"O cadastro foi realizado");

        } catch (AtributoException e) {
            Notificacao.MostraNotificacao("Erro de sintaxe", "O seu cadastro não possui os seguintes atributos: ", e.getMessage());
        } catch (Exception e) {
            Notificacao.MostraNotificacao("Erro inesperado", e.getMessage(), null);
        }
    }

    private void limparFormulario(){
        campoNomeFunc.clear();
        campoDataNascFunc.clear();
        campoCpfFunc.clear();
        campoCnpjFunc.clear();
        campoEmailFunc.clear();
        campoTelefoneFunc.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializaTela();
    }

    public void inicializaTela() {
        colunaNomeFunc.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDataNascFunc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
        colunaCpfFunc.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaCnpjFunc.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        colunaEmailFunc.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTelefoneFunc.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        atualizaTabela();
    }

    public void atualizaTabela() {
        obsLista = FXCollections.observableList(Listas.listaFuncionarios);
        tabelaFunc.setItems(obsLista);
        inicializaBotaoEditar();
        inicializaBotaoDeletar();
    }

    private void inicializaBotaoDeletar() {
        colunaDeletarFunc.setCellValueFactory( param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colunaDeletarFunc.setCellFactory(param -> new TableCell<Funcionario, Funcionario>(){
            private final Button button = new Button("Remover");

            @Override
            protected void updateItem(Funcionario obj, boolean vazio){
                super.updateItem(obj,vazio);
                if (obj== null){
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> removeFuncionario(obj));
            }

            private void removeFuncionario(Funcionario obj) {
                Optional<ButtonType> resultado = Notificacao.MostraConfirmacao("Confirmação", null, "Você tem certeza que deseja remover o funcionario?");

                if (resultado.get() == ButtonType.OK){
                    Listas.listaFuncionarios.remove(obj);
                    atualizaTabela();
                }
            }
        });
    }

    public void inicializaBotaoEditar() {
        colunaEditarFunc.setCellValueFactory( param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colunaEditarFunc.setCellFactory(param -> new TableCell<Funcionario,Funcionario>(){
            private final Button button = new Button("Editar");

            @Override
            protected void updateItem(Funcionario obj, boolean vazio){
                super.updateItem(obj,vazio);
                if (obj == null){
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> editarFuncionario(obj));
            }

            private void editarFuncionario(Funcionario obj) {
                campoNomeFunc.setText(obj.getNome());
                campoDataNascFunc.setText(obj.getDataNasc());
                campoCpfFunc.setText(obj.getCpf());
                campoCnpjFunc.setText(obj.getCnpj());
                campoEmailFunc.setText(obj.getEmail());
                campoTelefoneFunc.setText(obj.getTelefone());
                funcionario = obj;

                tabPaneFunc.getSelectionModel().select(tabCadastroFunc);

                Button botaoCancelarEdicao = new Button();

                botaoCancelarEdicao.setText("Cancelar Edicao");

                botaoCancelarEdicao.setFont(new Font(14));

                botaoCancelarEdicao.setPrefSize(116, 54);

                botaoCancelarEdicao.setLayoutX(516);
                botaoCancelarEdicao.setLayoutY(335);

                botaoCancelarEdicao.setOnAction(event -> cancelarEdicao(botaoCancelarEdicao));

                anchorCadastros.getChildren().add(botaoCancelarEdicao);
            }

            private void cancelarEdicao(Button botao){
                limparFormulario();
                funcionario = null;
                anchorCadastros.getChildren().remove(botao);
            }
        });
    }

}
