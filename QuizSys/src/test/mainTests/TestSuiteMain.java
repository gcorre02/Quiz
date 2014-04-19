package test.mainTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.mainTests.test.launchers.LocalMainTest;

/**
 *
 * Created by Guilherme Ribeiro on 19/04/2014.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LocalMainTest.class
        //, local tests go in here.
        //SetupSystemFiles.class,DeleteSystemFiles.class // to be called at the end.
})
public class TestSuiteMain {
    //TODO setUp file system here.
}
