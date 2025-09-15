package app;

import servicio.GestorFinanzas;
import modelo.Usuario;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorFinanzas gestor = new GestorFinanzas();
        gestor.cargarDatos(); // load from files
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Gestor de Finanzas - Consola ===");
        while (true) {
            System.out.println("\n1) Registrarse\n2) Iniciar sesión\n3) Salir");
            System.out.print("Elige una opción: ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                System.out.print("Nombre de usuario: "); String nombre = sc.nextLine().trim();
                System.out.print("Contraseña: "); String pass = sc.nextLine().trim();
                System.out.print("Carrera: "); String carrera = sc.nextLine().trim();
                System.out.print("Edad: "); int edad = Integer.parseInt(sc.nextLine().trim());
                boolean ok = gestor.registrarUsuario(nombre, pass, carrera, edad);
                System.out.println(ok ? "Usuario registrado correctamente." : "Ya existe ese usuario.");
            } else if (opt.equals("2")) {
                System.out.print("Usuario: "); String usuario = sc.nextLine().trim();
                System.out.print("Contraseña: "); String pass = sc.nextLine().trim();
                Usuario u = gestor.login(usuario, pass);
                if (u == null) {
                    System.out.println("Login incorrecto."); 
                } else {
                    System.out.println("Bienvenido, " + u.getNombre());
                    gestor.menuUsuario(u, sc);
                }
            } else if (opt.equals("3")) {
                System.out.println("Guardando datos... hasta luego."); 
                gestor.guardarDatos();
                break;
            } else {
                System.out.println("Opción inválida."); 
            }
        }
        sc.close();
    }
}
