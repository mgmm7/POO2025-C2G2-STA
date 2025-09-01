package pe.edu.upeu.asistencia.enums;

public enum CARRERA {
    Sistemas(FACULTAD.FIA),

    Civil(FACULTAD.FIA),

    Arquitectura(FACULTAD.FIA),

    Educacion(FACULTAD.FACIHED),

    Contabilidades(FACULTAD.FCE),

    Nutricion(FACULTAD.FCS),

    Internacional(FACULTAD.GENERAL);

    private FACULTAD facultad;
    CARRERA(FACULTAD facultad) {
        this.facultad = facultad;
    }
    public FACULTAD getFacultad() {return facultad;}
}
