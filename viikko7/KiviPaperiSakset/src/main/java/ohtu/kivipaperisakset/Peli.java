package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Peli extends Kyselyoperaatio {

    private static final Scanner scanner = new Scanner(System.in);

    private Vastustaja vastustaja;

    public enum Vastustaja {
        PELAAJA, TEKOALY, SUPERTEKOALY;
    }

    protected Peli(Vastustaja vastustaja) {
        this.vastustaja = vastustaja;
    }

//    @Override
    public void pelaa() {

        Tuomari tuomari = new Tuomari();

        if (this.vastustaja.equals(Vastustaja.PELAAJA)) {

            String ekanSiirto = kysyEkanPelaajanSiirto(scanner);
            String tokanSiirto = kysyTokanPelaajanSiirto(scanner);

            while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
                tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
                System.out.println(tuomari);
                System.out.println();

                ekanSiirto = kysyEkanPelaajanSiirto(scanner);

                tokanSiirto = kysyTokanPelaajanSiirto(scanner);
            }
        }

        Tekoaly tekoaly;

        if (this.vastustaja.equals(Vastustaja.SUPERTEKOALY)) {
            tekoaly = new Supertekoaly(20);
        } else {
            tekoaly = new Perustekoaly();
        }

        String ekanSiirto = kysyEkanPelaajanSiirto(scanner);
        String tokanSiirto;

        tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);


        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            ekanSiirto = kysyEkanPelaajanSiirto(scanner);

            tokanSiirto = tekoaly.annaSiirto();
            System.out.println("Tietokone valitsi: " + tokanSiirto);
            tekoaly.asetaSiirto(ekanSiirto);

        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

//    @Override
    public boolean onkoOkSiirto(String siirto) {

        if ("k".equals(siirto)) return true;
        if ("p".equals(siirto)) return true;
        if ("s".equals(siirto)) return true;
        return false;
    }

}
