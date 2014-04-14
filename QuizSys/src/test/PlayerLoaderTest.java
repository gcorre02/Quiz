package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import quizData.Player;

import static junit.framework.Assert.assertEquals;
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
        ps = new PlayerSaver(playerName);
    }

    @After
    public void tearDown() throws Exception {
        source =null;
    }

    @Test
    public void testGetPlayersArray() throws Exception {
        fail();//TODO impl
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
