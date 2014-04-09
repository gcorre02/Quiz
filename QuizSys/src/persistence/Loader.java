package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

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
	
	public ArrayList<String> getUsernames(){
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
		try {
			jReader.beginArray();
		} catch (IOException e) {
			System.out.print("UserNames File is empty");
			return null;
		}
		//JsonToken token;
		try {
			
			while(jReader.hasNext()){
				//token = jReader.peek();
				usernames.add(jReader.nextString());	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usernames ;
	}
}
