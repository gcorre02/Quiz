package test;

import org.junit.Before;
import org.junit.Test;
import persistence.Loader;
import persistence.Saver;
import quizData.Question;
import quizData.Quiz;
import tools.CollectionTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * generates the file structure to allow for testing.
 *
 * Created by Admin on 19/04/2014.
 */
public class SetupSystemFiles {
    private Saver s;
    private String source;
    private Loader l;
    private ArrayList<String> userNames;
    private String user;
    private String quizName;

    @Before
    public void beforeTests() throws Exception{
        source = "testFiles";
        s = new Saver(source);
        l = new Loader(source);
        addSomeUsers();
        setupSeptimusBondQuiz();
        setupGuyFawlkesBondQuestions();
    }

    private void addSomeUsers() throws IOException {
        String[] names = {"Bartolomeu","Gonzo","Sebastiao","Septimus"};
        userNames = new ArrayList<>();
        Collections.addAll(userNames, names);
        for(String name : userNames){
            s.addUserName(name);
        }
        String[] gonzoQuizzes = {"numbers","people","cars"};
        for(String quiz : gonzoQuizzes){
            s.addQuiz(quiz,"Gonzo",l.getUserQuizzes());
        }
    }

    @Test
    public void run(){

        File f = new File(source);
        assertTrue(f.exists());//TODO add more tests to check whole structure.
    }

    @Test
    public void checkUsers(){
        //debug
        System.out.println(CollectionTools.printMap(l.getUserQuizzes()));
        //Expected
        Set<String> expectedKeys = new HashSet<>();
        expectedKeys.add("Admin");
        expectedKeys.add("Gonzo");
        expectedKeys.add("Septimus");
        //actual
        Set<String> actualKeys = l.getUserQuizzes().keySet();
        //test
        assertTrue(actualKeys.containsAll(expectedKeys));
    }

    @Test
    public void checkSeptimusQuizExists(){
        Quiz bond = l.getQuizObject(user, quizName);
        assertTrue(bond.getOwner().equals(user));
        assertTrue(bond instanceof Quiz);
    }

    private void setupSeptimusBondQuiz(){

        user = "Septimus";
        quizName = "Bond Villains";
        Quiz newQuiz = new Quiz(quizName, user);

        ArrayList<String> quizQuestions = new ArrayList<>();
        quizQuestions.add("Which bond villain has a massive jaw?");
        quizQuestions.add("Which bond villain has a golden penis?");
        quizQuestions.add("Who is the sexiest bond villain?");
        quizQuestions.add("Which bond villain is actually a nice person?");
        newQuiz.setQuizQuestions(quizQuestions);
        s.addQuiz(quizName, user, l.getUserQuizzes());
        s.saveQuiz(newQuiz);
    }
    private void setupGuyFawlkesBondQuestions(){
        String newUser = "Guy Fawlkes";
        String newquizName = "Another Bond";
        try {
            s.addUserName(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Quiz newQuiz = new Quiz(newquizName, newUser);
        ArrayList<String> quizQuestions = new ArrayList<>();
        String questionString = "Who is the sexiest bond villain?";
        quizQuestions.add("Which bond villain has a massive jaw?");
        quizQuestions.add("Which bond villain has a golden penis?");
        quizQuestions.add(questionString);
        quizQuestions.add("Which bond villain is actually a nice person?");
        newQuiz.setQuizQuestions(quizQuestions);
        s.addQuiz(newquizName, newUser, l.getUserQuizzes());
        s.saveQuiz(newQuiz);
        ArrayList<String> answers = new ArrayList<String>();
        answers.add("Odd Job");
        answers.add("Octopussy");
        answers.add("Bane");
        answers.add("MoneyPenny");
        Question updatableQuestion = new Question(questionString, answers, 1, newUser, newquizName);
        s.saveAQuestionObject(updatableQuestion);
    }
}
