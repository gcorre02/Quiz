package main.LocalUserLaunchers;

import persistence.*;
import player.PlayerLoginMenu;
import tools.UserInterface;

/**
 * Launches the server version of the Player quiz system.
 *
 * Created by Guilherme on 15/04/2014.
 */
public class LaunchPlayerServerVersion {

    /**
     * main version of the Player server system launcher
     * @param args [0] position is the path to the file structure.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        launch(args[0], ui);
    }

    public static void launch(String source, UserInterface ui) {
        PlayerSaverInterface ps = new PlayerSaverRmiCaller(source);
        PlayerLoaderInterface pl = new PlayerLoaderRmiCaller(source);
        PlayerLoginMenu plm = new PlayerLoginMenu(pl, ps,ui);
        plm.run();
    }
}
