package pe.edu.upeu.asistencia.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.modelo.Participante;
import pe.edu.upeu.asistencia.repositorio.ParticipanteRepositorio;

import java.util.List;

@Service
public class ParticipanteServicioImp extends ParticipanteRepositorio implements ParticipanteServicioI {

    @Override
    public void save(Participante participante) {
    super.save(participante);}

    @Override
    public List<Participante> findAll() {
        return super.findAll();
    }

    @Override
    public Participante update(Participante participante) {
        return super.update(participante);
    }

    @Override
    public void delete(String dni) {
        super.delete(dni);
    }

    @Override
    public Participante findById(int index) {
        return listaParticipantes.get(index);
    }
}
