package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.Loader;

public class LoaderTest {
	Loader loader;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testLoader() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetUsernames() {
		loader = new Loader("TestFiles");
		ArrayList<String> usernames = new ArrayList<>();
		try {
			usernames = loader.getUsernames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(usernames.toString());
	}

}
