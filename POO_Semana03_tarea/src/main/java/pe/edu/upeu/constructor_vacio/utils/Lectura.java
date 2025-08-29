package pe.edu.upeu.constructor_vacio.utils;

import java.util.Scanner;

public class Lectura {
    private static final Scanner sc = new Scanner(System.in);

    public static String leerCadena(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public static int leerEntero(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine());
    }

    public static double leerDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(sc.nextLine());
    }
}
