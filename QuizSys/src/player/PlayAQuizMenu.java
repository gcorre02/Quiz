package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import userInterface.UserInterface;

/**
 * Created by Admin on 15/04/2014.
 */
@AllArgsConstructor
public class PlayAQuizMenu {
    UserInterface ui;
    PlayerLoader pl;
    PlayerSaver ps;
    String playerName;
    String quizOwner;
    String quiz;

    public void run() {
        //TODO stubbed method
        System.out.println("Stubbed Play a quiz dude!!");
    }
}
