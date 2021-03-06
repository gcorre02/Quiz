package player;

import lombok.AllArgsConstructor;
import persistence.LoaderInterface;
import persistence.PlayerLoaderInterface;
import persistence.PlayerSaverInterface;
import quizData.Player;
import tools.CollectionTools;
import tools.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Admin on 15/04/2014.
 */
@AllArgsConstructor
public class PlayerMenu {
    String playerName;
    UserInterface ui;
    PlayerLoaderInterface pl;
    PlayerSaverInterface ps;

    public void run() {
        String[] menuArray = {"Browse All Quizzes", "Play a Quiz", "Played Quizzes", "Logout"};
        ArrayList<String> menuItems = new ArrayList<>();
        Collections.addAll(menuItems, menuArray);
        String menu = CollectionTools.collectionPrinter('S', menuItems);
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
        String played = CollectionTools.printMap(playedQuizzes);
        System.out.println(played);
    }

    private void playAQuizz() throws IOException {
        //SETUP
        LoaderInterface l = pl.getL();
        showAllQuizzes();
        //USER
        System.out.println("Which user would you like to access ? ");
        ArrayList<String> userNames = l.getUsernames();
        String users = CollectionTools.collectionPrinter('0', userNames);
        System.out.println(users);
        String quizOwner = userNames.get(Integer.parseInt(ui.readFromUser()));
        //QUIZ
        System.out.println("Please choose a quiz to play: ");
        String[] quizNames = l.getUserQuizzes().get(quizOwner);
        ArrayList<String> quizNamesArray = new ArrayList<>();
        Collections.addAll(quizNamesArray, quizNames);
        String quizzes = CollectionTools.collectionPrinter('0', quizNamesArray);
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
        LoaderInterface l = pl.getL();
        String print = CollectionTools.printMap(l.getUserQuizzes());
        System.out.println(print);
    }
}
