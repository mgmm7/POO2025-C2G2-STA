package pe.edu.upeu.conceptopoo.enums;
enum GENERO {
    Masculino,
    Femenino
}
enum NACIONALIDAD {Peruano, Venezolano, Boliviano}

public class Persona {
    static String nombre;

    static GENERO g = GENERO.Masculino;
    static NACIONALIDAD n = NACIONALIDAD.Peruano;

    public static void main(String[] args) {
        nombre="maykol";
        // Recorriendo los valores del enum
        for (GENERO g : GENERO.values()) {
            System.out.println(g);
        }
        for (NACIONALIDAD n : NACIONALIDAD.values()) {
            System.out.println(n);
        }
        System.out.println("mi nombre es "+ nombre + " y mi nacionalidad es "+ n+" y mi genero es "+ g);
    }
}
