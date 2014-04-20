package test.persistenceTests.test.local;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.PlayerLoader;
import persistence.PlayerSaver;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Guilherme on 13-04-2014.\n
 * Test class for PlayerSaver
 */
public class PlayerSaverTest {
    PlayerSaver ps;
    String path;
    private PlayerLoader pl;

    @Before
    public void setUp() throws Exception {
        path = "testFiles";
        ps = new PlayerSaver(path);
        pl = new PlayerLoader(path);
    }

    @After
    public void tearDown() throws Exception {
        path = null;
        ps = null;
    }
    @Test
    public final void testConstructorGeneratesFileSystem() {

        //expected
        //TODO also test the object is created.
        //actual
        String source = path + File.separator + "Player";
        File p = new File(source);
        source = path + File.separator + "Player" + File.separator + "AdminPlayer.txt";
        File a = new File(source);
        source = path + File.separator + "Player"+ File.separator + "playersIndex.txt";
        File i = new File(source);
        //test
        assertTrue(p.exists());
        assertTrue(a.exists());
        assertTrue(i.exists());
    }
    @Test
    public final void testAddPlayerAddsPlayer() throws IOException {
        //expected
        String expected = "Jonah";
        //exec
        ps.addPlayer(expected);
        //actual
        String actual = pl.getPlayer(expected).getName();
        //test
        assertEquals(expected,actual);
    }
    @Test
    public final void testDeletePlayerDelsPlayer() throws IOException {
        //expected
        String expected = "Jon";
        //exec
        ps.addPlayer(expected);
        assertTrue(pl.getPlayersArray().contains(expected));
        ps.removePlayer(expected);
        assertFalse(pl.getPlayersArray().contains(expected));
    }
}
