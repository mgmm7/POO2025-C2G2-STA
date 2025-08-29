package pe.edu.upeu.constructor_vacio.modelo;

public class Curso {
    private String nombre;
    private String codigo;
    private int creditos;
    private int horas;
    private String docente;

    public Curso(String nombre, String codigo, int creditos, int horas, String docente) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.horas = horas;
        this.docente = docente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getHoras() {
        return horas;
    }

    public String getDocente() {
        return docente;
    }
}
