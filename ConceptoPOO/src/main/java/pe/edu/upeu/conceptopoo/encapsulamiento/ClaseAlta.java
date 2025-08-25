package pe.edu.upeu.conceptopoo.encapsulamiento;

public class ClaseAlta {
    public static void main(String[] args) {
        Persona persona = new Persona();
        persona.nombre = "Maykol";
        persona.edad = 17;
        persona.saludar();
        persona.setNombre("maykol");
        persona.setEdad(17);
        persona.saludar();

    }
}
