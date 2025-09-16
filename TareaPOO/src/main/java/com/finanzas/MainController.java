package com.finanzas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.springframework.stereotype.Component;

@Component
public class MainController {

    @FXML
    private TextArea outputArea;

    @FXML
    private Button btnIngreso, btnGasto, btnReporte, btnPresupuesto, btnMetas;

    private final GestorFinanzasService service;

    public MainController(GestorFinanzasService service) {
        this.service = service;
    }

    @FXML
    private void initialize() {
        btnIngreso.setOnAction(e -> outputArea.appendText(service.registrarIngreso(100) + "\n"));
        btnGasto.setOnAction(e -> outputArea.appendText(service.registrarGasto(50) + "\n"));
        btnReporte.setOnAction(e -> outputArea.appendText(service.generarReporte() + "\n"));
        btnPresupuesto.setOnAction(e -> outputArea.appendText("Presupuesto pendiente...\n"));
        btnMetas.setOnAction(e -> outputArea.appendText("Metas pendientes...\n"));
    }
}
