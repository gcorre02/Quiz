package test.mainTests.test.launchers;

import main.launchers.LaunchServerMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rmi.LoaderClient;
import tools.UserInterface;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 *
 *
 * Created by Guilherme on 19/04/2014.
 */
public class LaunchServerMainTest {

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
    public void testMain() throws Exception {
        //setup
        LaunchServerMain.main(new String[]{source});
        LoaderClient lc = new LoaderClient();
        String callClass = "persistence.Loader";
        String callMethod = "getUsernames";
        //exec
        ArrayList<String> usernames = lc.run(callClass, callMethod);
        //expected
        String expected = "Admin";
        //actual
        String actual = usernames.get(0);
        //test
        assertEquals(expected, actual);
    }
}
