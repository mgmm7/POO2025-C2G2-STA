package pe.edu.upeu.asistencia.modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

@Data
public class Participante {
    private StringProperty nombre;
    private BooleanProperty estado;
}
