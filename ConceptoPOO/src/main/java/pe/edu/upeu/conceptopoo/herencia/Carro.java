package pe.edu.upeu.conceptopoo.herencia;

public class Carro extends Vehiculo {
    String color = "negro";
    String modelo = "solo se que es un carro";
    void caracteristicas(){
         marca = "china";
        System.out.println( "La marca de este vehiculo es "+marca);
        System.out.println( "El color es "+color);
        System.out.println( "el modelo es "+modelo);
        System.out.println( "Y el carro emite el sonido "+sonido());

    }
    public static void main(String[] args) {
        Carro carro = new Carro();
        carro.caracteristicas();
    }
}
