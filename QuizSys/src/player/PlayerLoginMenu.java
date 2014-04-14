package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import tools.CollectionPrinter;
import userInterface.UserInterface;

import java.util.ArrayList;

/**
 * Created by Guilherme on 14-04-2014.
 */
@AllArgsConstructor
public class PlayerLoginMenu {
    private PlayerLoader l;
    private PlayerSaver s;
    private UserInterface ui;

    public void run(){
        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("New Player");
        menuItems.add("Delete Player");
        menuItems.add("Login");
        menuItems.add("Close Program");
        String menu = CollectionPrinter.collectionPrinter('S', menuItems);
        runMenu(menu);
    }

    private void runMenu(String menu){
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

    private void closeProgram() {
        //TODO
        System.out.println("Thank you for Playing! ");
    }

    private void login() {
        //TODO
        System.out.println("Please enter your name : ");
    }

    private void deletePlayer() {
        //TODO
        System.out.println("Please enter the name of the player to delete:");
    }

    private void createNewPlayer() {
        //TODO
        System.out.println("Please enter the new player name :");
    }

}
