package modelo;

public class Usuario {
    private String nombre;
    private String contrasena;
    private String carrera;
    private int edad;
    private double saldoGeneral = 0;

    public Usuario() {}

    public Usuario(String nombre, String contrasena, String carrera, int edad) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.carrera = carrera;
        this.edad = edad;
    }



    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public double getSaldoGeneral() { return saldoGeneral; }
    public void setSaldoGeneral(double saldoGeneral) { this.saldoGeneral = saldoGeneral; }

    @Override
    public String toString() {
        return nombre + "|" + contrasena + "|" + carrera + "|" + edad;
    }
}
