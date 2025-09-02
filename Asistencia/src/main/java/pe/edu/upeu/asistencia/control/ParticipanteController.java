package pe.edu.upeu.asistencia.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.servicio.ParticipanteServicioI;

@Controller
public class ParticipanteController {
    @Autowired
    private ParticipanteServicioI participanteServicioI;

}
