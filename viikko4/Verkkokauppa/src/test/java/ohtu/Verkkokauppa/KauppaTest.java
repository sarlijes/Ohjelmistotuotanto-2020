package ohtu.Verkkokauppa;

import com.google.errorprone.annotations.Var;
import ohtu.verkkokauppa.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    private Pankki pankki;
    private Viitegeneraattori viitegeneraattori;
    private Varasto varasto;
    private Kauppa kauppa;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viitegeneraattori = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viitegeneraattori);

    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOriginal() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viitegeneraattori.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {

        when(viitegeneraattori.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        // suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void kahdenEriOstoksenKorillaPankinMetodiaTilisiirtoKutsutaanOikein() {

        when(viitegeneraattori.uusi()).thenReturn(44);

        when(varasto.saldo(1)).thenReturn(11);
        when(varasto.saldo(2)).thenReturn(22);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "kauramaito", 1));
        when(varasto.haeTuote(1)).thenReturn(new Tuote(2, "linssit", 3));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("tiina", "121212");

        // varmistetaan että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("tiina"), eq(44), eq("121212"), eq("33333-44455"), eq(3));
    }

    @Test
    public void kahdenSamanOstoksenKorillaPankinMetodiaTilisiirtoKutsutaanOikein() {

        when(viitegeneraattori.uusi()).thenReturn(44);
        when(varasto.saldo(1)).thenReturn(11);
        when(varasto.saldo(2)).thenReturn(22);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "hasselpähkinät", 2));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("tiina", "121212");

        // varmistetaan että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("tiina"), eq(44), eq("121212"), eq("33333-44455"), eq(4));
    }

    @Test
    public void kahdenSamanOstoksenKorillaPankinMetodiaTilisiirtoKutsutaanOikeinKunToinenTuoteOnLoppu() {

        when(viitegeneraattori.uusi()).thenReturn(44);
        when(varasto.saldo(1)).thenReturn(11);
        when(varasto.saldo(2)).thenReturn(0);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "hasselpähkinät", 2));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "suklaa", 2));


        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("tiina", "121212");

        // varmistetaan että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("tiina"), eq(44), eq("121212"), eq("33333-44455"), eq(2));
    }
/*




aloitetaan asiointi, koriin lisätään tuote, jota on varastossa tarpeeksi ja tuote joka on loppu ja suoritetaan ostos,
varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla

     */

}
