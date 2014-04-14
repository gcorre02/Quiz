package player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import userInterface.UserInterface;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;

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
}
