package persistence;

import quizData.Player;
import rmi.LoaderClient;

import java.io.IOException;
import java.util.ArrayList;

//TODO need to write tests for this.

/**
 * Created by user on 18-04-2014.
 */
public class PlayerLoaderRmiCaller implements PlayerLoaderInterface {
    private String source;
    private LoaderClient lc;
    private LoaderRmiCaller lrmi;

    public PlayerLoaderRmiCaller(String source){
        this.source = source;
        lrmi = new LoaderRmiCaller(source);//TODO source needs handling. probably when the the system loads, it asks the server what the source is.
    }

    @Override
    public ArrayList<String> getPlayersArray() throws IOException {
        lc = new LoaderClient();
        String callClass = "persistence.PlayerLoader";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod);
    }

    @Override
    public Player getPlayer(String name) {
        lc = new LoaderClient();
        String callClass = "persistence.PlayerLoader";
        //get the name of the current method
        String callMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        //call the corresponding method in the server
        return lc.run(callClass,callMethod,name);
    }

    @Override
    public LoaderInterface getL() {
        return lrmi;
    }
}
