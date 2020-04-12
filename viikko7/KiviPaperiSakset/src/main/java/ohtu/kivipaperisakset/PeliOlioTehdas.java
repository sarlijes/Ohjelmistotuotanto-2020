package ohtu.kivipaperisakset;

public class PeliOlioTehdas {

    public PeliOlioTehdas() {
    }

    public static Peli luoPelaajaVsPelaajaPeli() {
        return new Peli(Peli.Vastustaja.PELAAJA);
    }

    public static Peli luoPelaajaVsTekoalyPeli() {
        return new Peli(Peli.Vastustaja.TEKOALY);
    }

    public static Peli luoPelaajaVsSuperTekoalyPeli() {
        return new Peli(Peli.Vastustaja.SUPERTEKOALY);
    }


}
