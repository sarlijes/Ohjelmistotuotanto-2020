package laskin;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.util.HashMap;
import java.util.Map;

public class Tapahtumankuuntelija implements EventHandler<ActionEvent> {
    private Button undo;
    private Sovelluslogiikka sovellus;
    private Komento edellinen;

    private Map<Button, Komento> komennot;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Pluslasku(sovellus, tuloskentta, syotekentta, nollaa, undo));
        komennot.put(miinus, new Miinuslasku(sovellus, tuloskentta, syotekentta, nollaa, undo));
        komennot.put(nollaa, new Nollaus(sovellus, tuloskentta, syotekentta, nollaa, undo));
    }

    @Override
    public void handle(ActionEvent event) {

        if (event.getTarget() != undo) {
            Komento komento = komennot.get(event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }
    }


}