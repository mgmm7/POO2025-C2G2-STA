package pe.edu.upeu.asistencia.control;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.enums.TipoMovimiento;
import pe.edu.upeu.asistencia.modelo.Movimiento;
import pe.edu.upeu.asistencia.servicio.MovimientoService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;

@Controller
public class MovimientoController {
    private Movimiento movimientoEnEdicion = null;
    @FXML private TableView<Movimiento> tablaMovimientos;
    @FXML private Label saldoLabel;
    @FXML private TextField descripcionField;
    @FXML
    private TextField montoField;
    @FXML private ComboBox<TipoMovimiento> tipoCombo;
    @FXML private Button guardarBtn;
    @FXML private Button reporteBtn;
    @Autowired
    private MovimientoService service;
    @Autowired
    private ApplicationContext context;
    @FXML
    public void initialize() {
        tipoCombo.setItems(FXCollections.observableArrayList(TipoMovimiento.values()));
        tipoCombo.getSelectionModel().selectFirst();
        tablaMovimientos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablaMovimientos.getColumns().clear();
        TableColumn<Movimiento, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Movimiento, Double> colMonto = new TableColumn<>("Monto");
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));

        TableColumn<Movimiento, TipoMovimiento> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));


        TableColumn<Movimiento, Double> colSaldo = new TableColumn<>("Saldo");
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));


        TableColumn<Movimiento, Void> colOpciones = new TableColumn<>("Opciones");
        colOpciones.setCellFactory(param -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnBorrar = new Button("Borrar");

            {
                btnEditar.setOnAction(event -> {
                    Movimiento mov = getTableView().getItems().get(getIndex());
                    editarMovimiento(mov);
                });

                btnBorrar.setOnAction(event -> {
                    Movimiento mov = getTableView().getItems().get(getIndex());
                    borrarMovimiento(mov);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(5, btnEditar, btnBorrar);
                    setGraphic(box);
                }
            }
        });


        tablaMovimientos.getColumns().addAll(colDescripcion, colMonto, colTipo, colSaldo, colOpciones);

        tablaMovimientos.setItems(service.listarMovimientos());
        actualizarSaldo();
    }

    @FXML
    private void onGuardar(ActionEvent event) {
        String desc = descripcionField.getText().trim();
        String montoTxt = montoField.getText().trim();
        if (desc.isEmpty() || montoTxt.isEmpty()) {
            showAlert("Faltan datos", "Complete descripción y monto.");
            return;
        }
        double monto;
        try {
            monto = Double.parseDouble(montoTxt);
        } catch (NumberFormatException ex) {
            showAlert("Monto inválido", "Ingrese un número válido para monto.");
            return;
        }

        if (movimientoEnEdicion == null) {

            Movimiento m = new Movimiento(desc, monto, tipoCombo.getValue());
            service.registrarMovimiento(m);

        } else {

            movimientoEnEdicion.setDescripcion(desc);
            movimientoEnEdicion.setMonto(monto);
            movimientoEnEdicion.setTipo(tipoCombo.getValue());
            tablaMovimientos.refresh();
            movimientoEnEdicion = null;
        }

        actualizarSaldo();
        clearFields();
    }
    private void actualizarSaldo() {
        double balance = service.calcularBalance();
        saldoLabel.setText(String.format("Saldo: %.2f", balance));
    }
    private void editarMovimiento(Movimiento mov) {
        descripcionField.setText(mov.getDescripcion());
        montoField.setText(String.valueOf(mov.getMonto()));
        tipoCombo.setValue(mov.getTipo());

        movimientoEnEdicion = mov;
    }

    private void borrarMovimiento(Movimiento mov) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Seguro que deseas eliminar este movimiento?",
                ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                service.eliminarMovimiento(mov);
            }
        });
    }
    private void showError(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText(title);
        a.showAndWait();
    }
    @FXML
    private void onReporte(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reporte.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();


            pe.edu.upeu.asistencia.control.ReporteController reporteCtrl = loader.getController();
            reporteCtrl.cargarDatos();


            pe.edu.upeu.asistencia.control.MainguiController maingui = context.getBean(pe.edu.upeu.asistencia.control.MainguiController.class);


            for (Tab t : maingui.tabPane.getTabs()) {
                if ("Reporte".equals(t.getText())) {
                    maingui.tabPane.getSelectionModel().select(t);

                    reporteCtrl.cargarDatos();
                    return;
                }
            }

            ScrollPane scroll = new ScrollPane(root);
            scroll.setFitToWidth(true);
            scroll.setFitToHeight(true);
            Tab newTab = new Tab("Reporte", scroll);
            newTab.setUserData(reporteCtrl);
            maingui.tabPane.getTabs().add(newTab);
            maingui.tabPane.getSelectionModel().select(newTab);

        } catch (Exception e) {
            e.printStackTrace();
            showError("Error", "No se pudo generar la pestaña de reporte.");
        }

    }

    private void clearFields() {
        descripcionField.clear();
        montoField.clear();
        tipoCombo.getSelectionModel().selectFirst();
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        a.setHeaderText(title);
        a.showAndWait();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setHeaderText(title);
        a.showAndWait();
    }
}
