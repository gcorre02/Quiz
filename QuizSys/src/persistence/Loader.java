package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import quizData.Question;
import quizData.Quiz;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Local Loader class that interacts directly with  the data and file structure.
 */
public class Loader implements LoaderInterface {
	String source;
	File file;
	Saver s;

    /**
     * Instantiates Saver to make sure that the file structure exists.
     * @param path to the data structure in the file system.
     */
	public Loader(String path){
		source = path;
		//setup basic file system;
		s = new Saver(path);
		s = null;
	}

    /**
     * Method to return the saved structure of existing users.
     * @return all the usernames as an arraylist
     * @throws IOException if it has a problem accessing the files.
     */
	@Override
    public ArrayList<String> getUsernames() throws IOException{
		ArrayList<String> usernames = new ArrayList<>();
		file = new File(source+File.separator+"UserNames.txt");
		BufferedReader reader;
		JsonReader jReader;
	
		try {
			reader =new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.print("File cannot be accessed");
			return null;
		}
		
		jReader = new JsonReader(reader);
		jReader.beginArray();

		while(jReader.hasNext()){
			usernames.add(jReader.nextString());
		}
		
		jReader.close();
		reader.close();
		
		return usernames;
	}
	/**
	 *
	 * 
	 * @return Map all quizzes mapped to their corresponding users.
	 */
	@Override
    public Map<String, String[]> getUserQuizzes(){
		Map<String, String[]> userQuizzes;
		Gson gson = new Gson();
		file = new File(source + File.separator+ "UserQuizzes.txt");
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.print("UserQuizzes File cannot be accessed");
			return null;
		}
		
		userQuizzes = gson.fromJson(reader, new TypeToken<Map<String,String[]>>(){}.getType());
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Couldn't close reader in loader.getUserQuizzes()");
        }
        return userQuizzes;
	}

    /**
     * Grabs the referenced quiz object from the data structure
     * @param user The user name that owns the quiz.
     * @param quizName the name of the requested quiz.
     * @return the quiz object.
     */
    @Override
    public Quiz getQuizObject(String user, String quizName){
		Quiz returnQuiz;
		Gson gson = new Gson();
		
		file = new File(source + File.separator+ user +File.separator+ quizName+File.separator+quizName + "Object.txt");
		BufferedReader reader;
		//debug
        //System.out.println(file.getAbsolutePath());
        //debug
        try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.print("Object File cannot be accessed");
			return null;
		}
        returnQuiz = gson.fromJson(reader, Quiz.class);
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnQuiz;
	}

    /**
     * Returns an array with the ordered questions belonging to the targetted quiz.
     * This is a separate simpler file located in the file system next to the quiz object file.
     *
     * @param owner the name of the owner of the quiz.
     * @param quizName the name of the quiz.
     * @return returns an array with the ordered questions.
     * @throws IOException if file is not properly accessible.
     */
	@Override
    public ArrayList<String> getQuizQuestionsConfig(String owner, String quizName) throws IOException {
		ArrayList<String> returnArray = new ArrayList<>();
		
		file = new File(source+File.separator+owner+File.separator+quizName+File.separator+quizName+".txt");
		BufferedReader reader;
		JsonReader jReader;
	
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.print("File cannot be accessed");
			return null;
		}
		
		jReader = new JsonReader(reader);
		jReader.beginArray();

		while(jReader.hasNext()){
			returnArray.add(jReader.nextString());
		}
		
		jReader.close();
		reader.close();
		
		return returnArray;
	}

    /**
     * returns an integer corresponding to the requested question
     * @param questionString the String of the question itself.
     * @param owner the name of the owner of the quiz.
     * @param quiz the name of the quiz.
     * @return the integer identifying the question in the file system.
     * @throws IOException if the quiz config file is not accessible.
     */
	@Override
    public int getQuestionNumber(String questionString, String owner,
                                 String quiz) throws IOException {
		ArrayList<String> questions = getQuizQuestionsConfig(owner, quiz);
		return questions.indexOf(questionString);
	}

    /**
     * returns the referenced question object
     * @param owner the name of the owner of the quiz.
     * @param quiz the name of the quiz.
     * @param questionString the String of the question itself.
     * @return the question object represented by the request
     * @throws IOException if the question object file is not accessible.
     */
	@Override
    public Question getQuestionObject(String owner, String quiz,
                                      String questionString) throws IOException {
		Question returnQuestion;
		Gson gson = new Gson();
		
		file = new File(source + File.separator+ owner +File.separator+ quiz+File.separator+ getQuestionNumber(questionString, owner, quiz) + ".txt");
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.print("Question File cannot be accessed");
			return null;
		}
		returnQuestion = gson.fromJson(reader, Question.class);
		reader.close();

		return returnQuestion;
	}
}
