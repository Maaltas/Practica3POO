package Sessio1;
import java.util.GregorianCalendar;
public abstract class ContenidorBrossa implements Comparable<ContenidorBrossa>{
    public final static int Groc = 0; // plastic
    public final static int Marro = 1; // organica
    public final static int Verd = 3; // vidre
    public final static int Gris = 4; // rebiug
    public final static int Blau = 5; // paper

    private String codi; // "XX-YYY" X= abc.., Y= 123...
    private int color;// G,M,V,G,B
    private String ubicacio; // si es null ubicacio = ajuntamnet
    private int anyColocacio;
    private int anyRetirada;
    protected float tara;
    public ContenidorBrossa(String codi, int color, String ubicacio, int anyColocacio, float tara) {
        this.codi = codi;
        inicialitzarColor(color);
        if (ubicacio == null) {
            this.ubicacio = "Ajuntament";
        }else{
            this.ubicacio = ubicacio;
        }
        this.anyColocacio = anyColocacio;
        this.tara=tara;
    }
    public ContenidorBrossa (String codi, int color, int anyColocacio, float tara){
        this(codi,color,"Ajuntament",anyColocacio,tara);
    }
    private void inicialitzarColor(int color){
        switch (color) {
            case ContenidorBrossa.Groc:
                this.color = ContenidorBrossa.Groc;
                break;
            case ContenidorBrossa.Marro:
                this.color = ContenidorBrossa.Marro;
                break;
            case ContenidorBrossa.Verd:
                this.color = ContenidorBrossa.Verd;
                break;
            case ContenidorBrossa.Gris:
                this.color = ContenidorBrossa.Gris;
                break;
            case ContenidorBrossa.Blau:
                this.color = ContenidorBrossa.Blau;
                break;
            default: this.color = -1;
        }
    }
    public String getTipusBrossa(){
        switch(color){
            case ContenidorBrossa.Groc:
                return "Plastic";
            case ContenidorBrossa.Marro:
                return "Organica";
            case ContenidorBrossa.Gris:
                return "Resta";
            case ContenidorBrossa.Blau:
                return "Paper";
            case ContenidorBrossa.Verd:
                return "Vidre";
            default:
                return "Error";
        }
    }
    public void retirarViaPublica(){
        if (ubicacio!=null || !ubicacio.equals("Ajuntament")){
            GregorianCalendar avui = new GregorianCalendar();
            int anyActual = avui.get(1);
            ubicacio = null;
            anyColocacio=0;
            anyRetirada = anyActual;
        }
    }
    public String getUbicacio(){
        return ubicacio;
    }
    public void setUbicacio (String ubicacio){
        if (ubicacio==null){
            retirarViaPublica();
        } else {
            this.ubicacio=ubicacio;
        }
    }
    public String getEstat(){
        if (ubicacio==null || getUbicacio().equals("Ajuntament")){
            return "retirat";
        }
        GregorianCalendar avui = new GregorianCalendar();
        int anyActual = avui.get(1);
        int comparacio = anyActual-anyColocacio;
        if (comparacio>=5){
            return "vell";
        } else if (comparacio>=3) {
            return "seminou";
        } else {
            return "nou";
        }
    }

    public int compareTO(ContenidorBrossa c){return this.codi.compareTo(c.codi);}
    public boolean equals(ContenidorBrossa c){
        if (c.compareTO(this)==0){
            return true;
        } else {
            return false;
        }
    }
    public String toString(){
        return "Contenidor amb codi: " + codi + ", ubicat a: " + ubicacio + ", de tipus: " + getTipusBrossa() + ", de color: " + tipusColor(color) + ", amb tara: " + tara + ", el seu estat actual es: " + getEstat();
    }
    private String tipusColor (int color){
        switch(color){
            case ContenidorBrossa.Groc:
                return "Groc";
            case ContenidorBrossa.Marro:
                return "Marro";
            case ContenidorBrossa.Gris:
                return "Gris";
            case ContenidorBrossa.Blau:
                return "Blau";
            case ContenidorBrossa.Verd:
                return "Verd";
            default:
                return null;
        }
    }
    public abstract void buidat (float pes);

}
