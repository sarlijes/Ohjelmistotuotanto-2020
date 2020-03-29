package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {

    private static IntJoukko A, B, C;

    private static String lueSyote() {
        Scanner lukija = new Scanner(System.in);
        return lukija.nextLine();
    }

    private static IntJoukko tunnistaJoukko() {
        String luettu = lueSyote();

        while (true) {
            if (luettu.equalsIgnoreCase("A")) {
                return A;
            } else if (luettu.equalsIgnoreCase("B")) {
                return B;
            } else if (luettu.equalsIgnoreCase("C")) {
                return C;
            } else {
                System.out.println("Virheellinen joukko! " + luettu);
                System.out.print("Yritä uudelleen!");
                luettu = lueSyote();
            }
        }
    }

    private static void lisaa() {

        Scanner lukija = new Scanner(System.in);
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = tunnistaJoukko();
        System.out.println("");
        System.out.print("Mikä luku lisätään? ");
        int lisattavaLuku = lukija.nextInt();

        joukko.lisaa(lisattavaLuku);
        return;

    }

    private static void yhdiste() {

        System.out.print("1. joukko? ");
        IntJoukko aJoukko = tunnistaJoukko();
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = tunnistaJoukko();

        System.out.println("A yhdiste B = " + IntJoukko.yhdiste(aJoukko, bJoukko).toString());
        return;
    }

    private static void leikkaus() {

        System.out.print("1. joukko? ");
        IntJoukko aJoukko = tunnistaJoukko();
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = tunnistaJoukko();

        System.out.println("A leikkaus B = " + IntJoukko.leikkaus(aJoukko, bJoukko).toString());
        return;
    }

    private static void erotus() {

        System.out.print("1. joukko? ");
        IntJoukko aJoukko = tunnistaJoukko();
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = tunnistaJoukko();

        System.out.println("A erotus B = " + IntJoukko.erotus(aJoukko, bJoukko).toString());
        return;
    }

    private static void poista() {

        Scanner lukija = new Scanner(System.in);
        System.out.print("Mistä joukosta? ");
        IntJoukko joukko = tunnistaJoukko();
        System.out.print("Mikä luku poistetaan? ");

        joukko.poista(lukija.nextInt());
        return;
    }

    private static void kuuluu() {

        Scanner lukija = new Scanner(System.in);
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = tunnistaJoukko();
        System.out.print("Mikä luku? ");
        int kysyttyLuku = lukija.nextInt();

        if (joukko.kuuluuJoukkoon(kysyttyLuku)) {
            System.out.println(kysyttyLuku + " kuuluu joukkoon ");
        } else {
            System.out.println(kysyttyLuku + " ei kuulu joukkoon ");
        }
        return;
    }

    public static void main(String[] args) {
        A = new IntJoukko();
        B = new IntJoukko();
        C = new IntJoukko();

        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).");
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");

        Scanner lukija = new Scanner(System.in);
        while (true) {
            String luettu = lukija.nextLine();
            if (luettu.startsWith("li")) {
                lisaa();
            } else if (luettu.startsWith("p")) {
                poista();
            } else if (luettu.startsWith("k")) {
                kuuluu();
            } else if (luettu.startsWith("y")) {
                yhdiste();
            } else if (luettu.startsWith("le")) {
                leikkaus();
            } else if (luettu.startsWith("e")) {
                erotus();

            } else if (luettu.equalsIgnoreCase("A")) {
                System.out.println(A);
            } else if (luettu.equalsIgnoreCase("B")) {
                System.out.println(B);
            } else if (luettu.equalsIgnoreCase("C")) {
                System.out.println(C);

            } else if (luettu.equalsIgnoreCase("lopeta") || luettu.startsWith("q")) {
                System.out.println("Lopetetaan, moikka!");
                break;
            } else {
                System.out.println("Virheellinen komento! " + luettu);
            }
            System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
        }
    }
}
