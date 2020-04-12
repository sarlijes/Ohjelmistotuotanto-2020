package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        PeliOlioTehdas olioTehdas = new PeliOlioTehdas();

        while (true) {
            tulostaOhjeet();


            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                tulostaPelinLopettamisOhje();

                Peli peli = olioTehdas.luoPelaajaVsPelaajaPeli();
                peli.pelaa();
            } else if (vastaus.endsWith("b")) {
                tulostaPelinLopettamisOhje();
                Peli peli = olioTehdas.luoPelaajaVsTekoalyPeli();
                peli.pelaa();
            } else if (vastaus.endsWith("c")) {
                tulostaPelinLopettamisOhje();
                Peli peli = olioTehdas.luoPelaajaVsSuperTekoalyPeli();
                peli.pelaa();
            } else {
                break;
            }

        }

    }

    private static void tulostaPelinLopettamisOhje() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
    }

    private static void tulostaOhjeet() {
        System.out.println("\nValitse pelataanko"
                + "\n (a) ihmistä vastaan "
                + "\n (b) tekoälyä vastaan"
                + "\n (c) parannettua tekoälyä vastaan"
                + "\nmuilla valinnoilla lopetetaan");
    }



}
