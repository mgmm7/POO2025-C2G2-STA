package pe.edu.upeu.asistencia.control;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.modelo.Movimiento;
import pe.edu.upeu.asistencia.servicio.MovimientoService;
@Controller
public class ReporteController {
    @FXML private TableView<Movimiento> tablaReporte;
    @FXML private TableColumn<Movimiento, String> colDescripcion;
    @FXML private TableColumn<Movimiento, Double> colMonto;
    @FXML private TableColumn<Movimiento, String> colTipo;
    @FXML private Label saldoLabel;

    @Autowired
    private MovimientoService service;

    @FXML
    public void initialize() {
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));


        TableColumn<Movimiento, Double> colSaldo = new TableColumn<>("Saldo");
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        tablaReporte.getColumns().add(colSaldo);


        tablaReporte.setItems(service.listarMovimientos());


        service.listarMovimientos().addListener((javafx.collections.ListChangeListener<Movimiento>) c -> {
            saldoLabel.setText(String.format("%.2f", service.calcularSaldo()));
        });

        saldoLabel.setText(String.format("%.2f", service.calcularSaldo()));

    }
    public void cargarDatos() {
        saldoLabel.setText(String.format("%.2f", service.calcularSaldo()));
    }
}
