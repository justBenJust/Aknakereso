package src;

public class Mezo{
    Fedo Cover;
    int Ertek;
    Mezo(){
        Cover = Fedo.fedett;
        Ertek = 0;
    }
    public int getErtek(){return Ertek;}
    public Fedo getCover(){return Cover;}
    public void setErtek(int x){Ertek = x;}
    public void setCover(Fedo x){Cover = x;}
}
