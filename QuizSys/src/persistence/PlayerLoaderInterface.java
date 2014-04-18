package persistence;

import quizData.Player;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 18-04-2014.
 */
public interface PlayerLoaderInterface {
    ArrayList<String> getPlayersArray() throws IOException;

    Player getPlayer(String name);

    Loader getL();
}
