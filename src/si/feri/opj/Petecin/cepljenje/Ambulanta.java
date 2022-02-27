package si.feri.opj.Petecin.cepljenje;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Sara
 * @version 1.0
 */

public class Ambulanta extends Ustanova implements ILogistika, Serializable {
    private String telefon;

    /**
     *
     * @param telefon doda telefonsko dtevilko
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    private Ambulanta(){super();}

    private Ambulanta (String naziv, Kraj kraj){
        super(naziv,kraj);
    }

    /**
     *
     * @param naziv doda naziv ambulante
     * @param kraj doda kraj ambulante
     * @param telefon doda telefonsko stevilko ambulante
     */
    public Ambulanta (String naziv, Kraj kraj, String telefon){
        this(naziv, kraj);
        this.telefon = telefon;
    }


    /**
     *
     * @param cepivo doda cepivo
     * @return vrne ali je cepivo uporabno ali ne (ce je rok uporabe manjsi od 7, cepivo je uporabno)
     */
    public boolean cepivoUporabno(Cepivo cepivo) throws CepivoNeuporabnoException {
        LocalDate danes = LocalDate.now().plusDays(7);
        LocalDate datum = cepivo.getRokUporabe();
        long dni = ChronoUnit.DAYS.between(danes, datum);
        if (datum.isAfter(danes)) {
            return true;
        }
        throw new CepivoNeuporabnoException("cepivo je neuporabno");
    }

    public boolean prevzamiCepiva(PrevoznoSredstvo prevoznoSredstvo){
        if (prevoznoSredstvo instanceof Avto || prevoznoSredstvo instanceof Kombi){
            prevoznoSredstvo.raztovoriCepiva();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return " Ambulanta{" +
                super.toString() +
                "telefon='" + telefon + '\'' +
                '}';
    }
}
