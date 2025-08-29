package pe.edu.upeu.modelo;

public class Persona {
    private String nombre;
    private int edad;
    private double talla;

    public Persona() {
    }

    public Persona(String nombre, int edad, double talla) {
        this.nombre = nombre;
        this.edad = edad;
        this.talla = talla;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
