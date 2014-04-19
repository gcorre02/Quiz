package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizData.Quiz;
import tools.CollectionTools;

public class CollectionToolsTest {

	private Quiz quiz;
	private String quizName;
	private String question;

	@Before
	public void setUp() throws Exception {
		quizName = "Alien";
		quiz = new Quiz(quizName,"Ridley");
		question = "Who is the director of the Alien film?";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testOrderQuestions() {
		//setup
		quiz.addQuestion(question);
		quiz.addQuestion("another question");
		quiz.addQuestion("what happens to John Hurt in Alien ?");
		//expected
		String prints = "0 -> "+question
				+ "\n1 -> another question"
				+ "\n2 -> what happens to John Hurt in Alien ?\n";
		String expected = prints;
		//actual
		String actual = CollectionTools.collectionPrinter('0', quiz.getQuizQuestions());
		//debug
		//System.out.println(actual);
		//test
		assertEquals("The integer based collection printer is not printing propperly",expected, actual);
	}
	@Test
	public final void testOrderQuestionsStartWith1() {
		//setup
		quiz.addQuestion(question);
		quiz.addQuestion("another question");
		quiz.addQuestion("what happens to John Hurt in Alien ?");
		//expected
		String prints = "1 -> "+question
				+ "\n2 -> another question"
				+ "\n3 -> what happens to John Hurt in Alien ?\n";
		String expected = prints;
		//actual
		String actual = CollectionTools.collectionPrinter('1', quiz.getQuizQuestions());
		//debug
		//System.out.println(actual);
		//test
		assertEquals("The integer based collection printer is not printing propperly",expected, actual);
	}
	@Test
	public final void testOrderQuestionsString() {
		//setup
		quiz.addQuestion(question);
		quiz.addQuestion("another question");
		quiz.addQuestion("what happens to John Hurt in Alien ?");
		//expected
		String prints = "A -> "+question
				+ "\nB -> another question"
				+ "\nC -> what happens to John Hurt in Alien ?\n";
		String expected = prints;
		//actual
		String actual = CollectionTools.collectionPrinter('S', quiz.getQuizQuestions());
		//debug
		//System.out.println(actual);
		//test
		assertEquals("The String based collection printer is not printing propperly",expected, actual);
	}
}
