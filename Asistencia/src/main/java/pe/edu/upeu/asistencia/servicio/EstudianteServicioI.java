package pe.edu.upeu.asistencia.servicio;

import pe.edu.upeu.asistencia.modelo.Estudiante;

import java.util.List;

public interface EstudianteServicioI {
    void saveEntidad(Estudiante estudiante); //C

    List<Estudiante> findAllEntidades();//R

    void deleteEntidad(Estudiante estudiante, int index);//U

    void updateEntidad(Estudiante estudiante, int index);//D

    Estudiante findEntidad(int index);//Buscar ese


}
