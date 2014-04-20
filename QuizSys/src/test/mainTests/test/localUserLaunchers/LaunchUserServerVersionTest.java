package test.mainTests.test.localUserLaunchers;

import main.LocalUserLaunchers.LaunchUserServerVersion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tools.UserInterface;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * Created by Guilherme on 20/04/2014.
 *
 *
 */
public class LaunchUserServerVersionTest {
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
        LaunchUserServerVersion.launch(source, ui);
        verify(ui).printToUser("Shutting down, thank you.");
    }
}
