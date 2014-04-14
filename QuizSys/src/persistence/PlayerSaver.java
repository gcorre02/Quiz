package persistence;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;
import quizData.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * handles persistence for the player file system.
 *
 * Created by Guilherme on 13-04-2014.
 */
public class PlayerSaver {
    String source;
    Saver s;
    public PlayerSaver(String path){
        this.source = path;
        //makes all the checks for the admin file structure
        Saver s = new Saver(path);
        s = null;
        checkPlayerFolderExists();
        checkPlayersIndexFileExists();
    }

    private void checkPlayersIndexFileExists() {
        String path = source + File.separator + "Player" + File.separator + "playersIndex.txt";
        File f = new File(path);
        if(!f.exists()){
            ArrayList<String> stubUsers = new ArrayList<>();
            stubUsers.add("AdminPlayer");
            createPlayersIndexJson(stubUsers);
        }
    }

    private void createPlayersIndexJson(ArrayList<String> inputArray) {
        String path = source + File.separator + "Player" + File.separator + "playersIndex.txt";
        Gson gson = new Gson();
        File file = new File(path);
        PrintWriter w;
        try {
            w = new PrintWriter(file);
            w.println(gson.toJson(inputArray));
            w.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't create the player's file system.");
        }

        createPlayersFiles(inputArray);
    }

    private void createPlayersFiles(ArrayList<String> playerNames) {
        for(String player : playerNames){
            Player p = new Player(player);
            savePlayer(p);
        }
    }

    private void savePlayer(Player p) {
        String path = source + File.separator + "Player" + File.separator + p.getName()+".txt";
        Gson gson = new Gson();
        File file = new File(path);
        PrintWriter w;
        try {
            w = new PrintWriter(file);
            w.println(gson.toJson(p));
            w.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't persist the player's object.");
        }

    }

    private void checkPlayerFolderExists() {
        File f = new File(source + File.separator + "Player");
        if(!f.exists()){
            f.mkdir();
        }
    }
    public void addPlayer(String name) throws IOException {
        PlayerLoader pl = new PlayerLoader(source);
        ArrayList<String> existingPlayer;
        existingPlayer = pl.getPlayersArray();
        if(existingPlayer.contains(name)){
            System.out.println(name + " already exists.");
        }else{
            existingPlayer.add(name);
            createPlayersIndexJson(existingPlayer);
        }
    }

}
