package pe.edu.upeu.asistencia.control;
import java.util.Scanner;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.modelo.Estudiante;
import pe.edu.upeu.asistencia.servicio.EstudianteServicioI;

@Controller
public class AsistenciaController {
    @Autowired
    private EstudianteServicioI estudianteServicioI;
    @FXML private Label idMsg;
    @FXML TextField txtdato;
    @FXML Button btnenviar;
    @FXML
    void enviar(){
        System.out.println("KS");
        idMsg.setText(txtdato.getText());

    }
    @FXML
    void regEstudiantes(){
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(new SimpleStringProperty(txtdato.getText()));
        estudiante.setEstado(new SimpleBooleanProperty(true));
        estudianteServicioI.saveEntidad(estudiante);
        listaEstudiantes();
    }
    void listaEstudiantes(){
        for (Estudiante e: estudianteServicioI.findAllEntidades()){
            System.out.println(e.getNombre());
        }
    }
}
