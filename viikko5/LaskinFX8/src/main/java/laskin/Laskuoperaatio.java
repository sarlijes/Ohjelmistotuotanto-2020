package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Laskuoperaatio extends Komento {

    public int syote;

    public Laskuoperaatio(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
        super(sovellus, tuloskentta, syotekentta, nollaa, undo);
    }

    private void tulostaOperaationTulos() {
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.setText("");

    }

    @Override
    public void suorita() {
        syote = 0;
        try {
            syote = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {

        }

        laskeOperaatio();
        tulostaOperaationTulos();
    }

    protected abstract void laskeOperaatio();

}