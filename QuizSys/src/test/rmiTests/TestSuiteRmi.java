package test.rmiTests;

/**
 * For launching local and remote tests of the menu package.
 *
 * Created by Guilherme Ribeiro on 20/04/2014.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.rmiTests.test.GenericGetterTestTest;
import test.rmiTests.test.LoaderClientTest;
import test.rmiTests.test.LoaderRmiCallerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GenericGetterTestTest.class,
        LoaderClientTest.class,
        LoaderRmiCallerTest.class
})
public class TestSuiteRmi {
}
