package test.mainTests.test.launchers;

import main.launchers.ServerMain;
import org.junit.*;
import rmi.LoaderServerLauncher;
import tools.UserInterface;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * Created by Guilherme on 19/04/2014.
 *
 */
public class ServerMainTest {
    String source;
    UserInterface ui;

    @BeforeClass
    public static void setupBeforeClass(){
        //server is working for all tests
        LoaderServerLauncher.main(new String[]{"testFiles"});
    }

    @Before
    public void setUp() throws Exception {
        source = "testFiles";
        ui = mock(UserInterface.class);
    }

    @After
    public void tearDown() throws Exception {
        source = null;
    }



    @Test
    public void testLaunch() throws Exception {
        when(ui.getUserAnswer(anyString())).thenReturn('D');
        when(ui.printToUser(anyString())).thenCallRealMethod();
        ServerMain.launch(source, ui);
        verify(ui).getUserAnswer(anyString());
        verify(ui).printToUser("Couldn't understand input. Bye");
    }
}
