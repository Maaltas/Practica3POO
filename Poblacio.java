ackage Sessio1;
import java.util.*;
public class Poblacio {
    private String nom;
    private ContenidorBrossa[] contenidors;
    private int numContenidors;
    private final int increment;

    public Poblacio (String nom,int max,int increment){
        this.nom=nom;
        contenidors = new ContenidorBrossa[max];
        this.increment=increment;
    }
    public String getNom(){
        return nom;
    }
    public int getNumContenidors(){
        return numContenidors;
    }
    public ContenidorBrossa getContenidor(int quin){
        if (quin>=0 || quin<numContenidors){
            return contenidors[quin];
        } else {
            return null;
        }
    }
    public void afegirContenidor (ContenidorBrossa p){
        if (numContenidors<contenidors.length && !recurregutAgefir(p)){
            numContenidors=numContenidors+increment;
            int posicio=ordenacio(p);
            if (contenidors[posicio]==null){
                contenidors[posicio]=p;
            } else {
                ContenidorBrossa[] aux = new ContenidorBrossa[contenidors.length];
                Boolean trobat=false;
                for (int i=0; !trobat && i<numContenidors; i++){
                    if (i==posicio){
                        aux[posicio]=p;
                        trobat=true;
                    } else {
                        aux[i]=contenidors[i];
                    }
                }
                for (int i=posicio, j=posicio+1; j<numContenidors;i++,j++){
                    aux[j]=contenidors[i];
                }
                contenidors=aux;
            }
        }
    }
    public void afegirContenidor (String codi, int color, String ubicacio, int anyColocacio, float tara){
        switch (color){
            case ContenidorBrossa.Groc:
                Plàstic p = new Plàstic(codi,ubicacio,anyColocacio,tara);
                afegirContenidor(p);
                break;
            case ContenidorBrossa.Marro:
                Organic o = new Organic(codi,ubicacio,anyColocacio,tara);
                afegirContenidor(o);
                break;
            case ContenidorBrossa.Verd:
                Vidre v = new Vidre(codi,ubicacio,anyColocacio,tara);
                afegirContenidor(v);
                break;
            case ContenidorBrossa.Gris:
                Rebuig r = new Rebuig(codi,ubicacio,anyColocacio,tara);
                afegirContenidor(r);
                break;
            case ContenidorBrossa.Blau:
                Paper pa = new Paper(codi,ubicacio,anyColocacio,tara);
                afegirContenidor(pa);
                break;
        }
    }
    public String hiEs(String codi){
        String s="";
        for (int i=0; i<numContenidors; i++){
            s=contenidors[i].toString();
            if (s.contains(codi)){
                if (s.contains("Groc")){
                    return "Groc";
                } else if (s.contains("Marro")){
                    return "Marro";
                } else if (s.contains("Gris")){
                    return "Gris";
                } else if (s.contains("Blau")){
                    return "Blau";
                } else if (s.contains("Verd")){
                    return "Verd";
                }
            }
        }
        return "No hi es";
    }

    public String numContenidorsPerUbicacio(){
        int quantes = 0, poisicio;
        String[] adresa = new String[numContenidors];
        int[] quants = new int[numContenidors];
        for (int i = 0; i<numContenidors; i++){
            if (!contenidors[i].getUbicacio().equals("Ajuntament")){
                quantes++;
                adresa[i]=contenidors[i].getUbicacio();
                poisicio = trobar(contenidors[i].getUbicacio(),adresa,quantes);
                if (poisicio>=0 && comprovacioCaracters(adresa,adresa[i])){
                    quants[poisicio]++;
                }
            }
        }
        return crear(adresa,quants,quantes);
    }
    private boolean comprovacioCaracters(String [] a, String adresa){
        for (int i = 0; i<a.length; i++){
            if (a[i]==null || a[i].equals(adresa)){
                return true;
            }
        }
        return false;
    }
    public void eliminarContenidor(ContenidorBrossa c){
        int posicio = recurregutEliminar(c);
        if (posicio>=0){
            numContenidors--;
            contenidors[posicio]=null;
            int i=posicio, j=posicio+1;
            while (j<=numContenidors){
                contenidors[i]=contenidors[j];
                i++;
                j++;
            }
        }
    }
    private static int trobar (String carrer, String[] adresa,int quantes){
        for (int i=0; i < quantes; i++){
            if (adresa[i].equals(carrer)){
                return i;
            }
        }
        return -1;
    }
    private static String crear (String[] adresa, int [] quants, int quantes){
        String s="";
        for (int i=0; i<quantes; i++){
            if (quants[i]==0){
                continue;
            }
            s=s+" - Al carrer " + adresa[i] + " hi ha " + quants[i] + " contenidors";
        }
        return s;
    }
    public ContenidorBrossa mesRendiment(int tipus){
        ContenidorBrossa c = null;
        if (tipus>=ContenidorBrossa.Groc && tipus<=ContenidorBrossa.Blau){
            String a = "";
            switch (tipus){
                case ContenidorBrossa.Groc:
                    a="Plastic";
                    break;
                case ContenidorBrossa.Marro:
                    a="Organica";
                    break;
                case ContenidorBrossa.Verd:
                    a="Vidre";
                    break;
                case ContenidorBrossa.Gris:
                    a="Resta";
                    break;
                case ContenidorBrossa.Blau:
                    a="Paper";
                    break;
            }
            int posicio=0;
            float maxReciclat = 0, temp = 0;
            String s="";
            for (int i=0; i<numContenidors; i++){
                if (contenidors[i].getTipusBrossa().equals(a)){
                    switch (tipus){
                        case ContenidorBrossa.Marro:
                            s = ((Organic)contenidors[i]).getReciclat();
                            break;
                        case ContenidorBrossa.Verd:
                            s = ((Vidre)contenidors[i]).getReciclat();
                            break;
                        case ContenidorBrossa.Gris:
                            s = ((Rebuig)contenidors[i]).getReciclat();
                            break;
                        case ContenidorBrossa.Blau:
                            s = ((Paper)contenidors[i]).getReciclat();
                            break;
                        default:
                        case ContenidorBrossa.Groc:
                            s = ((Plàstic)contenidors[i]).getReciclat();
                            break;
                    }
                    posicio = s.indexOf(" ");
                    s = s.substring(0,posicio);
                    temp = Float.parseFloat(s);
                    if (temp>maxReciclat || maxReciclat==0){
                        maxReciclat = temp;
                        c = contenidors[i];
                    }
                }
            }
        }
        return c;
    }

    public boolean equals(Poblacio p) {
        if (p!=null){
            String[] poblacio1 = p.numContenidorsPerUbicacio().split("-");
            String[] poblacio2 = this.numContenidorsPerUbicacio().split("-");
            Arrays.sort(poblacio1);
            Arrays.sort(poblacio2);
            String a = Arrays.toString(poblacio1);
            String b = Arrays.toString(poblacio2);
            if (a.equals(b)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String s="";
        for (int i=0; i<numContenidors; i++){
            s=s + contenidors[i].toString() + " - ";
        }
        return s;
    }
    private int ordenacio(ContenidorBrossa c){ // funció que retorna la posicio on hauria d'anar el contenidor enviat per parametre tenint en compte l'ordre
        if (contenidors[0]==null){
            return 0;
        } else {
            for (int i=0; i<numContenidors; i++){
                if (contenidors[i]==null || contenidors[i].compareTO(c)>0){
                    return i;
                }
            }
        }
        return 0;
    }
    private boolean recurregutAgefir(ContenidorBrossa c){ // funcio que comprova si el contenidor esta o no al vector
        for (int i=0; i<numContenidors; i++){
            if (contenidors[i].equals(c)){
                return true;
            }
        }
        return false; // no hi es
    }
    private int recurregutEliminar (ContenidorBrossa c){ // funciona que retorna la posicio del contenidor si està al vector
        for (int i=0; i<numContenidors; i++){
            if (contenidors[i].equals(c)){
                return i;
            }
        }
        return -1; // no hi es
    }
}
