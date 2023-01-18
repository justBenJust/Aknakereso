package src;
//import java.io.*;
public class Rekordok implements java.io.Serializable {
    private int Kezdo;
    private int Kozepes;
    private int Halado;
    Rekordok(int a, int b, int c){
        Kezdo = a;
        Kozepes = b;
        Halado = c;
    }
    public int getKezdo(){
        return Kezdo;
    }
    public int getKozepes(){
        return Kozepes;
    }
    public int getHalado(){
        return Halado;
    }
    public void setKezdo(int i ){Kezdo = i;}
    public void setKozepes(int i ){Kozepes = i;}
    public void setHalado(int i ){Halado = i;}
    public void kiir(){
        System.out.println(Kezdo + " "+ Kozepes + " " + Halado);
    }

  
}