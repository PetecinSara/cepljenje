package si.feri.opj.Petecin.cepljenje;

import java.io.Serializable;
import java.time.LocalDate;

public class Cepivo implements Serializable {
    private int serijskaStevilka;
    private LocalDate rokUporabe;
    private boolean vecOdmerkov;
    private TipCepiva tipcepiva;

    public LocalDate getRokUporabe() {
        return rokUporabe;
    }

    public int getSerijskaStevilka() {
        return serijskaStevilka;
    }

    public void setVecOdmerkov(boolean vecOdmerkov) {
        this.vecOdmerkov = vecOdmerkov;
    }

    public TipCepiva getTipcepiva() {
        return tipcepiva;
    }



    private Cepivo (){}

    public Cepivo(int serijskaStevilka, LocalDate rokUporabe, TipCepiva tipcepiva){
        this.serijskaStevilka = serijskaStevilka;
        this.rokUporabe = rokUporabe;
        this.tipcepiva = tipcepiva;
    }

    public Cepivo(int serijskaStevilka, LocalDate rokUporabe, boolean vecOdmerkov, TipCepiva tipcepiva) {
        super();
        this.serijskaStevilka = serijskaStevilka;
        this.rokUporabe = rokUporabe;
        this.vecOdmerkov = vecOdmerkov;
        this.tipcepiva = tipcepiva;
    }

    public boolean isVecOdmerkov() {
        return vecOdmerkov;
    }

    public void setTipcepiva(TipCepiva tipcepiva) {
        this.tipcepiva = tipcepiva;
    }

    @Override
    public String toString() {
        return "Cepivo{" +
                "serijskaStevilka=" + serijskaStevilka +
                ", rokUporabe=" + rokUporabe +
                ", vecOdmerkov=" + vecOdmerkov +
                ", tipcepiva=" + tipcepiva +
                '}';
    }
}
