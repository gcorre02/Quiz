package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	
	
	public boolean saveUserQuizzes(){
		return false;
	}
	public boolean saveQuiz(){
		return false;
	}
}
