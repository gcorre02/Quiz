package test;

import static org.junit.Assert.*;

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
		ArrayList<String> usernames = loader.getUsernames();
		System.out.println(usernames.toString());
	}

}
