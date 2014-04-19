package main.launchers;

import rmi.LoaderServerLauncher;

/**
 * Launches the server through a main, 1st argument is the source folder for the file system.
 *
 * Created by Guilherme on 18-04-2014.
 */
public class LaunchServerMain {
    /**
     * Launches server.
     * @param args the file system folder, at position 0.
     */
    public static void main(String[] args) {
        LoaderServerLauncher.main(args);
    }
}
