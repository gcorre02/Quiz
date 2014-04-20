package persistence;

import quizData.Player;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * The interface to handle connection between local and server side PlayerLoader classes.
 * Created by Guilherme Ribeiro on 18-04-2014.
 */
public interface PlayerLoaderInterface {
    ArrayList<String> getPlayersArray() throws IOException;

    Player getPlayer(String name);

    LoaderInterface getL();
}
