package com.finanzas;

import org.springframework.stereotype.Service;

@Service
public class GestorFinanzasService {
    private double saldo = 0;

    public String registrarIngreso(double monto) {
        saldo += monto;
        return "Ingreso registrado: +" + monto + " | Saldo: " + saldo;
    }

    public String registrarGasto(double monto) {
        saldo -= monto;
        return "Gasto registrado: -" + monto + " | Saldo: " + saldo;
    }

    public String generarReporte() {
        return "Reporte actual -> Saldo: " + saldo;
    }
}
