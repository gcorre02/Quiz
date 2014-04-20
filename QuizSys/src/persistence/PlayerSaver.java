package persistence;

import com.google.gson.Gson;
import quizData.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * handles persistence on the server side for the player file system.
 *
 * Created by Guilherme on 13-04-2014.
 */
public class PlayerSaver implements PlayerSaverInterface {
    String source;
    Saver s;

    /**
     * Makes sure the file system structure exists and instantiates it if not.
     *
     *
     * @param path to the file structure in the system.
     */
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
            savePlayersIndexJson(stubUsers);
        }
    }

    private void savePlayersIndexJson(ArrayList<String> inputArray) {
        String path = source + File.separator + "Player" + File.separator + "playersIndex.txt";
        Gson gson = new Gson();
        File file = new File(path);
        PrintWriter w = null;
        try {
            w = new PrintWriter(file);
            w.println(gson.toJson(inputArray));
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't create the player's file system.");
        }
        w.close();


        createPlayersFiles(inputArray);
    }

    private void createPlayersFiles(ArrayList<String> playerNames) {
        for(String player : playerNames){
            Player p = new Player(player);
            savePlayer(p);
        }
    }

    /**
     * used to save or update a player object.
     * @param p the player object to create or update.
     */
    @Override
    public void savePlayer(Player p) {
        String path = source + File.separator + "Player" + File.separator + p.getName()+".txt";
        Gson gson = new Gson();
        File file = new File(path);
        PrintWriter w = null;
        try {
            w = new PrintWriter(file);
            w.println(gson.toJson(p));
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't persist the player's object.");
        }
        w.close();

    }

    private void checkPlayerFolderExists() {
        File f = new File(source + File.separator + "Player");
        if(!f.exists()){
            f.mkdir();
        }
    }

    /**
     * adds a reference to player to the file structure
     *
     * @param name the new name to input
     * @return true if the operation works, false if a player already exists.
     * @throws IOException if the file system cannot be accessed propperly
     */
    @Override
    public boolean addPlayer(String name) throws IOException {
        PlayerLoader pl = new PlayerLoader(source);
        ArrayList<String> existingPlayer;
        existingPlayer = pl.getPlayersArray();
        if(existingPlayer.contains(name)){
            System.out.println(name + " already exists.");
            return false;

        }else{
            existingPlayer.add(name);
            savePlayersIndexJson(existingPlayer);
            System.out.println("Player " + name + " has been added.");
            return true;
        }
    }

    /**
     * removes a player.
     *
     * @param name of the player to remove
     * @throws IOException if the file structure cannot be accessed.
     */
    @Override
    public boolean removePlayer(String name) throws IOException {
        PlayerLoader pl = new PlayerLoader(source);
        ArrayList<String> existingPlayers;
        existingPlayers = pl.getPlayersArray();
        if(!existingPlayers.contains(name)){
            System.out.println(name + " has already been removed.");
            return false;
        }else{
            existingPlayers.remove(name);
            deletePlayerFile(name);
            savePlayersIndexJson(existingPlayers);
            return true;
        }
    }

    private void deletePlayerFile(String name) {
        String path = source + File.separator + "Player" + File.separator + name+".txt";
        deleteFile(path);
    }

    private void deleteFile(String path) {
        File f = new File(path);
        if(f.exists())
            f.delete();
        else
            System.out.println("File already does not exist");
    }

}
