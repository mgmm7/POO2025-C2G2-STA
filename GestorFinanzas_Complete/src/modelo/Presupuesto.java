package modelo;

public class Presupuesto {
    private String usuario;
    private String categoria;
    private double limite;
    private double gastado;


    public Presupuesto() {}
    public Presupuesto(String usuario, String categoria, double limite, double gastado) {
        this.usuario = usuario; this.categoria = categoria; this.limite = limite; this.gastado = gastado;
    }
    public String getUsuario() { return usuario; }
    public String getCategoria() { return categoria; }
    public double getLimite() { return limite; }
    public double getGastado() { return gastado; }
    public void setGastado(double gastado) { this.gastado = gastado; }

    public String toCSV() {
        return usuario + "," + categoria + "," + limite + "," + gastado;
    }

    public static Presupuesto fromCSV(String line) {
        try {
            String[] p = line.split(",");
            return new Presupuesto(p[0], p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
        } catch (Exception e) { return null; }
    }
}
