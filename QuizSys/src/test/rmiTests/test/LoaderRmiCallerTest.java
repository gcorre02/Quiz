package test.rmiTests.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.LoaderRmiCaller;
import tools.CollectionTools;

import static junit.framework.TestCase.fail;

/**
 * Created by user on 18-04-2014.
 */
public class LoaderRmiCallerTest {
    private LoaderRmiCaller lrc;


    @Before
    public void setup() throws Exception{
        lrc = new LoaderRmiCaller("testFiles");

    }
    @After
    public void close() throws Exception{
        lrc = null;
    }



    @Test //TODO impl file structure
    public void testGetUsernames() throws Exception {
        System.out.println(lrc.getUsernames());
        fail();
    }

    @Test
    public void testGetUserQuizzes() throws Exception {
        System.out.println(CollectionTools.printMap(lrc.getUserQuizzes()));
        fail();
    }

    @Test
    public void testGetQuizObject() throws Exception {
        System.out.println(lrc.getQuizObject("Gonzo","cars"));
        fail();
    }

    @Test
    public void testGetQuizQuestionsConfig() throws Exception {
        System.out.println(CollectionTools.collectionPrinter('S', lrc.getQuizQuestionsConfig("Gonzo", "cars")));
        fail();
    }

    @Test
    public void testGetQuestionNumber() throws Exception {
        System.out.println(lrc.getQuestionNumber("Who is the sexiest bond villain?","Guy Fawlkes","Another Bond"));
        fail();
    }

    @Test
    public void testGetQuestionObject() throws Exception {
        System.out.println(lrc.getQuestionObject("Guy Fawlkes", "Another Bond", "Who is the sexiest bond villain?"));
        fail();
    }
}
