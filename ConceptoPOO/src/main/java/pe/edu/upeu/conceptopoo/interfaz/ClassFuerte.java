package pe.edu.upeu.conceptopoo.interfaz;

public class ClassFuerte {
    public static void main(String[] args) {
        Animal animal = new Loro();
        animal.emitirSonido();
        animal.dormir();

        animal=new GAto();
        animal.emitirSonido();
        animal.dormir();
    }
}
