package Sessio1;

public class Paper extends ContenidorBrossa{
    private float reciclat; // kg

    public Paper (String codi, String ubicacio,int anyColocacio, float tara){
        super(codi,ContenidorBrossa.Blau,ubicacio,anyColocacio,tara);
        reciclat=0;
    }
    public String getReciclat(){
        return reciclat + " kg";
    }
    public String toString(){
        return super.toString() + ", y s'ha reciclat: " + getReciclat();
    }

    public void buidat(float pes) {
        reciclat=pes*(95/100);
    }
    public int compareTo(ContenidorBrossa o) {
        if (o != null){
            return super.compareTO(o);
        } else {
            return -1;
        }
    }

}
