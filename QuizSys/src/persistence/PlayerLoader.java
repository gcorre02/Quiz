package persistence;

import lombok.AllArgsConstructor;

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

}
