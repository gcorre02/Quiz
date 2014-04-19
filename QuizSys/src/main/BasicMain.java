package main;

import tools.CollectionTools;
import tools.UserInterface;

//Client
public class BasicMain {
//TODO need to create a server Runner main and a process to get the folder from the server to set it here:

    public static void main(String[] args) {
        final String[] FOLDER = {"ExecTry"};
		UserInterface ui = new UserInterface();
        String[] strs = {"Go to Player Menus","Go to User Menus"};
        String choices = CollectionTools.collectionPrinter('S', CollectionTools.toArrayList(strs));
        char choice = ui.getUserAnswer(choices);
        if(choice == 'A')
            LaunchPlayerLocalVersion.main(FOLDER);
        else if(choice == 'B')
            LaunchUserLocalVersion.main(FOLDER);
        else
            System.out.println("Couldn't understand input. Bye");
    }


}
