package pe.edu.upeu.vistacontrol;


import pe.edu.upeu.modelo.Trabajador;
import pe.edu.upeu.utils.Lectura;
import pe.edu.upeu.utils.Utiliarios;
import java.util.ArrayList;

public class IndexTrabajador {
    private static ArrayList<Trabajador> trabajadores = new ArrayList<>();
    private static Lectura leer = new Lectura();
    public static void datosdeinstalacion(){
        Trabajador t1 = new Trabajador(1200, "Ventas", "Vendedor", 10, "Juan", "12345678");
        trabajadores.add(t1);
        Trabajador t2 = new Trabajador(4000, "Desarrollador", "Sistemas", 15, "Pedro", "22222222");
        trabajadores.add(t2);
        Trabajador t3 = new Trabajador(7000, "Arquitecto de Software", "Sistemas", 16, "Manuel", "33322233");
        trabajadores.add(t3);
        Trabajador t4 = new Trabajador(4500, "Analista", "Sistemas", 7, "Maria", "99999888");
        trabajadores.add(t4);
        Trabajador t5 = new Trabajador(2200, "Cajero", "Ventas", 12, "Andres", "55512155");
        trabajadores.add(t5);
    }
    public static void agregar(){
        System.out.println("Agregar Trabajador");
        System.out.print("> ID: ");
        int id = leer.entero();
        System.out.print("> Nombre: ");
        String nombre = leer.cadena();
        System.out.print("> DNI: ");
        String dni = leer.cadena();
        System.out.print("> Salario: ");
        double salario = leer.decimal();
        System.out.print("> Cargo: ");
        String cargo = leer.cadena();
        System.out.print("> Area de Trabajo: ");
        String area_trabajo = leer.cadena();
        Trabajador trabajador = new Trabajador(salario, area_trabajo, cargo, id, nombre, dni);
        trabajadores.add(trabajador);
        System.out.println("<Se agregó un nuevo trabajador>");
    }
    public static void eliminar(){
        listar();
        System.out.print("Indique la posición del trabajador a eliminar: ");
        int posicion = leer.entero();
        posicion--;
        trabajadores.remove(posicion);
        System.out.println("<Se eliminó correctamente>");
    }
    public static void editar(){
        listar();
        System.out.print("Indique la posición del trabajador a editar: ");
        int posicion = leer.entero();
        posicion--;
        System.out.println("* Nombre actual: " + trabajadores.get(posicion).getNombre());
        System.out.print("> Nuevo nombre: ");
        trabajadores.get(posicion).setNombre(leer.cadena());

        System.out.println("* DNI actual: " + trabajadores.get(posicion).getDni());
        System.out.print("> Nuevo DNI: ");
        trabajadores.get(posicion).setDni(leer.cadena());

        System.out.println("* Area de Trabajo actual: " + trabajadores.get(posicion).getArea_trabajo());
        System.out.print("> Nueva Area de Trabajo: ");
        trabajadores.get(posicion).setArea_trabajo(leer.cadena());

        System.out.println("* Cargo actual: " + trabajadores.get(posicion).getCargo());
        System.out.print("> Nuevo Cargo: ");
        trabajadores.get(posicion).setCargo(leer.cadena());

        System.out.println("* Salario actual: " + trabajadores.get(posicion).getSalario());
        System.out.print("> Nuevo Salario: ");
        trabajadores.get(posicion).setSalario(leer.decimal());

        System.out.println("<Se editó correctamente los datos>");
    }
    public static void listar(){
        int numero = 0;
        for (Trabajador trabajador : trabajadores) {
            numero++;
            Utiliarios.lineasenblanco(1);
            System.out.println("Trabajador Nro " + numero);
            System.out.println("* ID: " + trabajador.getIdpersona());
            System.out.println("* Nombre: " + trabajador.getNombre());
            System.out.println("* DNI: " + trabajador.getDni());
            System.out.println("* Area de Trabajo: " + trabajador.getArea_trabajo());
            System.out.println("* Cargo: " + trabajador.getCargo());
            System.out.println("* Salario: " + trabajador.getSalario());
            System.out.println("****************************************");
        }
    }
    public static void buscar(){
        // Esto debe ser implementado por el estudiante
    }
    public static void menu(){
        System.out.print("""
                         DATOS TRABAJADOR
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
