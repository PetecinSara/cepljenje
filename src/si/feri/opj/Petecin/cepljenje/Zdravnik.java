package si.feri.opj.Petecin.cepljenje;

public class Zdravnik implements Runnable {

    private String ime;
    private String priimek;
    private CepilniCenter naziv;

    public Zdravnik(String ime, String priimek, CepilniCenter naziv) {
        this.ime = ime;
        this.priimek = priimek;
        this.naziv = naziv;
    }

    @Override
    public void run() {
        System.out.println(ime + " " + priimek + " je začel s cepljenjem.");

        do{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(ime + " " + priimek + " je prekinjen.");
                e.printStackTrace();
            }
        } while(naziv.pripraviCepivo(ime, priimek) != null);

        System.out.println(ime + " " + priimek + " je končal s cepljenjem.");
    }
}
