package pe.edu.upeu.constructor_vacio.vistacontrol;

import pe.edu.upeu.constructor_vacio.modelo.Curso;
import pe.edu.upeu.constructor_vacio.utils.Lectura;

public class Index {
    public static void inicio(){
        String nombre = Lectura.leerCadena("Ingrese nombre del curso: ");
        String codigo = Lectura.leerCadena("Ingrese código: ");
        int creditos = Lectura.leerEntero("Ingrese créditos: ");
        int horas = Lectura.leerEntero("Ingrese horas: ");
        String docente = Lectura.leerCadena("Ingrese docente: ");

        Curso curso = new Curso(nombre, codigo, creditos, horas, docente);

        System.out.println("\nDATOS DEL CURSO");
        System.out.println("* Nombre: " + curso.getNombre());
        System.out.println("* Código: " + curso.getCodigo());
        System.out.println("* Créditos: " + curso.getCreditos());
        System.out.println("* Horas: " + curso.getHoras());
        System.out.println("* Docente: " + curso.getDocente());
    }
    public static void main(String[] args) {
        inicio();
    }
}
