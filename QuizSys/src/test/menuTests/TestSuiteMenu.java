package test.menuTests;

/**
 * For launching local and remote tests of the menu package.
 *
 * Created by Guilherme Ribeiro on 20/04/2014.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.menuTests.test.local.LoginMenuTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //Local
        LoginMenuTest.class
})
public class TestSuiteMenu {
//TODO need to repeat the same tests using the rmi versions of all the persistence classes.
}
