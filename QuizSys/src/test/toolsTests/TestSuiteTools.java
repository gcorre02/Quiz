package test.toolsTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.toolsTests.tests.CollectionToolsTest;
import test.toolsTests.tests.UserInterfaceTest;

/**
 *
 * Created by Guilherme Ribeiro on 19/04/2014.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CollectionToolsTest.class,
        UserInterfaceTest.class
})
public class TestSuiteTools {
    //TODO setUp file system here.
}
