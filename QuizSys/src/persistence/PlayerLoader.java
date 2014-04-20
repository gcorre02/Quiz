package persistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import quizData.Player;

import java.io.*;
import java.util.ArrayList;

/**
 * The real methods for all persistence loading data related to player files.
 *
 * Created by Guilherme on 13-04-2014.
 */
public class PlayerLoader implements PlayerLoaderInterface {
    private String source;
    @Getter
    private Loader l;

    /**
     * Instantiates PlayerSaver to ensure the file system exists.
     * Instantiates Loader for any possible needs of data communication with user data.
     * @param path to the file structure in the system.
     */
    public PlayerLoader(String path){
        source = path;
        l = new Loader(path);
        PlayerSaver s = new PlayerSaver(source);
        s = null;
    }

    /**
     * Returns an array with all registered player names
     *
     * @return array with all registered player names.
     * @throws IOException if the file with player names is not propperly accessible.
     */
    @Override
    public ArrayList<String> getPlayersArray() throws IOException {
        File f = new File(source + File.separator + "Player" + File.separator + "playersIndex.txt");
        ArrayList<String> playernames = new ArrayList<>();
        BufferedReader reader;
        JsonReader jReader;

        try {
            reader =new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            System.out.print("File cannot be accessed");
            return null;
        }

        jReader = new JsonReader(reader);
        jReader.beginArray();

        while(jReader.hasNext()){
            playernames.add(jReader.nextString());
        }

        jReader.close();
        reader.close();

        return playernames;
    }

    /**
     * returns the player object.
     * @param name of the player.
     * @return requested player object.
     */
    @Override
    public Player getPlayer(String name){
        Player p;

        Gson gson = new Gson();

        File file;
        file = new File(source + File.separator+ "Player" +File.separator+ name+ ".txt");
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.print("Player File cannot be accessed");
            return null;
        }
        p = gson.fromJson(reader, Player.class);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;
    }
}
