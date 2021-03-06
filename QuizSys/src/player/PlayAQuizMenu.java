package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoaderInterface;
import persistence.PlayerSaverInterface;
import quizData.Player;
import quizData.Question;
import quizData.Quiz;
import tools.CollectionTools;
import tools.UserInterface;

import java.util.*;

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

    public void run(){

        //TODO played counter needs to be implemented into the Player Object

        System.out.println("Welcome to " + quiz);
        double score = 0;

        score = playQuiz();
        boolean highestScore = checkHighScore(score);
        if(highestScore){
            System.out.println("New global high score: " + score + "%");
        }
        System.out.println("Your score is " + score + "%");

        close(score);
    }

    private boolean checkHighScore(double score) {
        Quiz q = pl.getL().getQuizObject(quizOwner,quiz);
        LinkedHashMap<Double,String> highestScore = q.getHighestScore();
        if(highestScore.keySet().toArray(new Double[0])[0]<score){
            highestScore = new LinkedHashMap<>();
            highestScore.put(score,playerName);
            q.setHighestScore(highestScore);
            return true;
        }
        return false;
    }

    private double playQuiz() {
        double score = 0;
        try {
            ArrayList<String> questions = pl.getL().getQuizQuestionsConfig(quizOwner, quiz);
            for (String question : questions) {
                System.out.println("\n" + question);
                Question q = pl.getL().getQuestionObject(quizOwner, quiz, question);
                int qScore = getQuestionScore(q);
                score += qScore;
            }

            int numberOfQuestions = questions.size();

            score = score / numberOfQuestions * 100;
        }catch(Exception e){
            score = 0;
            System.out.println("Couldn't finish the quiz");
        }
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
        if(!p.getPlayedQuizzes().containsKey(quizOwner+"."+quiz)){
            p.getQuizScores().put((quizOwner+"."+quiz),0.0);
        }
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
