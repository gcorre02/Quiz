package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import menu.EditQuizMenu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import persistence.Saver;
import quizData.Quiz;
import userInterface.UserInterface;

public class EditQuizMenuTest {
	private Quiz newQuiz;
	private EditQuizMenu eqm;
	private String user;
	private UserInterface ui;
	private String quizName;
	private Loader l;
	private Saver s;
	private ArrayList<String> quizQuestions;

	@Before
	public void setUp() throws Exception {
		//TODO need to setup the file system.
		String source = "testFiles";
		l = new Loader(source);
		s = new Saver(source);
		ui = new UserInterface();
		user = "Septimus";
		quizName = "Bond Villains";
		newQuiz = new Quiz(quizName, user);
		eqm = new EditQuizMenu(l, s, ui, user, quizName);
		quizQuestions = new ArrayList<>();
		quizQuestions.add("Which bond villain has a massive jaw?");
		quizQuestions.add("Which bond villain has a golden penis?");
		quizQuestions.add("Who is the sexiest bond villain?");
		quizQuestions.add("Which bond villain is actually a nice person?");
		newQuiz.setQuizQuestions(quizQuestions);
		s.addQuiz(quizName, user, l.getUserQuizzes());
		s.saveQuiz(newQuiz);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testRun() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRunMenu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditQuizMenu() {
		fail("Not yet implemented"); // TODO
	}

}
