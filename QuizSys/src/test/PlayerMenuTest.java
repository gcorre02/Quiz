package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import player.PlayerMenu;
import userInterface.UserInterface;

import static junit.framework.TestCase.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by Admin on 15/04/2014.
 */
public class PlayerMenuTest {
    String source;
    String player;
    PlayerMenu pm;
    UserInterface ui;
    PlayerLoader pl;
    PlayerSaver ps;

    @Before
    public void setUp() throws Exception {
        source = "testFiles";
        player = "Steve Balmoral";
        ui = mock(UserInterface.class);
        ps = new PlayerSaver(source);
        pl = new PlayerLoader(source);
        ps.addPlayer(player);
        pm = new PlayerMenu(player,ui,pl,ps);
    }

    @After
    public void tearDown() throws Exception {
        source = null;
        player = null;
        ui = null;
        ps = null;
        pl = null;
        pm = null;
    }

    @Test
    public void testRun() throws Exception {
        //TODO implement
        //setup
        when(ui.getUserAnswer(anyString())).thenReturn('A','C','D');
        //debug
        pm.run();
        //test
        fail();
    }
    @Test
    public void testShowAllQuizzes() throws Exception {
        //TODO improve test, kinda sucks
        //setup
        when(ui.getUserAnswer(anyString())).thenReturn('A','D');
        //debug
        pm.run();
        //test
        verify(ui,times(2)).getUserAnswer(anyString());
    }
    @Test
    public void testPlayAQuiz() throws Exception {
        //TODO implement
        //setup
        when(ui.getUserAnswer(anyString())).thenReturn('B','D');
        when(ui.readFromUser()).thenReturn("1","0");
        //debug
        pm.run();
        //test
        fail();
    }
}
