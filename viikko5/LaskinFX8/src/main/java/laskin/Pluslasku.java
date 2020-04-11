package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Pluslasku extends Laskuoperaatio {

    private int edellinenTulos;

    public Pluslasku(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
        super(sovellus, tuloskentta, syotekentta, nollaa, undo);
        this.edellinenTulos = 0;
    }

    @Override
    public void peru() {
        tuloskentta.setText(String.valueOf(Integer.valueOf(edellinenTulos)));
    }

    @Override
    protected void laskeOperaatio() {
        edellinenTulos = sovellus.tulos();
        sovellus.plus(super.syote);
    }
}
