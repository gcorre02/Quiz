package main;

import persistence.Loader;
import persistence.Saver;
import userInterface.UserInterface;
import menu.LoginMenu;

public class BasicMain {
	
	public static void main(String[] args) {
		final String FOLDER = "ExecTry";
		Saver s = new Saver(FOLDER);
		Loader l = new Loader(FOLDER);
		UserInterface ui = new UserInterface();
		LoginMenu lm = new LoginMenu(l,s,ui);
		lm.run();
	}

}
