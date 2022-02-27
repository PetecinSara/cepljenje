package si.feri.opj.Petecin.zagonski;

import si.feri.opj.Petecin.cepljenje.*;

import java.time.LocalDate;

public class Zagonski {
    public static void main(String[] args) {
        //KRAJ
        Kraj ljk = new Kraj("Ljubljana", "Slovenija");
        //System.out.println(ljk.toString());
/*
        //AMBULANTA
        Ambulanta ljAmbulanta = new Ambulanta("Zdravstveni dom Ljubljana", ljk, "01 586 49 00");
        System.out.println(ljAmbulanta.toString());

        //CEPIVA
        Cepivo ljCepivo = new Cepivo(123456, LocalDate.of(2021, 3, 22), TipCepiva.PFIZER);
        ljCepivo.setVecOdmerkov(true);
        System.out.println(ljCepivo.toString());

        try {
            System.out.println(ljAmbulanta.cepivoUporabno(ljCepivo));
        } catch (CepivoNeuporabnoException e) {
            e.printStackTrace();
        }

*/
        //CEPILNI CENTER

        Cepivo a = new Cepivo(1, LocalDate.of(2021, 5, 5), TipCepiva.MODERNA);
        Cepivo b = new Cepivo(2, LocalDate.of(2021, 5, 19), TipCepiva.PFIZER);
        Cepivo c = new Cepivo(3, LocalDate.of(2021, 5, 6), TipCepiva.ASTRAZENECA);
        Cepivo d = new Cepivo(4, LocalDate.of(2021, 5, 15), TipCepiva.PFIZER);
        Cepivo e = new Cepivo(5, LocalDate.of(2021, 5, 25), TipCepiva.ASTRAZENECA);
        Cepivo f = new Cepivo(6, LocalDate.of(2021, 5, 16), TipCepiva.MODERNA);
        Cepivo g = new Cepivo(7, LocalDate.of(2021, 5, 1), TipCepiva.PFIZER);
        Cepivo h = new Cepivo(8, LocalDate.of(2021, 5, 9), TipCepiva.MODERNA);
        Cepivo i = new Cepivo(9, LocalDate.of(2021, 5, 4), TipCepiva.PFIZER);
        Cepivo j = new Cepivo(10, LocalDate.of(2021, 5, 20), TipCepiva.ASTRAZENECA);

        Cepivo[] cepiva = new Cepivo[10];
        CepilniCenter ljCepilniCenter = new CepilniCenter("Zdravstveni zavod Zdravje Ljubljana", ljk, "info@zzzdravje.si", cepiva);
        //System.out.println(ljCepilniCenter.toString());

        ljCepilniCenter.dodajCepivo(a);
        ljCepilniCenter.dodajCepivo(b);
        ljCepilniCenter.dodajCepivo(c);
        ljCepilniCenter.dodajCepivo(d);
        ljCepilniCenter.dodajCepivo(e);
        ljCepilniCenter.dodajCepivo(f);
        ljCepilniCenter.dodajCepivo(g);
        ljCepilniCenter.dodajCepivo(h);
        ljCepilniCenter.dodajCepivo(i);
        ljCepilniCenter.dodajCepivo(j);

        System.out.println(ljCepilniCenter.toString());


        Zdravnik mojca = new Zdravnik("Mojca", "Novak", ljCepilniCenter);
        Zdravnik janez = new Zdravnik("Janez", "Kovač", ljCepilniCenter);
        Zdravnik mateja = new Zdravnik("Mateja", "Leskovar", ljCepilniCenter);

        new Thread (mojca).start();
        new Thread(janez).start();
        new Thread(mateja).start();



 /*     ljCepilniCenter.dodajCepivo(a);
        ljCepilniCenter.dodajCepivo(d);

        ljCepilniCenter.dodajCepivo(f);
        ljCepilniCenter.odstraniCepivo(f);
        ljCepilniCenter.dodajCepivo(e);
        ljCepilniCenter.odstraniCepivo(234567);             //a

        System.out.println();
        System.out.println("Stevilo cepiv v polju: " + ljCepilniCenter.vrniSteviloCepiv());
        System.out.println("Ali cepivo s serijsko stevilko 456789 obstaja? " + ljCepilniCenter.cepivoObstaja(456789));
        System.out.println("Ali cepivo s serijsko stevilko 246977 obstaja? " + ljCepilniCenter.cepivoObstaja(246977));
        System.out.println();

       //PREVOZNO SREDSTVO-AVTO
        Avto volvo = new Avto("Avto-dostava", 1, TipCepiva.ASTRAZENECA);
        volvo.setZnamka("Volvo");
        volvo.setRegistrskaStevilka("LJ-9999");
        System.out.println("AVTO");
        System.out.println("Kapaciteta " + volvo.vrniKapaciteto());
        System.out.println("Odstotek zasedenosti " + volvo.vrniZasedenost());

        volvo.natovoriCepivo(a);
        volvo.natovoriCepivo(a);
        volvo.natovoriCepivo(a);

        System.out.println("Odstotek zasedenosti " + volvo.vrniZasedenost());
        System.out.println(volvo.toString());

        System.out.println("Po raztovoru " + volvo.toString());
        System.out.println();

        //KOMBI
        System.out.println("KOMBI");
        Kombi reno = new Kombi("Kombi-dostava", 10, true);
        reno.setZnamka("Renault");
        reno.setRegistrskaStevilka("LJ 8888");
        System.out.println("Kapaciteta " + reno.vrniKapaciteto());

        reno.natovoriCepivo(a);
        reno.natovoriCepivo(b);
        reno.natovoriCepivo(c);

        System.out.println("Odstotek zasedenosti " + reno.vrniZasedenost());
        System.out.println(reno.toString());

        System.out.println("Po raztovoru " + reno.toString());

        //ILOGISTIKA - natovorjenje na avto

        ljCepilniCenter.dodajCepivo(a);
        ljCepilniCenter.dodajCepivo(d);
        ljCepilniCenter.dodajCepivo(c);
        ljCepilniCenter.dodajCepivo(e);
        ljCepilniCenter.dodajCepivo(c);
        ljCepilniCenter.dodajCepivo(e);
        ljCepilniCenter.dodajCepivo(c);
        ljCepilniCenter.dodajCepivo(e);
        ljCepilniCenter.dodajCepivo(c);
        ljCepilniCenter.dodajCepivo(e);

        System.out.println(ljCepilniCenter.vrniSteviloCepiv());

        try {
            System.out.println(ljCepilniCenter.prevzamiCepiva(volvo));
        } catch (KapacitetaPresezenaException kapacitetaPresezenaException) {
            kapacitetaPresezenaException.printStackTrace();
        } catch (TipCepivaException tipCepivaException) {
            tipCepivaException.printStackTrace();
        }
        System.out.println(ljAmbulanta.prevzamiCepiva(volvo));
        System.out.println("Po prevzetju cepiv " + volvo.toString());
    }*/
    }
}
