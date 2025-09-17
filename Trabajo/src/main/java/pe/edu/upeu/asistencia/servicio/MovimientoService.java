package pe.edu.upeu.asistencia.servicio;

import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.enums.TipoMovimiento;
import pe.edu.upeu.asistencia.modelo.Movimiento;
import pe.edu.upeu.asistencia.repositorio.MovimientoRepository;

import java.util.List;
@Service
public class MovimientoService {
    private final MovimientoRepository repo = new MovimientoRepository();

    public void registrarMovimiento(Movimiento m) {
        double saldoAnterior = calcularSaldo();
        if (m.getTipo() == TipoMovimiento.INGRESO) {
            m.setSaldo(saldoAnterior + m.getMonto());
        } else {
            m.setSaldo(saldoAnterior - m.getMonto());
        }
        repo.save(m);
    }

    public ObservableList<Movimiento> listarMovimientos() {
        return repo.findAll();
    }

    public double calcularSaldo() {
        return listarMovimientos().stream()
                .mapToDouble(m -> m.getTipo() == TipoMovimiento.INGRESO ? m.getMonto() : -m.getMonto())
                .sum();
    }

    public double calcularBalance() {
        double ingresos = 0.0;
        double gastos = 0.0;
        for (Movimiento m : repo.findAll()) {
            if (m.getTipo() == TipoMovimiento.INGRESO) ingresos += m.getMonto();
            else gastos += m.getMonto();
        }
        return ingresos - gastos;
    }
    public void eliminarMovimiento(Movimiento m) {
        repo.delete(m);
    }
}
