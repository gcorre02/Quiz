package main;

import persistence.PlayerLoader;
import persistence.PlayerSaver;
import player.PlayerLoginMenu;
import userInterface.UserInterface;

/**
 * Created by Admin on 15/04/2014.
 */
public class LaunchPlayerLocalVersion {
    public static void main(String[] args) {
        PlayerSaver ps = new PlayerSaver(args[0]);
        PlayerLoader pl = new PlayerLoader(args[0]);
        UserInterface ui = new UserInterface();
        PlayerLoginMenu plm = new PlayerLoginMenu(pl,ps,ui);
        plm.run();
    }
}
