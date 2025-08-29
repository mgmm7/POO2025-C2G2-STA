package pe.edu.upeu.vistacontrol;
import pe.edu.upeu.utils.Utiliarios;
import pe.edu.upeu.utils.Lectura;

public class indexprincipal {
    private static Lectura leer = new Lectura();
    public static void menu(){
        System.out.print("""
                           *** MENU PRINCIPAL ***
                           1. Trabajador
                           2. Estudiante
                           3. Salir
                           """);
        System.out.print("Seleccione opcion [1-3]: ");
    }
    public static void inicio(){
        int opcion;
        IndexTrabajador.datosdeinstalacion();
        IndexEstudiante.datosdeinstalacion();
        do {
            menu();
            opcion = leer.entero();
            switch (opcion) {
                case 1 -> IndexTrabajador.inicio();
                case 2 -> IndexEstudiante.inicio();
                case 3 -> Utiliarios.salir();
                default -> Utiliarios.error(1);
            }
        } while (opcion!=3);
    }
}
