package pe.edu.upeu.asistencia.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class AsistenciaController {
    @FXML private Label idMsg;
    @FXML TextField txtdato;
    @FXML Button btnenviar;
    @FXML
    void enviar(){
        System.out.println("KS");
        idMsg.setText(txtdato.getText());
    }
}
