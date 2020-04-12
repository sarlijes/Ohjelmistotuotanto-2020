package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class Kyselyoperaatio {

    public String kysyEkanPelaajanSiirto(Scanner scanner) {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    public String kysyTokanPelaajanSiirto(Scanner scanner) {
        System.out.print("Toisen pelaajan siirto: ");
        return scanner.nextLine();
    }

}
