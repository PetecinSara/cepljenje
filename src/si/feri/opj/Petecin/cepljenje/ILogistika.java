package si.feri.opj.Petecin.cepljenje;

public interface ILogistika {
    public boolean prevzamiCepiva(PrevoznoSredstvo prevoznoSredstvo) throws KapacitetaPresezenaException, TipCepivaException;
}