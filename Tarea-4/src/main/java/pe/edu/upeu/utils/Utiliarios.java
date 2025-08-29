package pe.edu.upeu.utils;

public class Utiliarios {
    public static void volver(){
        System.out.println("Regresando al MENÚ PRINCIPAL");
    }
    public static void salir(){
        System.out.println("Gracias por su visita");
    }
    public static void error(int tipo){
        System.out.print("Error: ");
        switch (tipo) {
            case 1 -> System.out.println("Opción inválida");
            default -> System.out.println("--");
        }
    }
    public static void lineasenblanco(int cantidad){
        for (int i = 1; i <= cantidad; i++) {
            System.out.println("");
        }
    }
}
