package group.trabalho_javafx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VerDanosController implements Initializable {

    @FXML
    private TableColumn<String, String> colunaDescricaoDanos;

    @FXML
    private TableView<String> tabelaDanos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colunaDescricaoDanos.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        //ObservableList<String> obsListaDanos = FXCollections.observableList(resourceBundle.getString(null));
        //tabelaDanos.setItems();

        List<String> listaDanos = (List<String>) resourceBundle.getObject(null);

        ObservableList<String> obsListaDanos = FXCollections.observableList(listaDanos);
        System.out.println();
        System.out.println(obsListaDanos);
        tabelaDanos.setItems(obsListaDanos);
    }

}
