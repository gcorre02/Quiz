package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rmi.LoaderClient;
import rmi.LoaderServerLauncher;
import userInterface.UserInterface;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;

/**
 * needs -Djava.security.policy=security.policy as jvm argument and for the security policy to be in the file system @ source(\...).
 *
 * Created by Admin on 16/04/2014.
 */
public class LoaderClientTest {
    UserInterface ui;
    LoaderClient lc;
    LoaderServerLauncher lsl;
    @Before
    public void setUp() throws Exception {
        ui = mock(UserInterface.class);
        lc = new LoaderClient(ui);
        lsl = new LoaderServerLauncher();
        lsl.main(new String[0]);
    }

    @After
    public void tearDown() throws Exception {
        lsl.shutDown();
    }

    @Test
    public void testMain() throws Exception {
        //lc.main(new String[0]);
        fail();
    }

    //@Test
    public void testRun() throws Exception {
    //this test calls the getUserNames() array.
        String callClass = "persistence.Loader.class";
        String callMethod = "getUsernames";
        System.out.println(lc.run(callClass, callMethod));
        fail();
    }
}
