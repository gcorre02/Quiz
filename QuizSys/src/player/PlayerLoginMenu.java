package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoaderInterface;
import persistence.PlayerSaverInterface;
import tools.CollectionTools;
import userInterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Guilherme on 14-04-2014.
 */
@AllArgsConstructor
public class PlayerLoginMenu {
    private PlayerLoaderInterface l;
    private PlayerSaverInterface s;
    private UserInterface ui;

    public void run(){
        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("New Player");
        menuItems.add("Delete Player");
        menuItems.add("Login");
        menuItems.add("Close Program");
        String menu = CollectionTools.collectionPrinter('S', menuItems);
        runMenu(menu);
    }

    private void runMenu(String menu){
        char choice = ui.getUserAnswer(menu);
        //debug
        System.out.println(choice);
        //\debug
        switch(choice){
            case 'A':
                try {
                    createNewPlayer();
                } catch (IOException e) {
                    System.out.println("Couldn't access Player Files at the moment, please try again later.");
                }
                run();
                break;
            case 'B':
                try {
                    deletePlayer();
                } catch (IOException e) {
                    System.out.println("Couldn't access Player Files at the moment, please try again later.");
                }
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
        String username = ui.readFromUser();
        try {
            if(l.getPlayersArray().contains(username)) {
                PlayerMenu pm = new PlayerMenu(username, ui, l, s);
                pm.run();
            }
        } catch (IOException e) {
            System.out.println("can't access the server at this time. Please try Logging in later.");
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void deletePlayer() throws IOException {
        System.out.println("Please enter the name of the player to delete:");
        String deletablePlayer = ui.readFromUser();
        if(!l.getPlayersArray().contains(deletablePlayer))
            System.out.println("Player already does not exist.");
        else{
            s.removePlayer(deletablePlayer);
            System.out.println(deletablePlayer + " has been removed");
        }
    }

    private void createNewPlayer() throws IOException {
        //TODO
        System.out.println("Please enter the new player name :");
        String newPlayer = ui.readFromUser();
        if(l.getPlayersArray().contains(newPlayer))
            System.out.println("Player already exists.");
        else{
            s.addPlayer(newPlayer);
            System.out.println(newPlayer + " has been added");
        }
    }

}
