package pe.edu.upeu.vistacontrol;

import pe.edu.upeu.modelo.Estudiante;
import pe.edu.upeu.utils.Lectura;
import pe.edu.upeu.utils.Utiliarios;

import java.net.URI;
import java.util.ArrayList;

public class IndexEstudiante {
    private static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static Lectura leer = new Lectura();
    public static void datosdeinstalacion(){
        Estudiante e1 = new Estudiante("20241001", "Ingenieria de Sistemas", 22, "Juan", "00000001");
        estudiantes.add(e1);
        Estudiante e2 = new Estudiante("202410002", "Medicina", 15, "Pedro", "01855222");
        estudiantes.add(e2);
        Estudiante e3 = new Estudiante("202410003", "Medicina", 2, "Lucas", "33355222");
        estudiantes.add(e3);
        Estudiante e4 = new Estudiante("202410004", "Contabilidad", 13, "Andres", "11155222");
        estudiantes.add(e4);
        Estudiante e5 = new Estudiante("202410005", "Contabilidad", 40, "Juan", "00000222");
        estudiantes.add(e5);
    }
    public static void agregar(){
        System.out.println("Agregar Estudiante");
        System.out.print("> ID: ");
        int id = leer.entero();
        System.out.print("> Nombre: ");
        String nombre = leer.cadena();
        System.out.print("> DNI: ");
        String dni = leer.cadena();
        System.out.print("> Codigo: ");
        String codigo = leer.cadena();
        System.out.print("> Carrera: ");
        String carrera = leer.cadena();
        Estudiante estudiante = new Estudiante(codigo, carrera, id, nombre, dni);
        estudiantes.add(estudiante);
        System.out.println("<Se agregó un nuevo estudiante>");
    }
    public static void eliminar(){
        listar();
        System.out.print("Indique la posición del estudiante a eliminar: ");
        int posicion = leer.entero();
        posicion--;
        estudiantes.remove(posicion);
        System.out.println("<Se eliminó correctamente>");
    }
    public static void editar(){
        listar();
        System.out.print("Indique la posición del estudiante a editar: ");
        int posicion = leer.entero();
        posicion--;
        System.out.println("* Nombre actual: " + estudiantes.get(posicion).getNombre());
        System.out.print("> Nuevo nombre: ");
        estudiantes.get(posicion).setNombre(leer.cadena());

        System.out.println("* DNI actual: " + estudiantes.get(posicion).getDni());
        System.out.print("> Nuevo DNI: ");
        estudiantes.get(posicion).setDni(leer.cadena());

        System.out.println("* Codigo actual: " + estudiantes.get(posicion).getCodigo());
        System.out.print("> Nuevo Codigo: ");
        estudiantes.get(posicion).setCodigo(leer.cadena());

        System.out.println("* Carrera actual: " + estudiantes.get(posicion).getCarrera());
        System.out.print("> Nueva Carrera: ");
        estudiantes.get(posicion).setCarrera(leer.cadena());

        System.out.println("<Se editó correctamente los datos>");
    }
    public static void listar(){
        int numero = 0;
        for (Estudiante estudiante : estudiantes) {
            numero++;
            Utiliarios.lineasenblanco(1);
            System.out.println("Estudiante Nro " + numero);
            System.out.println("* ID: " + estudiante.getIdpersona());
            System.out.println("* Nombre: " + estudiante.getNombre());
            System.out.println("* DNI: " + estudiante.getDni());
            System.out.println("* Codigo: " + estudiante.getCodigo());
            System.out.println("* Carrera: " + estudiante.getCarrera());
            System.out.println("****************************************");
        }
    }
    public static void buscar(){

    }
    public static void menu(){
        System.out.print("""
                         DATOS ESTUDIANTE
                         1. Agregar
                         2. Eliminar
                         3. Editar
                         4. Listar
                         5. Volver al menú principal
                         """);
        System.out.print("Seleccione una opción [1-5]: ");
    }

    public static void inicio(){
        int opcion;
        do {
            Utiliarios.lineasenblanco(3);
            menu();
            opcion = leer.entero();
            switch (opcion) {
                case 1 -> agregar();
                case 2 -> eliminar();
                case 3 -> editar();
                case 4 -> listar();
                case 5 -> Utiliarios.volver();
                default -> Utiliarios.error(1);
            }
        } while (opcion!=5);
    }
}
