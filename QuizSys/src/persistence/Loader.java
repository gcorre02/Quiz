package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import quizData.Quiz;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.reflect.TypeToken;

public class Loader {
	String source;
	File file;

	public Loader(String path){
		source = path;
		File folder = new File(path);
		if(!folder.exists()){
			System.out.println("folder is empty");
			folder.mkdir();
		}
	}

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
		
		return userQuizzes;
	}
	public Quiz getQuizObject(String user, String quizName){
		Quiz returnQuiz;
		Gson gson = new Gson();
		
		file = new File(source + File.separator+ user +File.separator+ quizName+File.separator+quizName + "Object.txt");
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.print("UserQuizzes File cannot be accessed");
			return null;
		}
		returnQuiz = gson.fromJson(reader, Quiz.class);
		
		return returnQuiz;
	}

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

	public int getQuestionNumber(String questionString, String owner,
			String quiz) throws IOException {
		ArrayList<String> questions = getQuizQuestionsConfig(owner, quiz);
		return questions.indexOf(questionString);
	}
}
