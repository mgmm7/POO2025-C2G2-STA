package pe.edu.upeu.modelo;

public class Trabajador extends Persona{
    private double salario;
    private String area_trabajo;
    private String cargo;

    public Trabajador() {
    }

    public Trabajador(int idpersona, String nombre, String dni) {
        super(idpersona, nombre, dni);
    }

    public Trabajador(double salario, String area_trabajo, String cargo, int idpersona, String nombre, String dni) {
        super(idpersona, nombre, dni);
        this.salario = salario;
        this.area_trabajo = area_trabajo;
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getArea_trabajo() {
        return area_trabajo;
    }

    public void setArea_trabajo(String area_trabajo) {
        this.area_trabajo = area_trabajo;
    }
}
