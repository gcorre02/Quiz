package quizData;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guilherme on 13-04-2014.
 */
@Data
public class Player {
    private String name;
    private Map<String, String[]> playedQuizzes;

    public Player(String name){
        this.name = name;
        playedQuizzes = new HashMap<>();
        playedQuizzes.put("No Quizzes Played Yet",new String[0]);
    }
}
