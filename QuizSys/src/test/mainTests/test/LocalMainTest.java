package test.mainTests.test;

import main.launchers.LocalMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tools.UserInterface;

import static junit.framework.TestCase.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.configuration.GlobalConfiguration.validate;

/**
 * Created by Admin on 19/04/2014.
 */
public class LocalMainTest {
    String source;
    LocalMain lm;
    UserInterface ui;
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
    public void testMain() throws Exception {
        when(ui.getUserAnswer(anyString())).thenReturn('D');
        when(ui.printToUser(anyString())).thenCallRealMethod();
        LocalMain.launch(source,ui);
        verify(ui).printToUser("Couldn't understand input. Bye");
    }
}
