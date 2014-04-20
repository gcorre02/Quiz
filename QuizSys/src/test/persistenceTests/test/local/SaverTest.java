package test.persistenceTests.test.local;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.Saver;
import quizData.Question;
import quizData.Quiz;
import tools.CollectionTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test Class for the Saver class.
 */
public class SaverTest {
	ArrayList<String> userNames;
	Saver s;
	String folder;
	@Before
	public void setUp() throws Exception {
		String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
		userNames = new ArrayList<>();
		for(String name : names){
			userNames.add(name);
		}
		folder = "testFiles";
		s = new Saver(folder);

	}

	@After
	public void tearDown() throws Exception {
		userNames = null;
		s = null;
		folder = null;
	}

	@Test
	public final void testSaver() {
		//setup
		String sourceFolder = s.getSource();
		File file = new File(sourceFolder);
		//expected
		String expected = folder;
		//actual
		String actual = sourceFolder;
		//test
		assertTrue(file.exists());
		assertEquals(expected, actual);
	}

	@Test
	public final void testSaveUserNames() {
		assertTrue(s.saveUserNames(userNames));
	}

	@Test
	public final void testAddUserNames() throws IOException {
		String newUser = "Guy Fawlkes";
		assertTrue(s.addUserName(newUser));
		File f = new File(folder + File.separator + newUser);
		assertTrue(f.exists());
	}
	@Test
	public final void testDeleteUserNames() throws IOException {
		String newUser = "DeleteableUser";
		s.addUserName(newUser);
		assertTrue(s.deleteUser(newUser));
		File f = new File(folder + File.separator + newUser);
		assertFalse(f.exists());
	}

	@Test
	public final void testSaveUserQuizzes() {
		Map<String, String[]> userQuizzes = generateGonzoQuizzes();
		//test
		assertTrue(s.saveUserQuizzes(userQuizzes));
	}

	@Test
	public final void testAddQuizz(){
		Map<String, String[]> userQuizzes = generateGonzoQuizzes();
		String newQuiz = "Sexy Sailors of the 20th Century";
		String user = "Bartolomeu";

		//expected
		String expected = newQuiz;

		Loader l = new Loader(folder);
		//test
		assertTrue(s.addQuiz(newQuiz, user, userQuizzes));
		String actual = l.getUserQuizzes().get(user)[0];
		assertEquals(expected, actual);
	}
	@Test
	public final void testRemoveQuiz(){
		//Setup
		Map<String, String[]> userQuizzes = generateGonzoQuizzes();
		Loader l = new Loader(folder);
		File removedFile = new File(folder+File.separator+"Gonzo"+File.separator+"people");
		//test
		assertTrue(s.removeQuiz("people", "Gonzo", userQuizzes));
		assertFalse(CollectionTools.arrayContains("people", l.getUserQuizzes().get("Gonzo")));
		assertFalse(removedFile.exists());
	}

	private Map<String, String[]> generateGonzoQuizzes() {
		Map<String, String[]> userQuizzes = new HashMap<>();
		String[] gonzoQuizzes = {"numbers","people","cars"};
		for(String name : userNames){
			userQuizzes.put(name, new String[0]);
		}
		userQuizzes.put("Gonzo", gonzoQuizzes);
		return userQuizzes;
	}
	@Test
	public final void testSaveQuiz() {
		//setup
		Quiz quiz = setupQuiz();
		//test
		assertTrue(s.saveQuiz(quiz));
	}

	private Quiz setupQuiz() {
		s.saveUserNames(userNames);
		Map<String, String[]> userQuizzes = generateGonzoQuizzes();
		s.saveUserQuizzes(userQuizzes);
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

	@Test
	public final void testAddQuestion() throws IOException{
		//setup
		Quiz quiz = setupQuiz();
		String newQuestion = "how many clowns fit in a Mini ?";
		s.addAQuestion(newQuestion, quiz.getOwner(), quiz.getQuizName());
		//expected
		String expected = newQuestion;
		//actual
		Loader l = new Loader(folder);
		
		String actual = l.getQuizQuestionsConfig(quiz.getOwner(), quiz.getQuizName()).get(3);

		//test

		File f = new File(folder + File.separator + quiz.getOwner()+ File.separator +quiz.getQuizName()+ File.separator + "3.txt");
		assertTrue(f.exists());
		assertEquals(expected, actual);
	}

	@Test
	public final void testRemoveQuestion() throws IOException{
		//setup
        Loader l = new Loader(folder);
        String removeableQuestion = "Remove";
        s.addAQuestion(removeableQuestion,"Gonzo","cars");
        assertTrue(l.getQuizObject("Gonzo", "cars").getQuizQuestions().contains(removeableQuestion));
        s.removeAQuestion(removeableQuestion, "Gonzo", "cars");

		//test
		assertTrue(l.getQuizQuestionsConfig("Gonzo", "cars").size()==2);
		assertFalse(l.getQuizObject("Gonzo", "cars").getQuizQuestions().contains(removeableQuestion));
	}
	
	@Test
	public final void testSaveQuestionToJson() throws IOException{
		//setup
		setupQuiz();
		String questionString = "What brand is the batmobile?";
		ArrayList<String> answers = new ArrayList<>();
		int rightAnswer = 1;
		String owner = "Gonzo";
		String quiz = "cars";
		answers.add("Pingu Industries");
		answers.add("it's home made by Albert");
		answers.add("Something else");
		Question question = new Question(questionString, answers, rightAnswer, owner, quiz);
		//debug
		s.saveAQuestionObject(question);
		//expected
		Question expected = question;
		String[] expecteds = question.getAnswers().toArray(new String[0]);
		//actual
		Loader l = new Loader(folder);
		Question actual = l.getQuestionObject(owner, quiz, questionString);
		String[] actuals = actual.getAnswers().toArray(new String[0]);
		//debug
		System.out.println(CollectionTools.collectionPrinter('1', actual.getAnswers()));
		//test
		assertEquals(expected, actual);
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public final void checkNewSaverDoensntOverwriteExistingFileSystem() throws IOException{
		//setup
		s = new Saver(folder);
		s = null;
		Loader l = new Loader(folder);
		//expected
		ArrayList<String> expecteds = userNames;
		//actual
		ArrayList<String> actuals = l.getUsernames();
		//test
		assertEquals(expecteds, actuals);
	}
}
