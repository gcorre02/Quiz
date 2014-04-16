package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rmi.LoaderClient;
import userInterface.UserInterface;

import static org.mockito.Mockito.mock;

/**
 * Created by Admin on 16/04/2014.
 */
public class LoaderClientTest {
    UserInterface ui;
    LoaderClient lc;
    @Before
    public void setUp() throws Exception {
        ui = mock(UserInterface.class);
        lc = new LoaderClient(ui);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMain() throws Exception {
        lc.main(new String[0]);
    }

    @Test
    public void testRun() throws Exception {
        lc.run();
    }
}
