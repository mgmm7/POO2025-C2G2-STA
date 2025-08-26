package pe.edu.upeu.asistencia.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.modelo.Estudiante;
import pe.edu.upeu.asistencia.repositorio.EstudianteRepositorio;

import java.util.ArrayList;
import java.util.List;
@Service
public class EstudianteServisioImp extends EstudianteRepositorio implements EstudianteServicioI {

    @Override
    public void saveEntidad(Estudiante estudiante) {
        ListaEstudiantes.add(estudiante);
    }

    @Override
    public List<Estudiante> findAllEntidades() {
        return ListaEstudiantes;
    }

    @Override
    public void deleteEntidad(Estudiante estudiante, int index) {
        ListaEstudiantes.remove(index);
    }

    @Override
    public void updateEntidad(Estudiante estudiante, int index) {
        ListaEstudiantes.set(index,estudiante);
    }

    @Override
    public Estudiante findEntidad(int index) {
        return ListaEstudiantes.get(index);
    }
}
