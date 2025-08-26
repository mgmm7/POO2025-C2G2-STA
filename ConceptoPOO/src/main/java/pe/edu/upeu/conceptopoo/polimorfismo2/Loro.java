package pe.edu.upeu.conceptopoo.polimorfismo2;

public class Loro extends Animal {
    @Override
    public void animalSoun() {
        System.out.println("Hola manito");

    }
    @Override
    public void sleep() {
        System.out.println("Estoy durmiendo");
    }
}
