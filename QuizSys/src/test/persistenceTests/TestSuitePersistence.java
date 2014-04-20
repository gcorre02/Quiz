package test.persistenceTests;

/**
 * For launching local and remote tests of the menu package.
 *
 * Created by Guilherme Ribeiro on 20/04/2014.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.persistenceTests.test.LoaderTest;
import test.persistenceTests.test.PlayerLoaderTest;
import test.persistenceTests.test.PlayerSaverTest;
import test.persistenceTests.test.SaverTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoaderTest.class,
        PlayerLoaderTest.class,
        PlayerSaverTest.class,
        SaverTest.class
})
public class TestSuitePersistence {
}
