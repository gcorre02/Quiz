package test;

import static org.junit.Assert.*;

import org.junit.Test;

import quizData.Quiz;
import tools.CollectionTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionToolsTest {

	private Quiz quiz;
	private String quizName;
	private String question;

	public void setUp() throws Exception {
		quizName = "Alien";
		quiz = new Quiz(quizName,"Ridley");
		question = "Who is the director of the Alien film?";
	}

	public void tearDown() throws Exception {
        quiz = null;
        quizName = null;
        question = null;
	}

	@Test
	public final void testOrderQuestions() throws Exception {
		//setup
        setUp();
		quiz.addQuestion(question);
		quiz.addQuestion("another question");
		quiz.addQuestion("what happens to John Hurt in Alien ?");
		//expected
		String expected0 = "0 -> "+question
				+ "\n1 -> another question"
				+ "\n2 -> what happens to John Hurt in Alien ?\n";

        String expected1 = "1 -> "+question
                + "\n2 -> another question"
                + "\n3 -> what happens to John Hurt in Alien ?\n";

        String expectedS = "A -> "+question
                + "\nB -> another question"
                + "\nC -> what happens to John Hurt in Alien ?\n";

        //actual
		String actual0 = CollectionTools.collectionPrinter('0', quiz.getQuizQuestions());
        String actual1 = CollectionTools.collectionPrinter('1', quiz.getQuizQuestions());
        String actualS = CollectionTools.collectionPrinter('S', quiz.getQuizQuestions());

		//test
        assertEquals("The integer 0 based collection printer is not printing propperly",expected0, actual0);
        assertEquals("The integer 1 based collection printer is not printing propperly",expected1, actual1);
        assertEquals("The String based collection printer is not printing propperly",expectedS, actualS);
        tearDown();
	}
	@Test
	public final void testOrderQuestionsStartWith1() throws Exception {
		//setup
        setUp();
		quiz.addQuestion(question);
		quiz.addQuestion("another question");
		quiz.addQuestion("what happens to John Hurt in Alien ?");

		//expected
		String expected = "1 -> "+question
				+ "\n2 -> another question"
				+ "\n3 -> what happens to John Hurt in Alien ?\n";

		//actual
		String actual = CollectionTools.collectionPrinter('1', quiz.getQuizQuestions());

		//test
		assertEquals("The integer based collection printer is not printing propperly",expected, actual);

        tearDown();
    }
	@Test
	public final void testOrderQuestionsString() throws Exception {
        setUp();
		//setup
		quiz.addQuestion(question);
		quiz.addQuestion("another question");
		quiz.addQuestion("what happens to John Hurt in Alien ?");
		//expected
		String expected = "A -> "+question
				+ "\nB -> another question"
				+ "\nC -> what happens to John Hurt in Alien ?\n";

		//actual
		String actual = CollectionTools.collectionPrinter('S', quiz.getQuizQuestions());
		//debug
		//System.out.println(actual);
		//test
		assertEquals("The String based collection printer is not printing propperly",expected, actual);
	    tearDown();
    }

    @Test
    public final void testPrintMap(){
        //setup
        Map<String, String[]> printableMap = new HashMap<>();
        String[] one = {"one","two","three"};
        String[] two = {"two","three","four"};
        String[] three = {"three","four","five"};
        printableMap.put(one[0],one);
        printableMap.put(two[0],two);
        printableMap.put(three[0],three);
        //actual
        String actual = CollectionTools.printMap(printableMap);
        //expected
        String expected = "one\n" +
                "| one\n" +
                "| two\n" +
                "| three\n" +
                "two\n" +
                "| two\n" +
                "| three\n" +
                "| four\n" +
                "three\n" +
                "| three\n" +
                "| four\n" +
                "| five\n";
        //test
        assertEquals(expected,actual);
    }

    @Test
    public final void testCompareTwoArrays(){
        //setup
        String[] actual = {"one","two","three"};
        String[] expected = {"one","two","three"};


        //test
        assertTrue(CollectionTools.compareTwoArrays(actual,expected));
    }

    @Test
    public final void testCompareTwoArraysFailDifLengths(){
        //setup
        String[] actual = {"one","two","three"};
        String[] expected = {"one","two","three","Four"};

        //test
        assertFalse(CollectionTools.compareTwoArrays(actual,expected));
    }
    @Test
    public final void testCompareTwoArraysFailDifContent(){
        //setup
        String[] actual = {"one","two","three"};
        String[] expected = {"two","two","Four"};

        //test
        assertFalse(CollectionTools.compareTwoArrays(actual,expected));
    }

    @Test
    public final void testArrayContainsTrue(){
        //setup
        String[] stringArray = {"one","two","three"};
        String toCheck = "two";
        //test
        assertTrue(CollectionTools.arrayContains(toCheck,stringArray));
    }
    @Test
    public final void testArrayDoesntContainFalse(){
        //setup
        String[] stringArray = {"one","two","three"};
        String toCheck = "four";
        //test
        assertFalse(CollectionTools.arrayContains(toCheck,stringArray));
    }

    @Test
    public final void testAddElementToArray(){
        //setup
        String[] stringArray = {"one","two","three"};
        String toAdd = "four";
        //Expecteds
        String[] expecteds = {"one","two","three","four"};
        //Actuals
        String[] actuals = CollectionTools.addElementToArray(toAdd,stringArray);
        //test
        assertArrayEquals(expecteds,actuals);
    }

    @Test
    public final void testToArraYlist(){
        //setup
        String[] stringArray = {"one","two","three"};
        ArrayList<String> stringArrayList = CollectionTools.toArrayList(stringArray);
        //test
        for(int i = 0; i <3; i++){
            assertEquals(stringArray[i],stringArrayList.get(i));
        }
    }

    @Test
    public final void testRemoveElementFromArray(){
        //setup
        String[] stringArray = {"one","two","three"};
        String toRemove = "two";
        //Expecteds
        String[] expecteds = {"one","three"};
        //Actuals
        String[] actuals = CollectionTools.removeElementFromArray(toRemove, stringArray);
        //test
        assertArrayEquals(expecteds,actuals);
    }
}
