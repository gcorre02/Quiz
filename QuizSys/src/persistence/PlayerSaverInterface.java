package persistence;

import quizData.Player;

import java.io.IOException;

/**
 * Created by user on 18-04-2014.
 */
public interface PlayerSaverInterface {
    void savePlayer(Player p);

    void addPlayer(String name) throws IOException;

    void removePlayer(String name) throws IOException;
}
