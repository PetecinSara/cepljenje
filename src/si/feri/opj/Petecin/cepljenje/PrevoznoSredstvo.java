package si.feri.opj.Petecin.cepljenje;

import java.io.Serializable;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

public abstract class PrevoznoSredstvo implements Serializable {
    private String registrskaStevilka;
    private String znamka;
    protected transient Cepivo[] cepiva = new Cepivo[10];
    private String naziv;
    private int kapaciteta;

    public PrevoznoSredstvo(){}
    public PrevoznoSredstvo(String registrskaStevilka){
        this.registrskaStevilka = registrskaStevilka;
    }
    public PrevoznoSredstvo(String naziv, int kapaciteta) {
        this.naziv = naziv;
        if (kapaciteta > 0) {
            this.kapaciteta = kapaciteta;
        } else {
            throw new IllegalArgumentException("Stevilo je negativno ali 0.");
        }
    }


    public PrevoznoSredstvo(String naziv, String registrskaStevilka, String znamka, int kapaciteta) {
        this.naziv = naziv;
        this.registrskaStevilka = registrskaStevilka;
        this.znamka = znamka;
        this.kapaciteta = kapaciteta;
    }
    //v prevozno sredstvo natovori cepivo;
    public void natovoriCepivo(Cepivo cepivo){
        for(int i=0;i<cepiva.length;i++) {
            if (this.cepiva[i] == null) {
                this.cepiva[i] = cepivo;
                break;
            }
        }
    }

    //iz prevoznega sredstva raztovori vsa cepiva
    public void raztovoriCepiva(){
        for(int i=0;i<cepiva.length;i++) {
            if (this.cepiva[i] != null)
                this.cepiva[i] = null;
        }
    }

    //vrne odstotek zasedenega prevoznega sredstva
    public double vrniZasedenost(){
        int stevec = 0;
        for(int i=0; i < this.cepiva.length; i++) {
            if (this.cepiva[i] != null) {
                stevec += 1;
            }
        }return (stevec * 100) / this.vrniKapaciteto();
    }

    //vrne kapaciteto prevoznega sredstva
    public abstract int vrniKapaciteto();

    public void setKapaciteta(int kapaciteta) {
        this.kapaciteta = kapaciteta;
    }

    public int getKapaciteta() {
        return kapaciteta;
    }

    public void setZnamka(String znamka) {
        this.znamka = znamka;
    }

    public void setRegistrskaStevilka(String registrskaStevilka) {
        this.registrskaStevilka = registrskaStevilka;
    }

    @Override
    public String toString() {
        return "PrevoznoSredstvo{" +
                "registrskaStevilka='" + registrskaStevilka + '\'' +
                ", znamka='" + znamka + '\'' +
                ", naziv='" + naziv + '\'' +
                ", kapaciteta=" + kapaciteta +
                '}';
    }

    public void natovoriCepivo(Cepivo[] cepiva) {
        this.cepiva = cepiva;
    }
}
