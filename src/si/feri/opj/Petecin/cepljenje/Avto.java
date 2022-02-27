package si.feri.opj.Petecin.cepljenje;

public class Avto extends PrevoznoSredstvo {
    private TipCepiva tipcepiva;

    public Avto(String registrskaStevilka){
        super(registrskaStevilka);
    }

    public Avto(String naziv, int kapaciteta, TipCepiva tipcepiva){
        super(naziv,kapaciteta);
        this.tipcepiva = tipcepiva;
        this.cepiva = new Cepivo[vrniKapaciteto()];
    }

    public Avto(String naziv, String registrskaStevilka, String znamka, int kapaciteta, TipCepiva tipcepiva) {
        super(naziv, registrskaStevilka, znamka, kapaciteta);
        this.tipcepiva = tipcepiva;
    }

    public boolean tipcepivaavto () throws TipCepivaException{
        for(int i=0; i < this.cepiva.length; i++) {
            if (this.cepiva[i] != null && this.cepiva[i].getTipcepiva() == this.tipcepiva) {
                return true;
            } else {
                throw new TipCepivaException("avto ne sprejema tega tipa cepiv");
            }
        }
        return true;
    }


    @Override
    public int vrniKapaciteto() {
        return this.getKapaciteta();
    }

    @Override
    public String toString() {
        return super.toString() +
                "Avto{" +
                "tipcepiva=" + tipcepiva +
                '}';
    }
}


