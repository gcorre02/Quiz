package test;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.mainTests.TestSuiteMain;
import test.toolsTests.TestSuiteTools;

/**
 *
 * Created by Guilherme Ribeiro on 19/04/2014.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SetupSystemFiles.class,
        TestSuiteMain.class,
        TestSuiteTools.class,
        //, local test suites go in here.
        DeleteSystemFiles.class // to be called at the end.
})
public class TestSuite {
    //TODO setUp file system here.
    @AfterClass
    public static void close(){
        System.out.println("\nClosing, thank you for testing!\nYou might need to manually close the jvm at this point");
        //System.exit(0);
    }
}
