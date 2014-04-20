package test;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.mainTests.TestSuiteMain;
import test.menuTests.TestSuiteMenu;
import test.persistenceTests.TestSuitePersistence;
import test.toolsTests.TestSuiteTools;

/**
 *
 * Created by Guilherme Ribeiro on 19/04/2014.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SetupSystemFiles.class,
        //package test suites
        TestSuiteMain.class,
        TestSuiteTools.class,
        TestSuiteMenu.class,
        TestSuitePersistence.class,
        //remove test files
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
