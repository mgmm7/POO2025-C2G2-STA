package pe.edu.upeu.constructor_con_parametros.vistacontrol;

import pe.edu.upeu.constructor_con_parametros.modelo.Curso;
import pe.edu.upeu.constructor_con_parametros.utils.Lectura;

public class Index {
    public static void inicio(){
        Curso curso = new Curso();

        curso.setNombre(Lectura.leerCadena("Ingrese nombre del curso: "));
        curso.setCodigo(Lectura.leerCadena("Ingrese código: "));
        curso.setCreditos(Lectura.leerEntero("Ingrese créditos: "));
        curso.setHoras(Lectura.leerEntero("Ingrese horas: "));
        curso.setDocente(Lectura.leerCadena("Ingrese docente: "));

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
