package test.persistenceTests.test.local;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.Saver;
import quizData.Quiz;
import tools.CollectionTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

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
		assertTrue(loader instanceof Loader);
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
		String[] expecteds = userNames.toArray(new String[0]); 

		//actuals
		String[] actuals = retrievedUserNames.toArray(new String[0]); 
		//test
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public final void testGetUserQuizzesMap(){
		//expected
		String[] expecteds = gonzoQuizzes;
		String[] expectedKeys = names;
		//actual
		String[] actuals = loader.getUserQuizzes().get("Gonzo");
		String[] actualKeys =loader.getUserQuizzes().keySet().toArray(new String[0]);
		//debug
		System.out.println(CollectionTools.printMap(loader.getUserQuizzes()));
		//test
		assertArrayEquals(expecteds, actuals);
		assertTrue(CollectionTools.compareTwoArrays(actualKeys, expectedKeys));
	}


	@Test
	public final void testGetQuizConfig(){
		//setup
		Quiz quiz = generateCarsQuiz();
		Quiz newQuiz = loader.getQuizObject("Gonzo", "cars");
		//expected
		Quiz expected = quiz;
		//actual
		Quiz actual = newQuiz;
		//debug
		System.out.println(CollectionTools.collectionPrinter('0', actual.getQuizQuestions()));
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

	

	//@Test
	public final void testSaveQuestionToJson() throws IOException{
		//TODO <already tested by saver. create if time allows it>
		fail();
	}

}
