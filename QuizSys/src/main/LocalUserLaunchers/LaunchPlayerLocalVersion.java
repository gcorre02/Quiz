package main.LocalUserLaunchers;

import persistence.PlayerLoader;
import persistence.PlayerSaver;
import player.PlayerLoginMenu;
import tools.UserInterface;

/**
 * Class used to launch a local version of the player quiz system, with the file name passed by args[0]
 *
 * Created by Guilherme on 15/04/2014.
 *
 */
public class LaunchPlayerLocalVersion {


    /**
     * Launch the local player quiz system
     *
     * @param args pass in the source folder path
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        launch(args[0],ui);
    }

    /**
     * alternative launching method for passing in a UserInterface.
     * @param source the path of where the quiz system is located.
     * @param ui param to pass in an instance of the UserInterface, opens the possibility for mocking.
     */
    public static void launch(String source, UserInterface ui) {
        PlayerSaver ps = new PlayerSaver(source);
        PlayerLoader pl = new PlayerLoader(source);
        PlayerLoginMenu plm = new PlayerLoginMenu(pl,ps,ui);
        plm.run();
    }
}
