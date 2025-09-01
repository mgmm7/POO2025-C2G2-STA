package pe.edu.upeu.asistencia.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.asistencia.enums.CARRERA;
import pe.edu.upeu.asistencia.enums.TIPO_PARTICIPANTE;
import pe.edu.upeu.asistencia.modelo.Participante;

import java.util.ArrayList;
import java.util.List;

public abstract class ParticianteRepositorio {
    public List<Participante> listaParticipantes =new ArrayList<>();

    public List<Participante> findAll(){
        listaParticipantes.add(
                new Participante(
                        new SimpleStringProperty("61159322"),
                        new SimpleStringProperty("Maykol"),
                        new SimpleStringProperty("Montalvo"),
                        new SimpleBooleanProperty(true), CARRERA.Arquitectura,
                        TIPO_PARTICIPANTE.Asistente
                )
        );
        listaParticipantes.add(
                new Participante(
                        new SimpleStringProperty("123456789"),
                        new SimpleStringProperty("Viruzzz"),
                        new SimpleStringProperty("123"),
                        new SimpleBooleanProperty(true), CARRERA.Civil,
                        TIPO_PARTICIPANTE.Organizador
                )
        );
        return listaParticipantes;

    }
}
