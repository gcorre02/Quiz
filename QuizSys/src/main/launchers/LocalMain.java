package main.launchers;

import main.LocalUserLaunchers.LaunchPlayerLocalVersion;
import main.LocalUserLaunchers.LaunchUserLocalVersion;
import tools.CollectionTools;
import tools.UserInterface;

/**
 * Launches a local version of the Quiz System.
 */
public class LocalMain {
    /**
     *
     * @param args the folder where the quiz file system is. if the folder does not exist or is empty, a new one is created.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        launch(args[0], ui);
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
            ui.printToUser("Couldn't understand input. Bye");
    }


}
