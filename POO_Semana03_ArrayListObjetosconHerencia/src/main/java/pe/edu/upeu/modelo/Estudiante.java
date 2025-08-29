package pe.edu.upeu.modelo;

public class Estudiante extends Persona{
    private String codigo;
    private String carrera;

    public Estudiante() {
    }

    public Estudiante(int idpersona, String nombre, String dni) {
        super(idpersona, nombre, dni);
    }

    public Estudiante(String codigo, String carrera, int idpersona, String nombre, String dni) {
        super(idpersona, nombre, dni);
        this.codigo = codigo;
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
