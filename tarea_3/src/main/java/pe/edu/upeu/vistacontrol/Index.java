package pe.edu.upeu.vistacontrol;

import pe.edu.upeu.modelo.Persona;

public class Index {
    public static void inicio(){
        Persona persona = new Persona("juanito", 13, 1.79);

        // Mostrar los valores del Objeto
        System.out.println("DATOS INGRESADOS");
        System.out.println("* Nombre: " + persona.getNombre());
        System.out.println("* Edad: " + persona.getEdad());
        System.out.println("* Talla: " + persona.getTalla());
    }
    public static void main(String[] args) {
        inicio();
    }
}
