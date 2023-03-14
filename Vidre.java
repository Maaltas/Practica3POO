package Sessio1;

public class Vidre extends ContenidorBrossa{
    private long reciclat; // nombre
    public Vidre (String codi, String ubicacio,int anyColocacio, float tara){
        super(codi,ContenidorBrossa.Verd,ubicacio,anyColocacio,tara);
        reciclat=0;
    }

    public String getReciclat(){
        return reciclat + " ampolles";
    }
    public String toString(){
        return super.toString() + getReciclat();
    }
    public void buidat(float pes){
        reciclat = Math.round(pes)* 3L;
    }
    public int compareTo(ContenidorBrossa o) {
        if (o != null){
            return super.compareTO(o);
        } else {
            return -1;
        }
    }

}
