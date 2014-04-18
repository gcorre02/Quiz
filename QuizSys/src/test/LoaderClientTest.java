package test;


import org.junit.*;
import rmi.LoaderClient;
import rmi.LoaderServerLauncher;

import java.util.ArrayList;

import static junit.framework.TestCase.fail;

/**
 * needs -Djava.security.policy=security.policy as jvm argument and for the security policy to be in the file system @ source(\...).
 *
 * at the moment, these tests only run properly with the server run outside of intelliJ.
 *
 * Created by Admin on 16/04/2014.
 */
public class LoaderClientTest {
    LoaderClient lc;
    static LoaderServerLauncher lsl;
    @BeforeClass
    public static void setupBeforeClass(){
        //server is working for all tests
        lsl = new LoaderServerLauncher();
        lsl.main(new String[0]);
    }

    @Before
    public void setUp() throws Exception {
        lc = new LoaderClient();


    }

    @After
    public void tearDown() throws Exception {
       // lsl = null;
    }
    @AfterClass
    public static void shutDown(){
        System.exit(0);
    }

    //@Test
    public void testMain() throws Exception {
        lc.main(new String[0]);
        fail();
    }

    @Test //TODO impl file system.
    public void testRun() {
        //this test calls the getUserNames() array.
        String callClass = "persistence.Loader";
        String callMethod = "getUsernames";
        ArrayList<String> usernames = null;
        try {
            usernames = lc.run(callClass, callMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(usernames);

        fail();
    }
   // @Test //TODO impl file system.
    public void testRunGetADataQuizObj() throws Exception { //TODO also test getting a map, getUserQuizzes...
        //this test calls the getUserNames() array.
        String callClass = "persistence.Loader";
        String callMethod = "getQuizObject";
        String quizName = "cars";
        String quizOwner = "Gonzo";
        System.out.println(lc.run(callClass, callMethod,quizOwner,quizName));
           //TODO serialize quizData, make tests run !(config ?)
        fail();
    }
}
