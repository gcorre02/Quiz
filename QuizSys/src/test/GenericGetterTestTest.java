package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import quizData.Quiz;
import rmi.GenericClassToBeUSedStub;
import rmi.GenericGetterStub;
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
        gcs = new GenericClassToBeUSedStub("true");
        ggt = new GenericGetterStub();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test //TODO impl test
    public void testDoAnythingGetaString() throws Exception {
        String inputClass = gcs.getClass().getName();
        String inputMethod = "saySomething";
        String returnMessage = ggt.doAnything(inputClass,inputMethod);
        System.out.println(returnMessage);
    }

    @Test//TODO impl test
    public void testDoAnythingGetanArray() throws Exception {
        String inputClass = gcs.getClass().getName();
        String inputMethod = "sayABunchOfStuff";
        //debug
        //System.out.println(inputMethod);
        //\debug
        ArrayList<String> returnMessage = ggt.doAnything(inputClass,inputMethod);
        System.out.println(CollectionPrinter.collectionPrinter('S',returnMessage));
    }
    @Test//TODO impl test
    public void testDoAnythingGetaQuiz() throws Exception {
        String inputClass = gcs.getClass().getName();
        String inputMethod = "returnsAQuiz";
        //debug
        //System.out.println(inputMethod);
        //\debug
        Quiz q = ggt.doAnything(inputClass,inputMethod);
        System.out.println(q + "\n" + q.getOwner() + "\n"+ q.getQuizQuestions());
    }

    @Test//TODO impl test
    public void testDoAnythingGetaStringFromParams() throws Exception {
        String inputClass = gcs.getClass().getName();
        String inputMethod = "getStringFromLoadsOfParams";
        //debug
        //System.out.println(inputMethod);
        //\debug
        String result = ggt.doAnythingWithMoreParams(inputClass,inputMethod,"Jimmy", 7);
        System.out.println(result);
    }
    @Test//TODO impl test
    public void testDoAnythingGetaStringnoParams() throws Exception {
        String inputClass = gcs.getClass().getName();
        String inputMethod = "saySomething";
        //debug
        //System.out.println(inputMethod);
        //\debug
        String result = ggt.doAnythingWithMoreParams(inputClass,inputMethod);
        System.out.println(result);
    }

    @Test//TODO impl test
    public void testDoAnythingGetaQuiznMultipleParams() throws Exception {
        String inputClass = gcs.getClass().getName();
        String inputMethod = "returnsAQuizMultipleParams";
        //debug
        System.out.println(inputClass);
        //\debug

        ArrayList<String> questions = new ArrayList<>();
        questions.add("I'm");
        questions.add("Returning");
        questions.add("Stuff");
        questions.add("!!");
        String quizname = "new generics";
        String quizOwner = "new reflectors";


        Quiz q = ggt.doAnythingWithMoreParams(inputClass,inputMethod,quizname,quizOwner,questions);
        System.out.println(q + "\n" + q.getOwner() + "\n"+ q.getQuizQuestions());
    }


}
