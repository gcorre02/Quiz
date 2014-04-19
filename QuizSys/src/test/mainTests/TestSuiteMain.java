package test.mainTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * Created by Guilherme Ribeiro on 19/04/2014.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SetupSystemFiles.class
        //,tests go in here.
        //DeleteSystemFiles.class // to be called at the end.
})
public class TestSuiteMain {
    //TODO setUp file system here.
}
