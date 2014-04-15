package player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import persistence.Saver;
import quizData.Player;
import quizData.Question;
import tools.CollectionPrinter;
import userInterface.UserInterface;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    UserInterface ui;
    String player;

    @Before
    public void setUp() throws Exception {
        String quizName = "cars";
        String quizOwner = "Gonzo";
        source = "testFiles";
        l = new Loader(source);

        //setup questions data
        ArrayList<String> questions = l.getQuizQuestionsConfig(quizOwner, quizName);
        String q0 = questions.get(0);
        String q1 = questions.get(1);
        String q3 = questions.get(3);
        String[] ans0 = {"Hundreds of years","74 years","some years"};
        String[] ans1 = {"GoodClown inc.","Ford","Some Italian Car Maker"};
        String[] ans3 = {"None, Clowns dont exist", "Some", "As Many as you want"};

        //create Question objects

        Question qu0 = new Question(q0,CollectionPrinter.toArrayList(ans0),2, quizOwner,quizName);
        Question qu1 = new Question(q1,CollectionPrinter.toArrayList(ans1),1, quizOwner,quizName);
        Question qu3 = new Question(q3,CollectionPrinter.toArrayList(ans3),2, quizOwner,quizName);

        //persist question objects
        s = new Saver(source);
        s.saveAQuestionObject(qu0);
        s.saveAQuestionObject(qu1);
        s.saveAQuestionObject(qu3);

        //setup player obj
        player = "Esteban";
        Player playa = new Player(player);
        ps = new PlayerSaver(source);
        ps.savePlayer(playa);
        //setup PlayAQuizMenu obj
        ui = mock(UserInterface.class);

        pl = new PlayerLoader(source);


        pqm = new PlayAQuizMenu(ui,pl,ps,player,quizOwner,quizName);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRun() throws Exception {
        //TODO working, need assert.

        //mock
        when(ui.readFromUser()).thenReturn("1");
        //exec
        pqm.run();
    }
}
