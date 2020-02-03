package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticsTest {
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void pelaajienMaalimaaratOikein() {
        assertEquals(4, stats.search("Semenko").getGoals());
        assertEquals(45, stats.search("Lemieux").getGoals());
        assertEquals(37, stats.search("Kurri").getGoals());
        assertEquals(42, stats.search("Yzerman").getGoals());
        assertEquals(35, stats.search("Gretzky").getGoals());
    }

    @Test
    public void olematontaPelaajaaEiLoydy() {
        assertEquals(null, stats.search("Jessi"));
    }

}
