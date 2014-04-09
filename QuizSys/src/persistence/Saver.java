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
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdir();
		}
	}
	
	public boolean saveUserNames(ArrayList<String> userNames){
		Gson gson = new Gson();
		System.out.println();
		file = new File(source+File.separator+"UserNames.txt");
		PrintWriter w;
		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		w.println(gson.toJson(userNames.toArray()));
		w.close();
		
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
	
	public boolean saveUserQuizzes(){
		return false;
	}
	public boolean saveQuiz(){
		return false;
	}
}
