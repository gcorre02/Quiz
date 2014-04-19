package test;

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
}
