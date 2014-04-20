package test.playerTests;

/**
 * For launching local and remote tests of the menu package.
 *
 * Created by Guilherme Ribeiro on 20/04/2014.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.playerTests.test.PlayAQuizMenuTest;
import test.playerTests.test.PlayerLoginMenuTest;
import test.playerTests.test.PlayerMenuTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlayAQuizMenuTest.class,
        PlayerLoginMenuTest.class,
        PlayerMenuTest.class
})
public class TestSuitePlayer {
}
