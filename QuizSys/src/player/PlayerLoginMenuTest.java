package player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import userInterface.UserInterface;

import java.io.File;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.fail;
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
        plm.run();

        fail();

    }
    @Test
    public void testDeleteAPlayer() throws Exception {
        //TODO only a stub
        String playerStr = "Goncalo";
        when(ui.readFromUser()).thenReturn(playerStr, playerStr);
        when(ui.getUserAnswer(anyString())).thenReturn('A','D','B','D');
        plm.run();
        //Expected
        String expected = playerStr;
        //actual
        String actual = playerStr;
        //test
        File f = new File(source + File.separator + "Player" + File.separator + playerStr+".txt");
        assertFalse(f.exists());
        }
}
