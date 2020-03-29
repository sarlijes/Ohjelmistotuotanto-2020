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
    public void setUpAndInitStock() {
        pankki = mock(Pankki.class);
        viitegeneraattori = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viitegeneraattori);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(22);
        when(varasto.saldo(3)).thenReturn(33);
        when(varasto.saldo(4)).thenReturn(11);
        when(varasto.saldo(5)).thenReturn(0);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "lehmänmaito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kauramaito", 1));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "linssit", 3));
        when(varasto.haeTuote(4)).thenReturn(new Tuote(4, "hasselpähkinät", 2));
        when(varasto.haeTuote(5)).thenReturn(new Tuote(5, "suklaa", 2));
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOriginal() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viitegeneraattori.uusi()).thenReturn(42);

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

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void kahdenEriOstoksenKorillaPankinMetodiaTilisiirtoKutsutaanOikein() {

        when(viitegeneraattori.uusi()).thenReturn(44);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("tiina", "121212");

        verify(pankki).tilisiirto(eq("tiina"), eq(44), eq("121212"), eq("33333-44455"), eq(4));
    }

    @Test
    public void kahdenSamanOstoksenKorillaPankinMetodiaTilisiirtoKutsutaanOikein() {

        when(viitegeneraattori.uusi()).thenReturn(44);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("tiina", "121212");

        verify(pankki).tilisiirto(eq("tiina"), eq(44), eq("121212"), eq("33333-44455"), eq(2));
    }

    @Test
    public void kahdenSamanOstoksenKorillaPankinMetodiaTilisiirtoKutsutaanOikeinKunToinenTuoteOnLoppu() {

        when(viitegeneraattori.uusi()).thenReturn(44);


        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(4);
        kauppa.lisaaKoriin(5);
        kauppa.tilimaksu("tiina", "121212");

        // varmistetaan että pankin metodia tilisiirto on kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("tiina"), eq(44), eq("121212"), eq("33333-44455"), eq(2));

    }

    @Test
    public void asioinninAloittaminenNollaaEdellisenOstoksen() {
        when(viitegeneraattori.uusi()).thenReturn(44);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(4);
        kauppa.lisaaKoriin(5);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("tiina", "121212");

        verify(pankki).tilisiirto(eq("tiina"), eq(44), eq("121212"), eq("33333-44455"), eq(5));

    }

    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiselleMaksutapahtumalle() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(4);
        kauppa.tilimaksu("esmeralda", "131313");
        verify(pankki).tilisiirto(eq("esmeralda"), eq(0), eq("131313"), eq("33333-44455"), eq(2));

        verify(viitegeneraattori, times(1)).uusi();

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(4);
        kauppa.tilimaksu("esmeralda", "131313");

        verify(viitegeneraattori, times(2)).uusi();

    }

    @Test
    public void ostoskoristaPoistaminenPoistaaTuotteen() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(4);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(3);

        kauppa.tilimaksu("esmeralda", "131313");

        verify(varasto, times(1)).palautaVarastoon(any());

    }

}
