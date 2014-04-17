package rmi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tools.CollectionPrinter;

import java.util.ArrayList;

/**
 * Created by user on 17-04-2014.
 */
public class GenericGetterTestTest {
    GenericClassToBeUSedStub gcs;
    GenericGetterStub ggt;
    @Before
    public void setUp() throws Exception {
        gcs = new GenericClassToBeUSedStub();
        ggt = new GenericGetterStub();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test //TODO impl test
    public void testDoAnythingGetaString() throws Exception {
        String inputClass = gcs.getClass().getName();
        String inputMethod = gcs.getClass().getDeclaredMethods()[0].getName();
        String returnMessage = ggt.doAnything(inputClass,inputMethod);
        System.out.println(returnMessage);
    }

    @Test//TODO impl test
    public void testDoAnythingGetanArray() throws Exception {
        String inputClass = gcs.getClass().getName();
        String inputMethod = gcs.getClass().getDeclaredMethods()[1].getName();
        ArrayList<String> returnMessage = ggt.doAnything(inputClass,inputMethod);
        System.out.println(CollectionPrinter.collectionPrinter('S',returnMessage));
    }
}
