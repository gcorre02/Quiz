package test.persistenceTests;

/**
 * For launching local and remote tests of the menu package.
 *
 * Created by Guilherme Ribeiro on 20/04/2014.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.persistenceTests.test.local.LoaderTest;
import test.persistenceTests.test.local.PlayerLoaderTest;
import test.persistenceTests.test.local.PlayerSaverTest;
import test.persistenceTests.test.local.SaverTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //local
        LoaderTest.class,
        PlayerLoaderTest.class,
        PlayerSaverTest.class,
        SaverTest.class
        //remote is already tested by menuTests, but it would be good to write some dedicated ones if time allows it.
})
public class TestSuitePersistence {
}
