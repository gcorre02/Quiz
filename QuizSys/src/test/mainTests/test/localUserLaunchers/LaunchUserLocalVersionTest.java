package test.mainTests.test.localUserLaunchers;

import main.LocalUserLaunchers.LaunchUserLocalVersion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tools.UserInterface;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by Admin on 20/04/2014.
 */
public class LaunchUserLocalVersionTest {
    private String source;
    private UserInterface ui;

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
        when(ui.printToUser(anyString())).thenCallRealMethod();
        when(ui.getUserAnswer(anyString())).thenReturn('D');
        LaunchUserLocalVersion.launch(source, ui);
        verify(ui).printToUser("Shutting down, thank you.");
    }
}
