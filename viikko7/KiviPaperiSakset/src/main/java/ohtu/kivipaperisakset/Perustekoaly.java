package ohtu.kivipaperisakset;

import java.util.Random;

public class Perustekoaly implements Tekoaly {

    private int siirto;
    private static final String[] siirrot = {"k", "s", "p"};

    public Perustekoaly() {
        siirto = 0;
    }

    public String annaSiirto() {
        siirto++;
        siirto = siirto % 3;

        if (siirto == 0) {
            return siirrot[0];
        } else if (siirto == 1) {
            return siirrot[1];
        } else {
            return siirrot[2];
        }
    }

    public void asetaSiirto(String ekanSiirto) {
    }
}
