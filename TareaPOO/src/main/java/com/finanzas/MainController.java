package com.finanzas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class MainController {

    @FXML
    private TextArea outputArea;

    @FXML
    private TextField montoField;

    @FXML
    private TextField descripcionField;

    @FXML
    private Button btnIngreso, btnGasto, btnReporte, btnPresupuesto, btnMetas;

    private final GestorFinanzasService service;

    public MainController(GestorFinanzasService service) {
        this.service = service;
    }

    @FXML
    private void initialize() {
        btnIngreso.setOnAction(e -> {
            try {
                double monto = Double.parseDouble(montoField.getText().trim());
                String desc = descripcionField.getText().trim();
                outputArea.appendText(service.registrarIngreso(monto, desc) + "\n");
                montoField.clear();
                descripcionField.clear();
            } catch (NumberFormatException ex) {
                outputArea.appendText("Error: ingrese un monto válido.\n");
            }
        });

        btnGasto.setOnAction(e -> {
            try {
                double monto = Double.parseDouble(montoField.getText().trim());
                String desc = descripcionField.getText().trim();
                outputArea.appendText(service.registrarGasto(monto, desc) + "\n");
                montoField.clear();
                descripcionField.clear();
            } catch (NumberFormatException ex) {
                outputArea.appendText("Error: ingrese un monto válido.\n");
            }
        });

        btnReporte.setOnAction(e -> outputArea.appendText(service.generarReporte() + "\n"));
        btnPresupuesto.setOnAction(e -> outputArea.appendText("Presupuesto pendiente...\n"));
        btnMetas.setOnAction(e -> outputArea.appendText("Metas pendientes...\n"));
    }
}
