package main;

import persistence.Loader;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import persistence.Saver;
import player.PlayerLoginMenu;
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
        PlayerSaver ps = new PlayerSaver(FOLDER);
        PlayerLoader pl = new PlayerLoader(FOLDER);
        PlayerLoginMenu plm = new PlayerLoginMenu(pl,ps,ui);
        plm.run();
	}

}
