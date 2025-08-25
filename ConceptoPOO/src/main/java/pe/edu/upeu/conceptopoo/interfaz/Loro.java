package pe.edu.upeu.conceptopoo.interfaz;

public class Loro implements Animal {
    @Override
    public void emitirSonido() {
        System.out.println("sonido");
    }

    @Override
    public void dormir() {
        System.out.println("zzzzzzwiggeta");
    }
}
