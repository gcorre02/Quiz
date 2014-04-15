package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import tools.CollectionPrinter;
import userInterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 15/04/2014.
 */
@AllArgsConstructor
public class PlayerMenu {
    String playerName;
    UserInterface ui;
    PlayerLoader pl;
    PlayerSaver ps;

    public void run() {
        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("New Player");
        menuItems.add("Delete Player");
        menuItems.add("Login");
        menuItems.add("Close Program");
        String menu = CollectionPrinter.collectionPrinter('S', menuItems);
        runMenu(menu);
    }

    private void runMenu(String menu) {
        //TODO Stub
        char choice = ui.getUserAnswer(menu);
        //debug
        System.out.println(choice);
        //\debug
        switch(choice){
            case 'A':
                    createNewPlayer();
                run();
                break;
            case 'B':
                    deletePlayer();
                run();
                break;
            case 'C':
                login();
                run();
                break;
            case 'D':
                closeProgram();
                break;
            default:
                System.out.println("Couldn't understand the input, please choose again.");
                run();
                break;
        }

    }
}
