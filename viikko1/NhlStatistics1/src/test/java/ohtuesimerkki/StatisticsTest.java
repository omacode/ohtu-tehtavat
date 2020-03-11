package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

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
    public void olemassaOlevanPelaajanEtsintaNimellaToimii() {
        assertEquals("Semenko", stats.search("Semenko").getName());
    }
    
    @Test
    public void eiOlemassaOlevanPelaajanEtsintaNimellaToimii() {
        assertEquals(null, stats.search("Davidson"));
    }
    
    @Test
    public void joukkueenPelaajienMaaraHaettaessaOikein() {
        assertEquals(3, stats.team("EDM").size());
    }
    
    @Test
    public void joukkueenPelaajienHakuToimii() {
        assertEquals("Yzerman", stats.team("DET").get(0).getName());
    }
    
    @Test
    public void pisteporssissaOikeaMaaraPelaajia() {
        assertEquals(3, stats.topScorers(2).size());
    }
    
    @Test
    public void pisteporssiTyhjaNegatiivisellaMaarallaHaettaessa() {
        assertEquals(0, stats.topScorers(-1).size());
    }
    
    @Test
    public void pisteporssinJohtajaOikein() {
        assertEquals("Gretzky", stats.topScorers(4).get(0).getName());
    }
}
