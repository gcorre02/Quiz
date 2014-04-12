package test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import menu.EditQuestionMenu;
import menu.EditQuizMenu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import persistence.Saver;
import quizData.Question;
import quizData.Quiz;
import userInterface.UserInterface;

public class EditQuestionMenuTest {

	private Loader l;
	private Saver s;
	private UserInterface ui;
	private String user;
	private String quizName;
	private Quiz newQuiz;
	private EditQuestionMenu eqm;
	private ArrayList<String> quizQuestions;
	private Question updatableQuestion;
	private ArrayList<String> answers;
	String questionString;
	
	@Before
	public void setUp() throws Exception {
		//TODO need to setup the file system.
		String source = "testFiles";
		s = new Saver(source);
		l = new Loader(source);
		ui = mock(UserInterface.class);
		user = "Guy Fawlkes";
		s.addUserName(user);
		quizName = "Another Bond";
		newQuiz = new Quiz(quizName, user);
		quizQuestions = new ArrayList<>();
		questionString ="Who is the sexiest bond villain?";
		quizQuestions.add("Which bond villain has a massive jaw?");
		quizQuestions.add("Which bond villain has a golden penis?");
		quizQuestions.add(questionString);
		quizQuestions.add("Which bond villain is actually a nice person?");
		newQuiz.setQuizQuestions(quizQuestions);
		s.addQuiz(quizName, user, l.getUserQuizzes());
		s.saveQuiz(newQuiz);
		answers = new ArrayList<String>();
		answers.add("Odd Job");
		answers.add("Octopussy");
		answers.add("Bane");
		answers.add("MoneyPenny");
		updatableQuestion = new Question(questionString, answers, 1, user, quizName);
		s.saveAQuestionObject(updatableQuestion);
		eqm = new EditQuestionMenu(questionString,l, s, ui, user, quizName);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testRun() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditQuestionMenu() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testChangeRightAnswerInt() throws IOException {
		//Setup
		when(ui.getUserAnswer(anyString())).thenReturn('C','D');
		when(ui.readFromUser()).thenReturn("0");
		eqm.run();
		//expected
		int expected = 0;
		//actual
		int actual = l.getQuestionObject(user, quizName, questionString).getRightAnswer();
		//test
		assertEquals(expected, actual);
	}
}
