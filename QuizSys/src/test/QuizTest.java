package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizData.Question;
import quizData.Quiz;

public class QuizTest {
	Quiz quiz;
	String quizName;
	String question;

	@Before
	public void setUp() throws Exception {
		quizName = "Alien";
		quiz = new Quiz(quizName);
		question = "Who is the director of the Alien film?";
	}

	@After
	public void tearDown() throws Exception {
		quiz = null;
		quizName = null;
		question = null;
	}

	@Test
	public final void testQuiz() {
		//expected
		String expected = quizName;
		//actual
		String actual = quiz.getQuizName();
		//test
		assertTrue("The class Quiz is not instantiating propperly",quiz instanceof Quiz);
		assertEquals("The Quiz constructor is not passing the owner's name in propperly",expected, actual);
	}

	@Test
	public final void testAddQuestion() {
		//setup
		quiz.addQuestion(question);
		//expected
		String expected = question;
		//actual
		String actual = quiz.getQuizQuestions().get(0).getQuestionString();
		//test
		assertEquals("The addQuestion() is not behaving propperly",expected, actual);
	}

	@Test
	public final void testToString() {
		//setup
		String quizString = quiz.toString();
		//expected
		String expected = quizName;
		//actual
		String actual = quizString;
		//test
		assertEquals("toString() is not implemented",expected, actual);
	}
	
	/*
	 * the quiz name is changed by the already implemented by lombok method setQuizName
	 */
	@Test
	public final void testChangeQuizName(){
		//setup
		String newQuizName = "Alien 2";
		quiz.setQuizName(newQuizName);
		//expected
		String expected = newQuizName;
		//actual
		String actual = quiz.getQuizName();
		//test
		assertEquals("setQuizName() is not behaving as expected",expected, actual);
	}
	@Test
	public final void testRemoveQuestion(){
		//setup
		String removeableQuestion = "another question";
		quiz.addQuestion(question);
		quiz.addQuestion(removeableQuestion);
		quiz.removeQuestion(1);
		//test
		assertTrue(quiz.getQuizQuestions().size()==1);
	}
	@Test
	public final void testGetQuestion() {
		//setup
		quiz.addQuestion(question);
		//expected
		String expected = question;
		//actual
		Question q = quiz.getQuestion(0);
		String actual = q.getQuestionString();
		//test
		assertEquals("The getQuestion() is not returning a question",expected, actual);
	}
	
}
