package test.persistenceTests.test.local;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.Saver;
import quizData.Quiz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Test class for Loader
 */
public class LoaderTest {
	Loader loader;
	String folder;
	String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
	ArrayList<String> userNames ;
	Saver s;
	String[] gonzoQuizzes = {"numbers","people","cars"};
	@Before
	public void setUp() throws Exception {
		folder = "testFiles";
		loader = new Loader(folder);

		userNames = new ArrayList<>();
        Collections.addAll(userNames, names);

		
	}

	@After
	public void tearDown() throws Exception {
		loader = null;
		folder = null;
		userNames = null;
		s = null;
	}

	@Test
	public final void testLoader() {
		assertTrue(loader != null);
		File f = new File(folder);
		assertTrue(f.exists());
	}

	@Test
	public final void testGetUsernames() throws IOException {
		//setup
		ArrayList<String> retrievedUserNames = loader.getUsernames();

		//debug
		System.out.println(retrievedUserNames.toString());

		//expecteds
		String[] expecteds = userNames.toArray(new String[userNames.size()]);
        ArrayList<String> expectedsList = new ArrayList<String>();
        Collections.addAll(expectedsList,expecteds);
		//actuals
		String[] actuals = retrievedUserNames.toArray(new String[retrievedUserNames.size()]);
        ArrayList<String> actualsList = new ArrayList<String>();
        Collections.addAll(actualsList,actuals);
		//test
		assertTrue(actualsList.containsAll(expectedsList));
	}

	@Test
	public final void testGetUserQuizzesMap(){
		//expected
		String[] expecteds = gonzoQuizzes;
		String[] expectedKeys = names;
		//actual
		String[] actuals = loader.getUserQuizzes().get("Gonzo");
        Set<String> strings = loader.getUserQuizzes().keySet();
        String[] actualKeys = strings.toArray(new String[strings.size()]);
        //toLists
        ArrayList<String> expectedList = new ArrayList<>();
        Collections.addAll(expectedList,expecteds);
        ArrayList<String> expectedKeysList = new ArrayList<>();
        Collections.addAll(expectedKeysList,expectedKeys);
        ArrayList<String> actualList = new ArrayList<>();
        Collections.addAll(actualList,actuals);
        ArrayList<String> actualKeysList = new ArrayList<>();
        Collections.addAll(actualKeysList,actualKeys);
        //test
        assertTrue(actualList.containsAll(expectedList));
        assertTrue(actualKeysList.containsAll(expectedKeysList));
	}


	@Test
	public final void testGetQuizConfig(){
		//setup
		Quiz quiz = generateCarsQuiz();
		Quiz newQuiz = loader.getQuizObject("Gonzo", "cars");
		//expected
		String expected = quiz.getQuizName();
		//actual
		String actual = newQuiz.getQuizName();
		//test
		assertEquals(expected, actual);
	}
    private Quiz generateCarsQuiz() {
        String name = "cars";
        String owner = "Gonzo";
        Quiz quiz = new Quiz(name, owner);
        ArrayList<String> quizQuestions = new ArrayList<>();
        quizQuestions.add("How old is VW?");
        quizQuestions.add("What was the first big car maker?");
        quizQuestions.add("What brand is the batmobile?");
        quiz.setQuizQuestions(quizQuestions);
        return quiz;
    }
}
