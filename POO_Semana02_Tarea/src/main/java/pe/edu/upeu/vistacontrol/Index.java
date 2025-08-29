package pe.edu.upeu.vistacontrol;

import pe.edu.upeu.modelo.Municipalidad;
import pe.edu.upeu.utils.Lectura;

public class Index {
    public static void main(String[] args) {
        Municipalidad muni = new Municipalidad();

        // Ingresar datos
        muni.setNombre(Lectura.leerCadena("Ingrese nombre de la municipalidad: "));
        muni.setDireccion(Lectura.leerCadena("Ingrese dirección: "));
        muni.setDistrito(Lectura.leerCadena("Ingrese distrito: "));
        muni.setProvincia(Lectura.leerCadena("Ingrese provincia: "));
        muni.setRegion(Lectura.leerCadena("Ingrese región: "));
        muni.setNumEmpleados(Lectura.leerEntero("Ingrese número de empleados: "));
        muni.setPresupuesto(Lectura.leerDouble("Ingrese presupuesto: "));

        // Mostrar datos
        System.out.println("\n--- Datos de la Municipalidad ---");
        System.out.println("Nombre: " + muni.getNombre());
        System.out.println("Dirección: " + muni.getDireccion());
        System.out.println("Distrito: " + muni.getDistrito());
        System.out.println("Provincia: " + muni.getProvincia());
        System.out.println("Región: " + muni.getRegion());
        System.out.println("N° Empleados: " + muni.getNumEmpleados());
        System.out.println("Presupuesto: " + muni.getPresupuesto());
    }
}
