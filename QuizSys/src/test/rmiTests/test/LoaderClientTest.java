package test.rmiTests.test;


import org.junit.*;
import quizData.Question;
import quizData.Quiz;
import rmi.LoaderClient;
import rmi.LoaderServerLauncher;
import tools.CollectionTools;

import java.util.ArrayList;

import static junit.framework.TestCase.fail;

/**
 * needs -Djava.security.policy=security.policy as jvm argument and for the security policy to be in the file system @ source(\...).
 *
 * at the moment, these tests only run properly with the server run outside of intelliJ.
 *
 * Created by Admin on 16/04/2014.
 */
public class LoaderClientTest {
    LoaderClient lc;
    static LoaderServerLauncher lsl;
    @BeforeClass
    public static void setupBeforeClass(){
        //server is working for all tests
        lsl = new LoaderServerLauncher();
        lsl.main(new String[0]);
    }

    @Before
    public void setUp() throws Exception {
        lc = new LoaderClient();


    }

    @After
    public void tearDown() throws Exception {
       // lsl = null;
    }
    @AfterClass
    public static void shutDown(){
        System.exit(0);
    }

    @Test
    public void testMain() throws Exception {
        lc.main(new String[0]);
        fail();
    }

    @Test //TODO impl file system.
    public void testRun() {
        //this test calls the getUserNames() array.
        String callClass = "persistence.Loader";
        String callMethod = "getUsernames";
        ArrayList<String> usernames = null;
        try {
            usernames = lc.run(callClass, callMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(usernames);

        fail();
    }

    @Test //TODO impl file system.
    public void testRunGetADataQuizObj() throws Exception { //TODO also test getting a map, getUserQuizzes...
        //this test calls the getUserNames() array.
        String callClass = "persistence.Loader";
        String callMethod = "getQuizObject";
        String quizName = "cars";
        String quizOwner = "Gonzo";
        Quiz quiz = lc.run(callClass, callMethod, quizOwner, quizName);
        System.out.println(quiz.getOwner() +"\n"+ quiz.getQuizName() + "\n" + CollectionTools.collectionPrinter('S', quiz.getQuizQuestions()));
           //TODO serialize quizData, make tests run !(config ?)
        fail();
    }

    @Test //TODO impl file system.
    public void testRunSaveQuizObj() throws Exception {
        //this test calls the getUserNames() array.
        String callClass = "persistence.Saver";
        String callMethod = "saveQuiz";
        String quizName = "cars";
        String quizOwner = "Gonzo";
        String callClass1 = "persistence.Loader";
        String callMethod1 = "getQuizObject";
        Quiz q = new Quiz(quizName,quizOwner);
        ArrayList<String> questions = new ArrayList<>();
        questions.add("Different Server created question");
        q.setQuizQuestions(questions);
        lc.run(callClass, callMethod, q);
        Quiz quiz = lc.run(callClass1, callMethod1, quizOwner, quizName);
        System.out.println(quiz.getOwner() +"\n"+ quiz.getQuizName() + "\n" + CollectionTools.collectionPrinter('S', quiz.getQuizQuestions()));
        //TODO serialize quizData, make tests run !(config ?)
        fail();
    }

    @Test //TODO impl file system.
    public void testRunGetADataQuestionObj() throws Exception {
        //this test calls the getUserNames() array.
        String callClass = "persistence.Loader";
        String callMethod = "getQuestionObject";
        String quizName = "Another Bond";
        String quizOwner = "Guy Fawlkes";
        String questionString = "Who is the sexiest bond villain?";
        Question question = lc.run(callClass, callMethod, quizOwner, quizName, questionString);  //getQuestionObject(String owner, String quiz, String questionString)
        System.out.println(question.getOwner() +"\n"+ question.getQuestionString() + "\n" + CollectionTools.collectionPrinter('S', question.getAnswers()));
        //TODO serialize quizData, make tests run !(config ?)
        fail();
    }
}
