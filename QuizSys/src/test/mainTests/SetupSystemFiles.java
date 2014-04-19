package test.mainTests;

import org.junit.Test;
import persistence.Loader;
import persistence.Saver;

/**
 * generates the file structure to allow for testing.
 *
 * Created by Admin on 19/04/2014.
 */
public class SetupSystemFiles {
    Saver s;
    String source;
    Loader l;
    @Test
    public void run(){
        s = new Saver(source);
        l = new Loader(source);
    }

}
