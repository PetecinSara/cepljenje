package si.feri.opj.Petecin.cepljenje;

public class Kombi extends PrevoznoSredstvo {
    private boolean moznostRazsiritve;
    private TipCepiva tipcepiva;

    public Kombi(String registrskaStevilka){
        super(registrskaStevilka);
    }

    public Kombi(String naziv, int kapaciteta, boolean moznostRazsiritve){
        super(naziv,kapaciteta);
        this.moznostRazsiritve = moznostRazsiritve;
        this.cepiva = new Cepivo[vrniKapaciteto()];
    }

    public Kombi(String naziv, String registrskaStevilka, String znamka, int kapaciteta, TipCepiva tipcepiva, boolean moznostRazsiritve) {
        super(naziv, registrskaStevilka, znamka, kapaciteta);
        this.tipcepiva = tipcepiva;
        this.moznostRazsiritve = moznostRazsiritve;
    }

    public boolean isMoznostRazsiritve() {
        return moznostRazsiritve;
    }

    public void setMoznostRazsiritve(boolean moznostRazsiritve) {
        this.moznostRazsiritve = moznostRazsiritve;
    }

    @Override
    public int vrniKapaciteto() {
        if (this.isMoznostRazsiritve()) {
            return 2 * super.getKapaciteta();
        } else {
            return super.getKapaciteta();
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "Kombi [moznostRazsiritve=" + moznostRazsiritve +
                ", tipcepiva=" + tipcepiva + "]";
    }


}
