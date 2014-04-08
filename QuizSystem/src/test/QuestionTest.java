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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testQuestion() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddAnswer() {
		fail("Not yet implemented"); // TODO
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
