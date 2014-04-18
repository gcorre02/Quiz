package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rmi.LoaderClient;
import rmi.LoaderServerLauncher;

import static junit.framework.TestCase.fail;

/**
 * needs -Djava.security.policy=security.policy as jvm argument and for the security policy to be in the file system @ source(\...).
 *
 * Created by Admin on 16/04/2014.
 */
public class LoaderClientTest {
    LoaderClient lc;
    LoaderServerLauncher lsl;
    @Before
    public void setUp() throws Exception {
        lc = new LoaderClient();
        lsl = new LoaderServerLauncher();
        lsl.main(new String[0]);
        //debug
        System.out.println("test set up is working");
    }

    @After
    public void tearDown() throws Exception {
       lsl.shutDown();
    }

    //@Test
    public void testMain() throws Exception {
        lc.main(new String[0]);
        fail();
    }

    @Test //TODO impl file system.
    public void testRun() throws Exception {
    //this test calls the getUserNames() array.
        String callClass = "persistence.Loader";
        String callMethod = "getUsernames";
        System.out.println(lc.run(callClass, callMethod));
        fail();
    }
    @Test //TODO impl file system.
    public void testRunGetADataQuizObj() throws Exception { //TODO also test getting a map, getUserQuizzes...
        //this test calls the getUserNames() array.
        String callClass = "persistence.Loader";
        String callMethod = "getQuizObject";
        String quizName = "cars";
        String quizOwner = "Gonzo";
        System.out.println(lc.run(callClass, callMethod,quizName,quizOwner));
        fail();
    }
}
