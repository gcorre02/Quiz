package test.playerTests.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import player.PlayerLoginMenu;
import tools.UserInterface;

import java.io.File;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Guilherme on 14-04-2014.
 */
public class PlayerLoginMenuTest {
    String source;
    PlayerLoginMenu plm;
    UserInterface ui;
    PlayerLoader pl;
    PlayerSaver ps;

    @Before
    public void setUp(){
        source = "testFiles";
        ui = mock(UserInterface.class);
        pl = new PlayerLoader(source);
        ps = new PlayerSaver(source);
        plm = new PlayerLoginMenu(pl, ps, ui);
    }
    @After
    public void tearDown(){
        source = null;
        ui = null;
        pl = null;
        ps = null;
        plm = null;
    }

    @Test
    public void testRun() throws Exception {
        //TODO only a stub
        when(ui.getUserAnswer(anyString())).thenReturn('B','D','D');
        plm.run();

        fail();

    }
    @Test
    public void testDeleteAPlayer() throws Exception {
        //TODO only a stub
        String playerStr = "Goncalo";
        ps.addPlayer(playerStr);
        when(ui.readFromUser()).thenReturn(playerStr);
        when(ui.getUserAnswer(anyString())).thenReturn('B','D','D');
        plm.run();
        //Expected
        String expected = playerStr;
        //actual
        String actual = playerStr;
        //test
        File f = new File(source + File.separator + "Player" + File.separator + playerStr+".txt");
        assertFalse(f.exists());
        }

    @Test
    public void testCreateAPlayer() throws Exception {
        //TODO only a stub
        String playerStr = "Jeremias";
        when(ui.readFromUser()).thenReturn(playerStr);
        when(ui.getUserAnswer(anyString())).thenReturn('A','D','D');
        plm.run();
        //Expected
        String expected = playerStr;
        //actual
        String actual = playerStr;
        //test
        File f = new File(source + File.separator + "Player" + File.separator + playerStr+".txt");
        assertTrue(f.exists());
    }

}
