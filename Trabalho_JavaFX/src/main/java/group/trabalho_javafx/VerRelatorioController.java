package group.trabalho_javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class VerRelatorioController implements Initializable {

    @FXML
    private Text campoVerRelatorio;

    @Override 
    public void initialize(URL url, ResourceBundle resourceBundle) {
        campoVerRelatorio.setText(resourceBundle.getString(null));
    }

}
