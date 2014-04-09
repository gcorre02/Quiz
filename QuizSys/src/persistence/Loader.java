package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;

public class Loader {
	String source;
	File file;

	public Loader(String path){
		source = path;
		File folder = new File(path);
		if(!folder.exists()){
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
}
