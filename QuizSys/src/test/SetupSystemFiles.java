package test;

import org.junit.Test;
import persistence.Loader;
import persistence.Saver;

import java.io.File;

import static org.junit.Assert.assertTrue;

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
        source = "testFiles";
        s = new Saver(source);
        l = new Loader(source);
        File f = new File(source);
        assertTrue(f.exists());//TODO add more tests to check whole structure.
    }

}
