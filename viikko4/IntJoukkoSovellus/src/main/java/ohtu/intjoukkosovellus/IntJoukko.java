
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5; // aloitustaulukon koko
    public final static int OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLukumaara;    // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        alustaJoukonMuuttujat(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        alustaJoukonMuuttujat(kapasiteetti, OLETUSKASVATUS);
    }

    public void alustaJoukonMuuttujat(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLukumaara = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetin tulee olla positiivinen kokonaisluku.");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoon tulee olla positiivinen kokonaisluku.");
        }
        alustaJoukonMuuttujat(kapasiteetti, kasvatuskoko);
    }

    public void kasvataTaulukonKokoa() {
        int[] vanhaTaulukko = lukujono;
        kopioiTaulukko(lukujono, vanhaTaulukko);
        lukujono = new int[alkioidenLukumaara + kasvatuskoko];
        kopioiTaulukko(vanhaTaulukko, lukujono);
    }

    public boolean lisaa(int luku) {
        if (!kuuluuJoukkoon(luku)) {
            lukujono[alkioidenLukumaara] = luku;
            alkioidenLukumaara++;

            if (alkioidenLukumaara == lukujono.length) {
                kasvataTaulukonKokoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluuJoukkoon(int luku) {

        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;

    }

    private void siirraLukujaVasemmalle(int aloituskohta) {

        for (int i = aloituskohta; i < alkioidenLukumaara; i++) {
            lukujono[i] = lukujono[i + 1];
        }
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                alkioidenLukumaara--;
                siirraLukujaVasemmalle(i);
                return true;
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int alkioidenLkm() {
        return alkioidenLukumaara;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("{");

        if (alkioidenLukumaara > 0) {
            for (int i = 0; i < alkioidenLukumaara - 1; i++) {
                sb.append(lukujono[i]);
                sb.append(", ");
            }
            sb.append(lukujono[alkioidenLukumaara - 1]);
        }

        return sb.append("}").toString();
    }

    public int[] muunnaKokonaislukutauluksi() {
        int[] taulu = new int[alkioidenLukumaara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.muunnaKokonaislukutauluksi();
        int[] bTaulu = b.muunnaKokonaislukutauluksi();

        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.muunnaKokonaislukutauluksi();
        int[] bTaulu = b.muunnaKokonaislukutauluksi();

        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.muunnaKokonaislukutauluksi();
        int[] bTaulu = b.muunnaKokonaislukutauluksi();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }

        return erotus;
    }

}
