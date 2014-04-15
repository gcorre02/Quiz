package player;

import lombok.AllArgsConstructor;
import persistence.Loader;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import quizData.Player;
import quizData.Quiz;
import tools.CollectionPrinter;
import userInterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

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
        String[] menuArray = {"Browse All Quizzes", "Play a Quiz", "Played Quizzes", "Logout"};
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
                showAllQuizzes();
                run();
                break;
            case 'B':
                try {
                    //TODO need to check if the quiz is playable, if not, cant play it. setup by user and saved inside the quiz object
                    playAQuizz();
                } catch (IOException e) {
                    System.out.println("Couldn't access user quizzes file.");
                }
                run();
                break;
            case 'C':
                printPlayerPlayedQuizzes();
                run();
                break;
            case 'D':
                System.out.println("Thank you for playing. Goodbye");
                break;
            default:
                System.out.println("Couldn't understand the input, please choose again.");
                run();
                break;
        }

    }

    private void printPlayerPlayedQuizzes() {
        //SETUP
        Player p = pl.getPlayer(playerName);
        Map<String, String[]> playedQuizzes = p.getPlayedQuizzes();
        //RUN
        System.out.println("These are the quizzes you have played:");
        String played = CollectionPrinter.printMap(playedQuizzes);
        System.out.println(played);
    }

    private void playAQuizz() throws IOException {
        //SETUP
        Loader l = pl.getL();
        showAllQuizzes();
        //USER
        System.out.println("Which user would you like to access ? ");
        ArrayList<String> userNames = l.getUsernames();
        String users = CollectionPrinter.collectionPrinter('0', userNames);
        System.out.println(users);
        String quizOwner = userNames.get(Integer.parseInt(ui.readFromUser()));
        //QUIZ
        System.out.println("Please choose a quiz to play: ");
        String[] quizNames = l.getUserQuizzes().get(quizOwner);
        ArrayList<String> quizNamesArray = new ArrayList<>();
        Collections.addAll(quizNamesArray, quizNames);
        String quizzes = CollectionPrinter.collectionPrinter('0',quizNamesArray);
        System.out.println(quizzes);
        String quiz = quizNamesArray.get(Integer.parseInt(ui.readFromUser()));
        //debug
        System.out.println("You picked quiz " + quiz + " from user " + quizOwner);
        //\debug
        //RUN QUIZ
        PlayAQuizMenu paqm = new PlayAQuizMenu(ui,pl,ps, playerName, quizOwner, quiz);
        paqm.run();
    }

    private void showAllQuizzes() {
        System.out.println("These are all the available quizzes: ");
        Loader l = pl.getL();
        String print = CollectionPrinter.printMap(l.getUserQuizzes());
        System.out.println(print);
    }
}
