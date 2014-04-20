package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import quizData.Question;
import quizData.Quiz;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Loader implements LoaderInterface {
	String source;
	File file;
	Saver s;
	public Loader(String path){
		source = path;
		//setup basic file system;
		s = new Saver(path);
		s = null;
	}

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
	 * TODO should verify that all userquizzes have associated folders and files.
	 * 
	 * @return
	 */
	@Override
    public Map<String, String[]> getUserQuizzes(){
		Map<String, String[]> userQuizzes = new HashMap<>();
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

	@Override
    public int getQuestionNumber(String questionString, String owner,
                                 String quiz) throws IOException {
		ArrayList<String> questions = getQuizQuestionsConfig(owner, quiz);
		return questions.indexOf(questionString);
	}

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
