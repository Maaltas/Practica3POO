package Sessio1;

public class Organic extends ContenidorBrossa{
    private float reciclat; // tones

    public Organic (String codi, String ubicacio,int anyColocacio, float tara){
        super(codi,ContenidorBrossa.Marro,ubicacio,anyColocacio,tara);
        reciclat=0;
    }
    public String getReciclat(){
        return reciclat + " tones";
    }
    public String toString(){
        return super.toString() + getReciclat();
    }
    public void buidat(float pes) {
        pes=pes/1000;
        reciclat=pes*(90/100);
    }

    public int compareTo(ContenidorBrossa o) {
        if (o != null){
            return super.compareTO(o);
        } else {
            return -1;
        }
    }
}
