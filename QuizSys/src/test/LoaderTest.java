package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;
import persistence.Saver;
import tools.CollectionPrinter;

public class LoaderTest {
	Loader loader;
	String folder;
	@Before
	public void setUp() throws Exception {
		folder = "TestFiles";
		loader = new Loader(folder);
	}

	@After
	public void tearDown() throws Exception {
		loader = null;
	}

	@Test
	public final void testLoader() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUsernames() throws IOException {
		//setup
		String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
		ArrayList<String> userNames = new ArrayList<>();
		for(String name : names){
			userNames.add(name);
		}
		Saver s = new Saver(folder);
		s.saveUserNames(userNames);
		
		ArrayList<String> retrievedUserNames = new ArrayList<>();

		retrievedUserNames = loader.getUsernames();
		//debug
		System.out.println(retrievedUserNames.toString());
		//expecteds
		String[] expecteds = userNames.toArray(new String[0]); 
		//actuals
		String[] actuals = retrievedUserNames.toArray(new String[0]); 
		//test
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public final void testGetUserQuizzesMap(){
		//setup
		String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
		Map<String, String[]> userQuizzes = new HashMap<>();
		String[] gonzoQuizzes = {"numbers","people","cars"};
		for(String name : names){
			userQuizzes.put(name, new String[0]);
		}
		userQuizzes.put("Gonzo", gonzoQuizzes);
		Saver s = new Saver(folder);
		s.saveUserQuizzes(userQuizzes);
		//expected
		String[] expecteds = gonzoQuizzes;
		String[] expectedKeys = names;
		//actual
		String[] actuals = loader.getUserQuizzes().get("Gonzo");
		String[] actualKeys =loader.getUserQuizzes().keySet().toArray(new String[0]);
		//debug
		System.out.println(CollectionPrinter.printMap(loader.getUserQuizzes()));
		//test
		assertArrayEquals(expecteds, actuals);
		assertTrue(CollectionPrinter.compareTwoArrays(actualKeys, expectedKeys));
	}



}
