package test.mainTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.mainTests.test.launchers.LaunchServerMainTest;
import test.mainTests.test.launchers.LocalMainTest;
import test.mainTests.test.launchers.ServerMainTest;
import test.mainTests.test.localUserLaunchers.LaunchPlayerLocalVersionTest;
import test.mainTests.test.localUserLaunchers.LaunchPlayerServerVersionTest;
import test.mainTests.test.localUserLaunchers.LaunchUserLocalVersionTest;
import test.mainTests.test.localUserLaunchers.LaunchUserServerVersionTest;

/**
 *
 * Created by Guilherme Ribeiro on 19/04/2014.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        //launchers -jar <<main classes>>
        LocalMainTest.class,
        ServerMainTest.class,
        LaunchServerMainTest.class,
        //userSpecific local Launchers.
        LaunchPlayerLocalVersionTest.class,
        LaunchUserLocalVersionTest.class,
        //userSpecific server launchers //TODO need to create a handler for grabbing the source file location variable.
        LaunchUserServerVersionTest.class,
        LaunchPlayerServerVersionTest.class
        //SetupSystemFiles.class,DeleteSystemFiles.class // to be called at the end.
})
public class TestSuiteMain {
    //TODO setUp file system here.
}
