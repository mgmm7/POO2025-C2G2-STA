package com.finanzas;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestorFinanzasService {
    private double saldo = 0;
    private final List<String> movimientos = new ArrayList<>();

    // Métodos antiguos (mantener compatibilidad)
    public String registrarIngreso(double monto) {
        return registrarIngreso(monto, "");
    }

    public String registrarGasto(double monto) {
        return registrarGasto(monto, "");
    }

    // Nuevos métodos con descripción
    public String registrarIngreso(double monto, String descripcion) {
        saldo += monto;
        String registro = "Ingreso: +" + monto + " | " + descripcion + " | Saldo: " + saldo;
        movimientos.add(registro);
        return registro;
    }

    public String registrarGasto(double monto, String descripcion) {
        saldo -= monto;
        String registro = "Gasto: -" + monto + " | " + descripcion + " | Saldo: " + saldo;
        movimientos.add(registro);
        return registro;
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE ===\n");
        for (String m : movimientos) {
            sb.append(m).append("\n");
        }
        sb.append("Saldo actual: ").append(saldo);
        return sb.toString();
    }
}