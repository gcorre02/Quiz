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
    private Map<String, Double> quizScores;

    public Player(String name){
        this.name = name;
        playedQuizzes = new HashMap<>();
        playedQuizzes.put("No Quizzes Played Yet",new String[0]);
        quizScores = new HashMap<>();
        quizScores.put("No Quizzes Played Yet", 0.0);
    }
    public boolean firstPlay(){
        if(playedQuizzes.containsKey("No Quizzes Played Yet"))
            return true;
        else
            return false;
    }
}
