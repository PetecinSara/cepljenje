package si.feri.opj.Petecin.cepljenje;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Sara
 * @version 1.0
 */

public class CepilniCenter extends Ustanova implements ILogistika, Serializable {
    private String email;
    private Cepivo[] cepiva = new Cepivo[10];

    public void setEmail(String email) {
        this.email = email;
    }

    private CepilniCenter(){
        this.cepiva = new Cepivo[10];
    }

    private CepilniCenter(String naziv){
        super(naziv);
    }



    public void setCepiva(Cepivo[] cepiva) {
        this.cepiva = cepiva;
    }

    /**
     *
     * @param naziv  dodamo naziv cepilnega centra
     * @param kraj dodamo kraj cepilnega centra
     */
    private CepilniCenter(String naziv, Kraj kraj){
        super(naziv, kraj);
    }

    public CepilniCenter (String naziv, Kraj kraj, String email) {
        this(naziv, kraj);
        this.email = email;
    }

    public CepilniCenter (String naziv, Kraj kraj, String email, Cepivo[] cepiva){
        this(naziv, kraj, email);
        this.cepiva = new Cepivo[10];
    }


    /* METODE ZA POLJE CEPIV
       dodaj cepivo*/
    public boolean dodajCepivo (Cepivo cepivo){
        for(int i = 0; i < this.cepiva.length; i++){
            if(this.cepiva[i] == null){
                this.cepiva[i] = cepivo;
                return true;
            }
        }
        return false;
    }

    //odstrani cepivo
    public void odstraniCepivo(Cepivo cepivo){
        for(int i = 0; i < this.cepiva.length; i++){
            if(this.cepiva[i] != null && this.cepiva[i].equals(cepivo)){
                this.cepiva[i] = null;
                break;
            }
        }
    }

    //odstrani cepivo po serijskistevilki
    public boolean odstraniCepivo(int serijskaStevilka){
        for(int i = 0; i < this.cepiva.length; i++){
            if(this.cepiva[i] != null && (this.cepiva[i].getSerijskaStevilka() == serijskaStevilka)){
                this.cepiva[i] = null;
                return true;
            }
        }
        return false;
    }

    //poda Ĺˇtevilko - koliko cepiv je v tabeli
    public int vrniSteviloCepiv(){
        int stevec = 0;
        for(int i = 0; i < this.cepiva.length; i++){
            if(this.cepiva[i] != null){
                stevec += 1;
            }
        }
        return stevec;
    }

    //preverimo ali to cepivo obstaja (preko serijske stevilke
    public boolean cepivoObstaja (int serijskaStevilka){
        for(int i = 0; i < this.cepiva.length; i++){
            if(this.cepiva[i] != null && (this.cepiva[i].getSerijskaStevilka() == serijskaStevilka)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean prevzamiCepiva(PrevoznoSredstvo prevoznoSredstvo) throws KapacitetaPresezenaException, TipCepivaException {
        int stevec = 0;
        for (int i = 0; i < this.cepiva.length; i++) {
            if (this.cepiva[i] != null) {
                stevec += 1;
            }
        }
        if (stevec <= prevoznoSredstvo.getKapaciteta() && prevoznoSredstvo instanceof Avto && ((Avto) prevoznoSredstvo).tipcepivaavto()) {
            prevoznoSredstvo.natovoriCepivo(this.cepiva);

            return true;
        } else if (stevec <= prevoznoSredstvo.getKapaciteta() && prevoznoSredstvo instanceof Kombi) {
            for (int i = 0; i < this.cepiva.length; i++) {
                prevoznoSredstvo.natovoriCepivo(this.cepiva[i]);
            }
            return true;
        } else {
            throw new KapacitetaPresezenaException("kapaciteta vozila je presezena");
        }
    }

    public Cepivo pripraviCepivo (String ime, String priimek){
        synchronized (this){
            for(int i = 0; i < this.cepiva.length; i++){
                if(this.cepiva[i] != null){
                    System.out.println("Zdravnik " + ime + " " + priimek + " je opravil cepljenje s cepivom s serijsko stevilko " + cepiva[i].getSerijskaStevilka());
                    Cepivo c = cepiva[i];
                    this.cepiva[i] = null;
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return " CepilniCenter{" +
                super.toString() +
                "email='" + email + '\'' +
                ", cepiva=" + Arrays.toString(cepiva) +
                '}';
    }
}






























