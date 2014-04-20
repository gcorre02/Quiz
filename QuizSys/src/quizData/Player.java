package quizData;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guilherme on 13-04-2014.
 */
@NoArgsConstructor //For rmi deserialization
@Data
public class Player implements Serializable{
    private String name;
    private Map<String, String[]> playedQuizzes;
    private Map<String, Double> quizScores;

    public Player(String name){
        this.name = name;
        playedQuizzes = new HashMap<>();
        playedQuizzes.put("No Quizzes Played Yet",new String[]{"none"});
        quizScores = new HashMap<>();
        quizScores.put("No Quizzes Played Yet", 1.0);
    }
    public boolean firstPlay(){
        if(playedQuizzes.containsKey("No Quizzes Played Yet"))
            return true;
        else
            return false;
    }
}
