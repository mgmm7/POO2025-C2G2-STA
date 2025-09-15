package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private int id;
    private String usuario;
    private LocalDateTime fecha;
    private double monto;
    private String categoria;
    private String descripcion;
    private String tipo; // INGRESO o GASTO

    public Transaccion() {}

    public Transaccion(int id, String usuario, LocalDateTime fecha, double monto, String categoria, String descripcion, String tipo) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.monto = monto;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getUsuario() { return usuario; }
    public LocalDateTime getFecha() { return fecha; }
    public double getMonto() { return monto; }
    public String getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    public String getTipo() { return tipo; }

    public String toCSV() {
        DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return id + "," + usuario + "," + fecha.format(f) + "," + tipo + "," + monto + "," + categoria + "," + (descripcion==null?"":descripcion.replace(',', ' '));
    }

    public static Transaccion fromCSV(String line) {
        try {
            String[] p = line.split(",", 7);
            if (p.length < 7) return null;
            int id = Integer.parseInt(p[0]);
            String usuario = p[1];
            LocalDateTime fecha = LocalDateTime.parse(p[2]);
            String tipo = p[3];
            double monto = Double.parseDouble(p[4]);
            String categoria = p[5];
            String descripcion = p[6];
            return new Transaccion(id, usuario, fecha, monto, categoria, descripcion, tipo);
        } catch (Exception e) {
            return null;
        }
    }
}
