package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import tools.CollectionPrinter;
import lombok.Data;

import com.google.gson.*;
/**
 * source defines the folder where all the quiz system files are.
 * TODO<concurrency> some conflict checking must go in here.
 * @author Guilherme
 *
 */
@Data
public class Saver {
	String source;
	File file;
	public Saver(String path){
		source = path;
		createFolder(path);
	}

	private void createFolder(String path) {
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdir();
		}
	}
	/**
	 * add User names should be called usually, saveUserNames has the danger of overwriting existing users
	 * TODO <overwriting> make it private ???
	 * @param userNames
	 * @return
	 */
	public boolean saveUserNames(ArrayList<String> userNames){
		Gson gson = new Gson();
		file = new File(source+File.separator+"UserNames.txt");
		PrintWriter w;
		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		w.println(gson.toJson(userNames.toArray()));
		w.close();
		createUserFolders(userNames);
		return true;
	}

	public boolean addUserName(String user) throws IOException{
		Loader l = new Loader(source);
		ArrayList<String> existingUserNameStrings = l.getUsernames();
		if(existingUserNameStrings.contains(user)){
			return false;
		} else {
			existingUserNameStrings.add(user);
			saveUserNames(existingUserNameStrings);
			return true;
		}
	}

	/*
	 * creates folders for each user, if these do not exist already.
	 *
	 */
	private void createUserFolders(ArrayList<String> users){
		for(String user : users){
			createFolder(source+File.separator+user);
		}
	}
	public boolean deleteUser(String user) throws IOException{
		Loader l = new Loader(source);
		ArrayList<String> users = l.getUsernames();
		if(!user.contains(user)){
			return false;
		} else {
			users.remove(user);
			saveUserNames(users);
			String path =source + File.separator + user; 
			deleteFolder(path);
			File f = new File(path);
			if(f.exists()){
				return false;
			}
			return true;
		}
	}

	public void deleteFolder(String path) {
		File file = new File(path);
		if(file.list()!=null){
			int i = file.list().length;
			for(int t = 0; t<i; t++){
				String s = file.listFiles()[0].toPath().toString();
				deleteFolder(s);
			}
		}
		file.delete();
	}
	/*
	 * need to improve the json to make it understand the map propperly
	 */
	public boolean saveUserQuizzes(Map<String, String[]> userQuizzes){
		if(!writeUserQuizzesJson(userQuizzes)){
			return false;
		}
		try {
			createQuizFiles(userQuizzes);
		} catch (IOException e) {
			System.out.println("couldn't set up all the quizz files");
			return false;
		}
		return true;
	}

	private boolean writeUserQuizzesJson(Map<String, String[]> userQuizzes) {
		Gson gson = new Gson();
		file = new File(source+File.separator+"UserQuizzes.txt");
		PrintWriter w;
		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		w.println(gson.toJson(userQuizzes));
		w.close();
		return true;
	}
	private void createQuizFiles(Map<String, String[]> userQuizzes) throws IOException {
		Loader l = new Loader(source);
		for(String user : userQuizzes.keySet()){
			if(!l.getUsernames().contains(user)){
				addUserName(user);
			}
			generateQuizFiles(user, userQuizzes.get(user));
		}
	}
	/*
	 * needs to check if the files already exist.
	 */
	private void generateQuizFiles(String user, String[] strings) throws IOException {
		for(String quizname: strings){
			File quizFile = new File(source+File.separator+user+File.separator+quizname+".txt");
			if(!quizFile.exists()){
				quizFile.createNewFile();
			}
		}
	}

	public boolean addQuiz(String quizName, String userName, Map<String, String[]> userQuizzes){
		//error checking
		if(!userQuizzes.keySet().contains(userName)){
			System.out.println("User " + userName + " does not exist.");
			return false;
		}

		if(CollectionPrinter.arrayContains(quizName, userQuizzes.get(userName))){
			System.out.println("A quiz with the name " + quizName + " already exists.");
			return true;
		}
		//exec
		
		userQuizzes.put(userName, CollectionPrinter.addElementToArray(quizName, userQuizzes.get(userName)));
		//debug
		//System.out.println(CollectionPrinter.printMap(userQuizzes));
		//end debug
		if(saveUserQuizzes(userQuizzes))
			return true;
		else
			return false;
	}

	public boolean removeQuiz(String quizName, String userName, Map<String, String[]> userQuizzes){
		//error checking
		if(!userQuizzes.keySet().contains(userName)){
			System.out.println("User " + userName + " does not exist.");
			return false;
		}

		if(!CollectionPrinter.arrayContains(quizName, userQuizzes.get(userName))){
			System.out.println("A quiz with the name " + quizName + " doesnt exist");
			return true;
		}
		
		//exec
		userQuizzes.put(userName, CollectionPrinter.removeElementFromArray(quizName, userQuizzes.get(userName)));
		
		//persist
		if(saveUserQuizzes(userQuizzes))
			return true;
		else
			return false;
	}

	public boolean saveQuiz(){
		return false;
	}
}
