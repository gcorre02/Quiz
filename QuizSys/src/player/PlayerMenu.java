package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import userInterface.UserInterface;

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

    }
}
