package pe.edu.upeu.conceptopoo.encapsulamiento;

public class Persona {
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

    String nombre;
    int edad;
    public void saludar(){
        System.out.println("hola, soy "+ nombre + " y mi edad es "+ edad);
    }
}
