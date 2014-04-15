package player;

import lombok.AllArgsConstructor;
import persistence.PlayerLoader;
import persistence.PlayerSaver;
import quizData.Question;
import tools.CollectionPrinter;
import userInterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 15/04/2014.
 */
@AllArgsConstructor
public class PlayAQuizMenu {
    private UserInterface ui;
    private PlayerLoader pl;
    private PlayerSaver ps;
    private String playerName;
    private String quizOwner;
    private String quiz;

    public void run() {
        //TODO stubbed method

        //TODO played counter needs to be implemented into the Player Object

        System.out.println("Welcome to " + quiz);
        double score = 0;
        try {
            score = playQuiz();
        } catch (IOException e) {
            System.out.println("Couldn't complete the quiz, please play again.");
        }

        System.out.println("Your score is " + score + "%");

        close();
    }

    private double playQuiz() throws IOException {
        double score = 0;

        ArrayList<String> questions = pl.getL().getQuizQuestionsConfig(quizOwner,quiz);
        for(String question : questions){
            Question q = pl.getL().getQuestionObject(quizOwner,quiz,question);
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
        System.out.println(CollectionPrinter.collectionPrinter('0',answers));
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
    private void close() {
    }
}
