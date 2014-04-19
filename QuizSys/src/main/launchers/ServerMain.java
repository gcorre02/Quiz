package main.launchers;

import main.LocalUserLaunchers.LaunchPlayerServerVersion;
import main.LocalUserLaunchers.LaunchUserServerVersion;
import tools.CollectionTools;
import tools.UserInterface;

//Client
public class ServerMain {
//TODO need to create a server Runner main and a process to get the folder from the server to set it here:
//STUB
    public static void main(String[] args) {
        final String[] FOLDER = args;//{"testFiles"};//TODO make this an input from the server
		UserInterface ui = new UserInterface();

        String[] strs = {"Go to Player Menus","Go to User Menus"};
        String choices = CollectionTools.collectionPrinter('S', CollectionTools.toArrayList(strs));
        char choice = ui.getUserAnswer(choices);
        if(choice == 'A')
            LaunchPlayerServerVersion.main(FOLDER);
        else if(choice == 'B')
            LaunchUserServerVersion.main(FOLDER);
        else
            System.out.println("Couldn't understand input. Bye");
    }


}
