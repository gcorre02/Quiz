package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoaderInterface;
import persistence.PlayerSaverInterface;
import quizData.Player;
import quizData.Question;
import tools.CollectionTools;
import tools.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 15/04/2014.
 */
@AllArgsConstructor
public class PlayAQuizMenu {
    private UserInterface ui;
    private PlayerLoaderInterface pl;
    private PlayerSaverInterface ps;
    private String playerName;
    private String quizOwner;
    private String quiz;

    public void run() {

        //TODO played counter needs to be implemented into the Player Object

        System.out.println("Welcome to " + quiz);
        double score = 0;
        try {
            score = playQuiz();
        } catch (IOException e) {
            System.out.println("Couldn't complete the quiz, please play again.");
        }

        System.out.println("Your score is " + score + "%");

        close(score);
    }

    private double playQuiz() throws IOException {
        double score = 0;

        ArrayList<String> questions = pl.getL().getQuizQuestionsConfig(quizOwner,quiz);
        for(String question : questions){
            System.out.println("\n"+question);
            Question q = pl.getL().getQuestionObject(quizOwner,quiz,question);
            //debug
            System.out.println("Loaded "+ q + ": ");
            System.out.println("Owner : " + q.getOwner());
            System.out.println("Answers : " + CollectionTools.collectionPrinter('S', q.getAnswers()));
            //\debug
            int qScore = getQuestionScore(q);
            score += qScore;
        }

        int numberOfQuestions = questions.size();

        score = score / numberOfQuestions * 100;

        return score;
    }

    private int getQuestionScore(Question q) {
        ArrayList<String> answers = q.getAnswers();
        //TODO use keys instead of ints
        System.out.println(CollectionTools.collectionPrinter('0', answers));
        int answer = Integer.parseInt(ui.readFromUser());
        int rightAnswer = q.getRightAnswer();
        if(answer == rightAnswer)
            return 1;
        else
            return 0;
    }

    /**
     * method used to persist data: score, increase played counter ...
     */
    private void close(Double score) {
        Player p = pl.getPlayer(playerName);
        Map<String, String[]> playedQuizzes;
        Map<String, Double> quizScores;

        if(p.firstPlay()){
            playedQuizzes = new HashMap<>();
            String[] currentQuiz = {quiz};
            playedQuizzes.put(quizOwner, currentQuiz);
            quizScores = new HashMap<>();
            quizScores.put((quizOwner+"."+quiz),score);

        }else{
            playedQuizzes = p.getPlayedQuizzes();
            quizScores = p.getQuizScores();
            if(!playedQuizzes.containsKey(quizOwner)
                    ||!CollectionTools.arrayContains(quiz, playedQuizzes.get(quizOwner))) {
                String[] newQuizzes = {quiz};
                playedQuizzes.put(quizOwner, newQuizzes);
            }
            String quizScoreKey = quizOwner+"."+quiz;
            Double highScore = quizScores.get(quizScoreKey);
            if(score > highScore){
                quizScores.replace(quizScoreKey,score);
                System.out.println("New High Score for you!");
            }

        }
        p.setPlayedQuizzes(playedQuizzes);
        p.setQuizScores(quizScores);

        ps.savePlayer(p);
    }
}
