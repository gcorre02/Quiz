package main;

import persistence.Loader;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import persistence.Saver;
import player.PlayerLoginMenu;
import tools.CollectionPrinter;
import userInterface.UserInterface;
import menu.LoginMenu;

import java.util.ArrayList;

public class BasicMain {
	
	public static void main(String[] args) {
        final String[] FOLDER = {"ExecTry"};
		UserInterface ui = new UserInterface();
        String[] strs = {"Go to Player Menus","Go to User Menus"};
        String choices = CollectionPrinter.collectionPrinter('S',CollectionPrinter.toArrayList(strs));
        char choice = ui.getUserAnswer(choices);
        if(choice == 'A')
            LaunchPlayer.main(FOLDER);
        else if(choice == 'B')
            LaunchUser.main(FOLDER);
        else
            System.out.println("Couldn't understand input. Bye");
    }

}
