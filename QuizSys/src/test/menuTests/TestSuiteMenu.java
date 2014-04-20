package test.menuTests;

/**
 * For launching local and remote tests of the menu package.
 *
 * Created by Guilherme Ribeiro on 20/04/2014.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.menuTests.test.local.LoginMenuTest;
import test.menuTests.test.remote.LoginMenuRmiTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //Local
        LoginMenuTest.class,

        //Remote
        LoginMenuRmiTest.class
})
public class TestSuiteMenu {
//TODO need to repeat the same tests using the rmi versions of all the persistence classes.
}
