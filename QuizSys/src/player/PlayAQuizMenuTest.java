package player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import persistence.Saver;
import quizData.Question;
import tools.CollectionPrinter;

import java.util.ArrayList;

/**
 * Created by Admin on 15/04/2014.
 */
public class PlayAQuizMenuTest {
    PlayAQuizMenu pqm;
    String source;
    Loader l;
    Saver s;
    PlayerLoader pl;
    PlayerSaver ps;
    @Before
    public void setUp() throws Exception {
        String quizName = "cars";
        String quizOwner = "Gonzo";
        source = "testFiles";
        l = new Loader(source);
        ArrayList<String> questions = l.getQuizQuestionsConfig(quizOwner,quizName);
        String q0 = questions.get(0);
        String q1 = questions.get(1);
        String q3 = questions.get(3);
        Question qu0 = l.getQuestionObject(quizOwner,quizName,q0);
        Question qu1 = l.getQuestionObject(quizOwner,quizName,q1);
        Question qu3 = l.getQuestionObject(quizOwner,quizName,q3);
        String[] ans0 = {"Hundreds of years","74 years","some years"};
        String[] ans1 = {"GoodClown inc.","Ford","Some Italian Car Maker"};
        String[] ans3 = {"None, Clowns dont exist", "Some", "As Many as you want"};
        for(String answer : ans0){
            qu0.addAnswer(answer);
        }
        for(String answer : ans1){
            qu1.addAnswer(answer);
        }
        for(String answer : ans3){
            qu3.addAnswer(answer);
        }
        qu0.setRightAnswer(2);
        qu1.setRightAnswer(1);
        qu3.setRightAnswer(2);
        s.saveAQuestionObject(qu0);
        s.saveAQuestionObject(qu1);
        s.saveAQuestionObject(qu3);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRun() throws Exception {

    }
}
