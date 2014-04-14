package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import quizData.Player;
import tools.CollectionPrinter;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Created by Guilherme on 14-04-2014.
 */
public class PlayerLoaderTest {
    String source;
    PlayerLoader pl;
    String playerName;
    PlayerSaver ps;
    @Before
    public void setUp() throws Exception {
        source = "testFiles";
        pl = new PlayerLoader(source);
        playerName = "Liam";
        ps = new PlayerSaver(source);
    }

    @After
    public void tearDown() throws Exception {
        source =null;
    }

    @Test
    public void testGetPlayersArray() throws Exception {
        //expected
        ArrayList<String> expecteds = new ArrayList<>();
        expecteds.add("AdminPlayer");
        //actual
        ArrayList<String> actuals = pl.getPlayersArray();
        //debug
        System.out.println(CollectionPrinter.collectionPrinter('0',actuals));
        //test
        assertTrue(actuals.containsAll(expecteds));
    }

    @Test
    public void testGetPlayer() throws Exception {
        //setup
        ps.addPlayer(playerName);
        Player p = pl.getPlayer(playerName);
        //expected
        String expected = playerName;
        //actual
        String actual = p.getName();
        //test
        assertEquals(expected,actual);
    }
}
