package test.menuTests.test.local;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import menu.EditQuizMenu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import persistence.Loader;
import persistence.Saver;
import quizData.Quiz;
import tools.UserInterface;

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
		ui = mock(UserInterface.class);
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
		//TODO remove files from system...
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
	//stub question dellete, needs a propper test
	@Test
	public final void testDeleteQuestion() throws IOException{
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('B','D');
		when(ui.readFromUser()).thenReturn("0");
		//exec
		eqm.run();
		//test

		//Quiz quiz = l.getQuizObject(user, quizName);
		//System.out.println(CollectionTools.collectionPrinter('0', quiz.getQuizQuestions()));
		//System.out.println(CollectionTools.collectionPrinter('S',l.getQuizQuestionsConfig(user, quizName)));

		fail("Not yet implemented"); // TODO

	}

	//stub question dellete, needs a propper test
	@Test
	public final void testAddQuestion() throws IOException{
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('A','D');
		when(ui.readFromUser()).thenReturn("What type of moron is Moe Sizlack?");
		//exec
		eqm.run();
		//test

		//Quiz quiz = l.getQuizObject(user, quizName);
		//System.out.println(CollectionTools.collectionPrinter('0', quiz.getQuizQuestions()));
		//System.out.println(CollectionTools.collectionPrinter('S',l.getQuizQuestionsConfig(user, quizName)));

		fail("Not yet implemented"); // TODO

	}

	//stub question dellete, needs a propper test
	@Test
	public final void testEditQuestion() throws IOException{
		//setup
		when(ui.getUserAnswer(anyString())).thenReturn('C','D','D');
		when(ui.readFromUser()).thenReturn("0");
		//exec
		eqm.run();
		//test

		//Quiz quiz = l.getQuizObject(user, quizName);
		//System.out.println(CollectionTools.collectionPrinter('0', quiz.getQuizQuestions()));
		//System.out.println(CollectionTools.collectionPrinter('S',l.getQuizQuestionsConfig(user, quizName)));

		fail("Not yet implemented"); // TODO

	}
}
