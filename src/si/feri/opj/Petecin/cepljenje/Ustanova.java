package si.feri.opj.Petecin.cepljenje;

import java.io.Serializable;

public class Ustanova implements Serializable {
    protected Cepivo[] cepiva;
    private String naziv;
    private Kraj kraj;

    public Ustanova(){}

    public Ustanova(String naziv, Kraj kraj){
        this.naziv = naziv;
        this.kraj = kraj;
    }

    public Ustanova(String naziv){
        this.naziv = naziv;
    }



    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Kraj getKraj() {
        return kraj;
    }

    public void setKraj(Kraj kraj) {
        this.kraj = kraj;
    }

    @Override
    public String toString() {
        return "naziv='" + naziv + '\'' +
                ", kraj=" + kraj;
    }
}
