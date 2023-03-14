package Sessio1;

public class Plàstic extends ContenidorBrossa{
    private float reciclat; // kg
    public Plàstic (String codi, String ubicacio,int anyColocacio, float tara){
        super(codi,ContenidorBrossa.Groc,ubicacio,anyColocacio,tara);
        reciclat=0;
    }
    public String getReciclat(){
        return reciclat + " kg";
    }
    public String toString(){
        return super.toString() + getReciclat();
    }
    public void buidat(float pes) {
        reciclat=pes*(80/100);
    }
    public int compareTo(ContenidorBrossa o) {
        if (o != null){
            return super.compareTO(o);
        } else {
            return -1;
        }
    }
}
