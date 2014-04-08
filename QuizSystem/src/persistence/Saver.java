package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import lombok.Data;

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
		file = new File(source+File.separator+"UserNames.txt");
		PrintWriter w;
		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		for(String field : userNames){
			w.println(field);
		}
		w.close();
		return true;
	}
	
	public boolean saveUserQuizzes(){
		return false;
	}
	public boolean saveQuiz(){
		return false;
	}
}
