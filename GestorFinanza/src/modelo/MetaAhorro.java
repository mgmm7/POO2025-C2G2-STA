package modelo;

public class MetaAhorro {
    private String usuario;
    private double montoMeta;
    private double montoActual;

    public MetaAhorro() {}

    public MetaAhorro(String usuario, double montoMeta, double montoActual) {
        this.usuario = usuario;
        this.montoMeta = montoMeta;
        this.montoActual = montoActual;
    }

    public String getUsuario() { return usuario; }
    public double getMontoMeta() { return montoMeta; }
    public double getMontoActual() { return montoActual; }
    public void setMontoActual(double montoActual) { this.montoActual = montoActual; }

    public String toLine() {
        return usuario + "|" + montoMeta + "|" + montoActual;
    }

    public static MetaAhorro fromLine(String line) {
        try {
            String[] p = line.split("\\|");
            return new MetaAhorro(p[0], Double.parseDouble(p[1]), Double.parseDouble(p[2]));
        } catch (Exception e) { return null; }
    }
}
