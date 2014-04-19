package main.launchers;

import main.LocalUserLaunchers.LaunchPlayerServerVersion;
import main.LocalUserLaunchers.LaunchUserServerVersion;
import tools.CollectionTools;
import tools.UserInterface;

//Client
public class ServerMain {
//TODO need to create a server Runner main and a process to get the folder from the server to set it here:
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        launch(args[0], ui);
    }

    /**
     * Separated from main to allow testing.
     * @param source the folder where the Quiz system file structure is.
     * @param ui UserInterface for mocking inputs.
     */
    public static void launch(String source, UserInterface ui) {
        String[] strs = {"Go to Player Menus","Go to User Menus"};
        String choices = CollectionTools.collectionPrinter('S', CollectionTools.toArrayList(strs));
        char choice = ui.getUserAnswer(choices);
        if(choice == 'A')
            LaunchPlayerServerVersion.main(new String[]{source});
        else if(choice == 'B')
            LaunchUserServerVersion.main(new String[]{source});
        else
            System.out.println("Couldn't understand input. Bye");
    }


}
