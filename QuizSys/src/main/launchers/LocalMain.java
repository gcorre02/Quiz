package main.launchers;

import main.LocalUserLaunchers.LaunchPlayerLocalVersion;
import main.LocalUserLaunchers.LaunchUserLocalVersion;
import tools.CollectionTools;
import tools.UserInterface;

//Local
public class LocalMain {
//TODO need to create a server Runner main and a process to get the folder from the server to set it here:

    public static void main(String[] args) {
        final String[] FOLDER = args;//{"ExecTry"};
		UserInterface ui = new UserInterface();
        launch(FOLDER[0], ui);
    }

    /*
     * method separated from main for testing.
     *
     */
    public static void launch(String source, UserInterface ui){
        String[] strs = {"Go to Player Menus","Go to User Menus"};
        String choices = CollectionTools.collectionPrinter('S', CollectionTools.toArrayList(strs));
        char choice = ui.getUserAnswer(choices);
        if(choice == 'A')
            LaunchPlayerLocalVersion.main(new String[]{source});
        else if(choice == 'B')
            LaunchUserLocalVersion.main(new String[]{source});
        else
            System.out.println("Couldn't understand input. Bye");
    }


}
