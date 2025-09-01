package pe.edu.upeu.asistencia.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.asistencia.modelo.Participante;

import java.util.ArrayList;
import java.util.List;

public class ParticianteRepositorio {
    public List<Participante> listaParticipantes =new ArrayList<>();

    public List<Participante> findAll(){
        listaParticipantes.add(
                new Participante(
                        new SimpleStringProperty("Juan"),
                        new SimpleBooleanProperty(true)
                )
        );
        listaParticipantes.add(
                new Participante(
                        new SimpleStringProperty("Viruzzz"),
                        new SimpleBooleanProperty(true)
                )
        );
        return listaParticipantes;

    }
}
