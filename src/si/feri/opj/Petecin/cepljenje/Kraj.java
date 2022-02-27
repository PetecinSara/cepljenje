package si.feri.opj.Petecin.cepljenje;

import java.io.Serializable;

public class Kraj implements Serializable {
    private String kraj;
    private String drzava;

    public String getKraj() {
        return kraj;
    }

    public Kraj(){
    }

    public Kraj(String kraj) {
        this.kraj = kraj;
    }

    public Kraj(String kraj, String drzava) {
        this.kraj = kraj;
        this.drzava = drzava;
    }



    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    @Override
    public String
    toString() {
        return "Kraj{" +
                "kraj='" + kraj + '\'' +
                ", drzava='" + drzava + '\'' +
                '}';
    }
}
