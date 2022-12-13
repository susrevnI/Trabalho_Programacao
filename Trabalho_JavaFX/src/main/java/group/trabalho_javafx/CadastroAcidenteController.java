package group.trabalho_javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Enumeration;

import group.trabalho_javafx.Exceptions.AtributoVazioException;
import group.trabalho_javafx.Exceptions.DanoExistenteException;
import group.trabalho_javafx.Exceptions.DanoVazioException;
import group.trabalho_javafx.Exceptions.ListaVaziaException;
import group.trabalho_javafx.Exceptions.TituloExistenteException;
import group.trabalho_javafx.Util.Listas;
import group.trabalho_javafx.Util.Notificacao;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CadastroAcidenteController implements Initializable {

    @FXML
    private AnchorPane anchorCadastrosAcidente;

    @FXML
    private TextField campoDanoAcidente;

    @FXML
    private TextField campoDataAcidente;

    @FXML
    private TextField campoHorarioAcidente;

    @FXML
    private TextArea campoRelatorioAcidente;

    @FXML
    private TextField campoTituloAcidente;

    @FXML
    private TableColumn<Acidente, Acidente> colunaDanosAcidente;

    @FXML
    private TableColumn<Acidente, String> colunaDataHorario;

    @FXML
    private TableColumn<Acidente, Acidente> colunaDeletarAcidente;

    @FXML
    private TableColumn<String, String> colunaDeletarDano;

    @FXML
    private TableColumn<String, String> colunaDescricaoDano;

    @FXML
    private TableColumn<Acidente, Acidente> colunaEditarAcidente;

    @FXML
    private TableColumn<String, String> colunaEditarDano;

    @FXML
    private TableColumn<Acidente, Acidente> colunaRelatorioAcidente;

    @FXML
    private TableColumn<Acidente, String> colunaTituoAcidente;

    @FXML
    private Tab tabCadastroAcidente;

    @FXML
    private Tab tabConsultaAcidente;

    @FXML
    private TabPane tabPaneAcidentes;

    @FXML
    private TableView<Acidente> tabelaAcidadentes;

    @FXML
    private TableView<String> tabelaDanos;

    private ObservableList<Acidente> obsListaAcidentes;

    private ObservableList<String> obsListaDanos;

    private Acidente acidente;

    private String dano;

    private Button botaoCancelarEdicao;

    private Button botaoCancelarEdicaoDano;

    private List<String> listaDanos;

    @FXML
    void VoltarMenu(ActionEvent event) throws IOException {
        HelloApplication.setRaiz("TelaMenu.fxml", 600., 900.);
    }

    @FXML
    void botaoAdicionarDano(ActionEvent event) {

        try {

            if (dano == null) {

                dano = campoDanoAcidente.getText().trim();

                if (!dano.equals("") && !listaDanos.contains(dano)) {
                    listaDanos.add(dano);
                    atualizaTabelaDanos();
                    dano = null;
                    campoDanoAcidente.clear();
                } else if (listaDanos.contains(dano)) {
                    dano = null;
                    throw new DanoExistenteException();
                }

            } else {

                String novoDano = campoDanoAcidente.getText().trim();

                if (!novoDano.equals("") && !listaDanos.contains(novoDano)){
                    int index = listaDanos.indexOf(dano);
                    listaDanos.set(index, novoDano);
                    atualizaTabelaDanos();
                    anchorCadastrosAcidente.getChildren().remove(botaoCancelarEdicaoDano);

                    dano = null;
                    campoDanoAcidente.clear();

                } else if (listaDanos.contains(novoDano)){
                    throw new DanoExistenteException();
                } else {
                    throw new DanoVazioException();
                }
            }
        } catch (DanoVazioException e) {
            Notificacao.MostraNotificacao("Campo vazio", null, "Não se pode editar para um dado vazio");
            campoDanoAcidente.setText(dano);
        } catch (DanoExistenteException e) {
            Notificacao.MostraNotificacao("Dado já existente", null, "Esse registro já existe");
            campoDanoAcidente.setText(dano);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }


    @FXML
    void botaoCadastrarAcidente(ActionEvent event) {

        try {

            String titulo = campoTituloAcidente.getText().trim();
            String data = campoDataAcidente.getText().trim();
            String horario = campoHorarioAcidente.getText().trim();
            String relatorio = campoRelatorioAcidente.getText().trim();

            String[] vetorAtributos = new String[]{titulo, data, horario};
            List<String> listaAtributos = Arrays.asList(vetorAtributos);

            if (listaAtributos.contains("") || relatorio.equals("")) {
                
                String atributosFalta = "";

                TextField[] vetorFieldAtributos = new TextField[]{campoTituloAcidente, campoDataAcidente, campoHorarioAcidente};

                for (String elemento : listaAtributos) {
                    if (elemento.equals("")){
                        atributosFalta += "> " + vetorFieldAtributos[listaAtributos.indexOf(elemento)].getPromptText() + "\n";
                        listaAtributos.set(listaAtributos.indexOf(elemento), null);
                    }
                }

                if (relatorio.equals("")) {
                    atributosFalta += "> " + campoRelatorioAcidente.getPromptText() + "\n";
                }

                throw new AtributoVazioException(atributosFalta);

            } else if (Listas.getTituloLista().contains(titulo)) {
                throw new TituloExistenteException();
            } else if (listaDanos.size() == 0) {
                Optional<ButtonType> confirmacao = Notificacao.MostraConfirmacao("Cadastro sem danos", "O seu cadastro não possui danos.", "Deseja continuar?");

                if (confirmacao.get() != ButtonType.OK) {
                    throw new ListaVaziaException();
                }
            } 

            if (acidente == null) {
                acidente = new Acidente(titulo, data, horario, relatorio, listaDanos);
                Listas.listaAcidentes.add(acidente);
            } else {
                int posicao = Listas.listaAcidentes.indexOf(acidente);
                acidente = new Acidente(titulo, data, horario, relatorio, listaDanos);
                Listas.listaAcidentes.set(posicao, acidente);
                anchorCadastrosAcidente.getChildren().remove(botaoCancelarEdicao);
            }

            limparFormulario();

            atualizaTabelaAcidentes();

            acidente = null;
            
            Notificacao.MostraNotificacao("Cadastro realizado com sucesso!!",null,"O cadastro foi realizado");

        } catch (AtributoVazioException e) {
            Notificacao.MostraNotificacao("Erro de sintaxe", "O seu cadastro não possui os seguintes atributos: ", e.getMessage());
        } catch (ListaVaziaException e) {
        } catch (TituloExistenteException e) {
            Notificacao.MostraNotificacao("Título Já existente", null, "Já existe um Acidente com esse título");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    private void limparFormulario() {
        campoTituloAcidente.clear();
        campoDanoAcidente.clear();
        campoDataAcidente.clear();
        campoHorarioAcidente.clear();
        campoRelatorioAcidente.clear();
        listaDanos = new ArrayList<>();
        atualizaTabelaDanos();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaDanos = new ArrayList<>();
        inicializaTela();
    }

    public void inicializaTela() {
        colunaTituoAcidente.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaDataHorario.setCellValueFactory(new PropertyValueFactory<>("dataHorario"));

        colunaDescricaoDano.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        atualizaTabelaAcidentes();
        atualizaTabelaDanos();
    }

    public void atualizaTabelaAcidentes() {
        obsListaAcidentes = FXCollections.observableList(Listas.listaAcidentes);
        tabelaAcidadentes.setItems(obsListaAcidentes);
        inicializaBotaoEditar();
        inicializaBotaoDeletar();
        inicializaBotaoDanos();
        inicializaBotaoRelatorio();
    }

    public void atualizaTabelaDanos() {
        obsListaDanos = FXCollections.observableList(listaDanos);
        tabelaDanos.setItems(obsListaDanos);
        inicializaBotaoEditarDano();
        inicializaBotaoDeletarDano();
    }

    private void inicializaBotaoDeletarDano() {
        colunaDeletarDano.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colunaDeletarDano.setCellFactory(param -> new TableCell<String, String>() {

            private final Button button = new Button("Deletar");

            @Override
            protected void updateItem(String obj, boolean vazio) {
                super.updateItem(obj, vazio);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> removeDano(obj));
            }

            private void removeDano(String obj) {
                Optional<ButtonType> resultado = Notificacao.MostraConfirmacao("Confirmação", null,
                        "Você tem certeza que deseja remover o dado?");

                if (resultado.get() == ButtonType.OK) {
                    listaDanos.remove(obj);
                    atualizaTabelaDanos();
                    if (anchorCadastrosAcidente.getChildren().contains(botaoCancelarEdicaoDano)){
                        anchorCadastrosAcidente.getChildren().remove(botaoCancelarEdicaoDano);
                        dano = null;
                    }
                }
            }
        });
    }

    private void inicializaBotaoEditarDano() {
        colunaEditarDano.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colunaEditarDano.setCellFactory(param -> new TableCell<String, String>() {

            private final Button button = new Button("Editar");

            @Override
            protected void updateItem(String obj, boolean vazio) {
                super.updateItem(obj, vazio);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> editarDano(obj));
            }

            private void editarDano(String obj) {
                campoDanoAcidente.setText(obj);
                dano = obj;

                botaoCancelarEdicaoDano = new Button();

                botaoCancelarEdicaoDano.setText("Cancelar");

                botaoCancelarEdicaoDano.setFont(new Font(12));

                botaoCancelarEdicaoDano.setPrefSize(62, 33);

                botaoCancelarEdicaoDano.setLayoutX(787);
                botaoCancelarEdicaoDano.setLayoutY(240);

                botaoCancelarEdicaoDano.setOnAction(event -> cancelarEdicao(botaoCancelarEdicaoDano));

                anchorCadastrosAcidente.getChildren().add(botaoCancelarEdicaoDano);
            }

            private void cancelarEdicao(Button botao) {
                campoDanoAcidente.clear();
                dano = null;
                anchorCadastrosAcidente.getChildren().remove(botao);
            }
        });
    }

    private void inicializaBotaoRelatorio() {
        colunaRelatorioAcidente.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colunaRelatorioAcidente.setCellFactory(param -> new TableCell<Acidente, Acidente>() {

            private final Button button = new Button("Relatorio");

            @Override
            protected void updateItem(Acidente obj, boolean vazio) {
                super.updateItem(obj, vazio);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> mostraRelatorio(obj.getRelatorio()));
            }

            private void mostraRelatorio(String relatorio){
                try {
                    final Stage novaJanela = new Stage();

                    ResourceBundle rbJanela = new ResourceBundle(){
                        @Override
                        protected Object handleGetObject(String key)  //A string 'key' será captada no método INITIALIZE da janela que será aberta 
                        {
                            return relatorio;
                        }

                        @Override
                        public Enumeration<String> getKeys() {
                            throw new UnsupportedOperationException("Not supported yet."); 
                        }
                    };

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaVerRelatorio.fxml"), rbJanela);
                    Scene novaTela = new Scene(fxmlLoader.load(), 600., 400.);

                    novaJanela.setTitle("Relatorio do acidente");
                    novaJanela.setScene(novaTela);
                    novaJanela.showAndWait();
                    
                } catch (IOException e) {
                    Notificacao.MostraNotificacao("Erro ao carregar a janela", null, "Não foi possivel caregar o relatorio");
                    e.printStackTrace();
                } catch (Exception e) {
                    Notificacao.MostraNotificacao("Erro inesperado", e.getMessage(), e.getLocalizedMessage());
                }
            }
        });
    }

    private void inicializaBotaoDanos() {
        colunaDanosAcidente.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colunaDanosAcidente.setCellFactory(param -> new TableCell<Acidente, Acidente>() {

            private final Button button = new Button("Danos");

            @Override
            protected void updateItem(Acidente obj, boolean vazio) {
                super.updateItem(obj, vazio);
                if (obj == null ) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> mostraDanos(obj.getListaDanos()));
            }

            private void mostraDanos(List<String> list) {
                try {
                    final Stage novaJanela = new Stage();

                    ResourceBundle rbJanela = new ResourceBundle(){
                        @Override
                        protected Object handleGetObject(String key)  //A string 'key' será captada no método INITIALIZE da janela que será aberta 
                        {
                            return list;
                        }

                        @Override
                        public Enumeration<String> getKeys() {
                            throw new UnsupportedOperationException("Not supported yet."); 
                        }
                    };
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaVerDanos.fxml"), rbJanela);
                    Scene novaTela = new Scene(fxmlLoader.load(), 600., 400.);

                    novaJanela.setTitle("Danos do acidente");
                    novaJanela.setScene(novaTela);
                    novaJanela.show();
                    
                } catch (IOException e) {
                    Notificacao.MostraNotificacao("Erro ao carregar a janela", null, "Não foi possivel caregar o relatorio");
                    e.printStackTrace();
                } catch (Exception e) {
                    Notificacao.MostraNotificacao("Erro inesperado", e.getMessage(), e.getLocalizedMessage());
                }
            }
        });

    }

    private void inicializaBotaoDeletar() {
        colunaDeletarAcidente.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colunaDeletarAcidente.setCellFactory(param -> new TableCell<Acidente, Acidente>() {

            private final Button button = new Button("Deletar");

            @Override
            protected void updateItem(Acidente obj, boolean vazio) {
                super.updateItem(obj, vazio);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> removeAcidente(obj));
            }

            private void removeAcidente(Acidente obj) {
                Optional<ButtonType> resultado = Notificacao.MostraConfirmacao("Confirmação", null,
                        "Você tem certeza que deseja remover o acidente?");

                if (resultado.get() == ButtonType.OK) {
                    Listas.listaAcidentes.remove(obj);
                    atualizaTabelaAcidentes();
                }
            }
        });
    }

    public void inicializaBotaoEditar() {
        colunaEditarAcidente.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colunaEditarAcidente.setCellFactory(param -> new TableCell<Acidente, Acidente>() {

            private final Button button = new Button("Editar");

            @Override
            protected void updateItem(Acidente obj, boolean vazio) {
                super.updateItem(obj, vazio);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> editarAcidente(obj));
            }

            private void editarAcidente(Acidente obj) {
                campoTituloAcidente.setText(obj.getTitulo());
                campoDataAcidente.setText(obj.getData());
                campoHorarioAcidente.setText(obj.getHorario());
                campoRelatorioAcidente.setText(obj.getRelatorio());
                listaDanos = obj.getListaDanos();
                atualizaTabelaDanos();
                acidente = obj;

                tabPaneAcidentes.getSelectionModel().select(tabCadastroAcidente);

                botaoCancelarEdicao = new Button();

                botaoCancelarEdicao.setText("Cancelar Edicao");

                botaoCancelarEdicao.setFont(new Font(14));

                botaoCancelarEdicao.setPrefSize(117, 51);

                botaoCancelarEdicao.setLayoutX(70);
                botaoCancelarEdicao.setLayoutY(462);

                botaoCancelarEdicao.setOnAction(event -> cancelarEdicao(botaoCancelarEdicao));

                anchorCadastrosAcidente.getChildren().add(botaoCancelarEdicao);
            }

            private void cancelarEdicao(Button botao) {
                limparFormulario();
                acidente = null;
                anchorCadastrosAcidente.getChildren().remove(botao);
            }
        });
    }

}
