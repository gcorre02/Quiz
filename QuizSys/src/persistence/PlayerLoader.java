package persistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.AllArgsConstructor;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Guilherme on 13-04-2014.
 */
public class PlayerLoader {
    private String source;
    private Loader l;

    public PlayerLoader(String path){
        source = path;
        l = new Loader(path);
    }

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
}
