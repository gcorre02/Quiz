package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizData.Question;

public class QuestionTest {
	ArrayList<String> answers;
	String question;
	Question q;
	String ans1, ans2, ans3, ans4;

	@Before
	public void setUp() throws Exception {
		ans1 = "a baby";
		ans2 = "a baby alien";
		ans3 = "a banana";
		ans4 = "a blood thursty teddy bear";
		answers = new ArrayList<>();
		answers.add(ans1);
		answers.add(ans2);
		answers.add(ans3);
		answers.add(ans4);
		question = "What comes out of John Hurt's tummy?";
		q = new Question(question);
		q.setAnswers(answers);
	}

	@After
	public void tearDown() throws Exception {
		ans1 = null;
		ans2 = null;
		ans3 = null;
		ans4 = null;
		answers = null;
		question = null;
		q =  null;
	}

	@Test
	public final void testQuestion() {
		//expected
		String expected = question;
		//actual
		String actual = q.getQuestionString();
		//test
		assertTrue("The class Question is not instantiating propperly",q instanceof Question);
		assertEquals("The Question constructor is not passing the owner's name in propperly",expected, actual);	}

	@Test
	public final void testAddAnswer() {
		//setup
		String answerString = "some other answer";
		q.addAnswer(answerString);
		//expected
		String expected = answerString;
		//actual
		String actual = q.getAnswer(4);
		//test
		assertEquals("The addQuestion() is not behaving propperly",expected, actual);
	}

	@Test
	public final void testRemoveAnswer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAnswer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
