package test.persistenceTests.test.local;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.PlayerSaver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Guilherme on 13-04-2014.
 */
public class PlayerSaverTest {
    PlayerSaver ps;
    String path;
    @Before
    public void setUp() throws Exception {
        path = "testFiles";
        ps = new PlayerSaver(path);
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
        //TODO just a behaviour stub
        String expected = "Jonah";
        ps.addPlayer(expected);
    }
    @Test
    public final void testDeletePlayerDelsPlayer() throws IOException {
        //TODO just a behaviour stub
        String expected = "Jon";
        ps.addPlayer(expected);
        ps.removePlayer(expected);
    }
}
