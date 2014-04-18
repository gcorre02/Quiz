package persistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import quizData.Player;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Guilherme on 13-04-2014.
 */
public class PlayerLoader implements PlayerLoaderInterface {
    private String source;
    @Getter
    private Loader l;

    public PlayerLoader(String path){
        source = path;
        l = new Loader(path);
        PlayerSaver s = new PlayerSaver(source);
        s = null;
    }

    @Override
    public ArrayList<String> getPlayersArray() throws IOException {
        File f = new File(source + File.separator + "Player" + File.separator + "playersIndex.txt");
        Gson gson = new Gson();
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


        //TODO finish this

        return playernames;
    }
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

        return p;
    }
}
