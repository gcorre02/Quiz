package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import tools.CollectionPrinter;
import userInterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
        String[] menuArray = {"Browse User Quizzes", "Play a Quiz", "Played Quizzes", "Logout"};
        ArrayList<String> menuItems = new ArrayList<>();
        Collections.addAll(menuItems, menuArray);
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
                //createNewPlayer();
                System.out.println("These are the selected user's quizzes: ");
                run();
                break;
            case 'B':
                System.out.println("Please choose a quiz to play: ");
              //  deletePlayer();
                run();
                break;
            case 'C':
                System.out.println("These are the quizzes you have played:");
            //    login();
                run();
                break;
            case 'D':
                System.out.println("Thank you for playing. Goodbye");
          //      closeProgram();
                break;
            default:
                System.out.println("Couldn't understand the input, please choose again.");
                run();
                break;
        }

    }
}
