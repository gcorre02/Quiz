package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import persistence.Saver;
import quizData.Quiz;
import tools.CollectionPrinter;

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
		//s.deleteFolder(folder); TODO <make it available for general testing>
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
	//these two tests are trying to be handled concurrently by the system, which forces them to wait for each other.
	@Test
	public final void testAddUserNames() throws IOException {
	//	s.saveUserNames(userNames);
		String newUser = "Guy Fawlkes";
		assertTrue(s.addUserName(newUser));
		File f = new File(folder + File.separator + newUser);
		assertTrue(f.exists());
	}
	@Test
	public final void testDeleteUserNames() throws IOException {
		//s.saveUserNames(userNames);
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
		assertFalse(CollectionPrinter.arrayContains("people", l.getUserQuizzes().get("Gonzo")));
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
	//@Test
	public final void testSaveQuiz() {
		//setup
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
		//test
		assertTrue(s.saveQuiz(quiz));
	}

}
