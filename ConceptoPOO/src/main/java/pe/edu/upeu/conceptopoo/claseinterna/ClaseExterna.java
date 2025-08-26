package pe.edu.upeu.conceptopoo.claseinterna;

import ch.qos.logback.core.encoder.JsonEscapeUtil;

public class ClaseExterna {
    int a,b,c;
    int operation(){
      return  a+b+c;
    }
    class Claseinterna1{
        int r;
        void mensaje(){
            r=a+b+c;
            System.out.println("la suma es "+r);
        }
    }
    class Claseinterna2{
        int t;
        void mensaje(){
            t=a*b*c;
            System.out.println("la multiplicacion es "+t);
        }
    }
    class Claseinterna3{
        int z;
        void mensaje(){
            z=a/b/c;
            System.out.println("la division es "+z);
        }
    }
}
class ClaseexternaX{
    public static void main(String[] args) {
        ClaseExterna c1=new ClaseExterna();
        c1.a=8;
        c1.b=9;
        c1.c=10;
        ClaseExterna.Claseinterna1 ci1=c1.new Claseinterna1();
        ci1.mensaje();
        ClaseExterna.Claseinterna2 ci2=c1.new Claseinterna2();
        ci2.mensaje();
        ClaseExterna.Claseinterna3 ci3=c1.new Claseinterna3();
        ci3.mensaje();
    }
}
class ClaseexternaY{}
class ClaseinternaZ{}