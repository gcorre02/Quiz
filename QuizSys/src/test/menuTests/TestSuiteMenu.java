package test.menuTests;

/**
 * For launching local and remote tests of the menu package.
 *
 * Created by Guilherme Ribeiro on 20/04/2014.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.menuTests.test.local.EditQuizMenuTest;
import test.menuTests.test.local.LoginMenuTest;
import test.menuTests.test.local.UserMenuTest;
import test.menuTests.test.remote.EditQuizMenuRmiTest;
import test.menuTests.test.remote.LoginMenuRmiTest;
import test.menuTests.test.remote.UserMenuRmiTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //Local
        LoginMenuTest.class,
        UserMenuTest.class,
        EditQuizMenuTest.class,

        //Remote
        LoginMenuRmiTest.class,
        UserMenuRmiTest.class,
        EditQuizMenuRmiTest.class
})
public class TestSuiteMenu {
}
