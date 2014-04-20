package test;

import org.junit.Test;
import persistence.Saver;

import java.io.File;

import static junit.framework.TestCase.assertFalse;

/**
 * Created by Guilherme on 19/04/2014.
 *
 *
 */
public class DeleteSystemFiles {
    String source;
    Saver s;
    @Test
    public void close() throws InterruptedException {
        Thread.sleep(100);
        source = "testFiles";
        s = new Saver(source);
        s.deleteFolder(source);
        File f = new File(source);
        assertFalse(f.exists());
    }

}
