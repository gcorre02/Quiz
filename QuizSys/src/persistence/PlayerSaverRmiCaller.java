package persistence;

import quizData.Player;
import rmi.LoaderClient;

import java.io.IOException;

/**
 * Created by user on 18-04-2014.
 */
public class PlayerSaverRmiCaller implements PlayerSaverInterface {
    private String source;
    private LoaderClient lc;

    public PlayerSaverRmiCaller(String source){
        this.source = source;
    }
    @Override
    public void savePlayer(Player p) {
        lc = new LoaderClient();
        String callClass = "persistence.PlayerSaver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        lc.run(callClass,callMethod, p);
    }

    @Override
    public boolean addPlayer(String name) throws IOException {
        lc = new LoaderClient();
        String callClass = "persistence.PlayerSaver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, name);
    }

    @Override
    public boolean removePlayer(String name) throws IOException {
        lc = new LoaderClient();
        String callClass = "persistence.PlayerSaver";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod, name);
    }
}
