package tools;

import java.util.ArrayList;
import java.util.Map;

public abstract class CollectionPrinter {
	public static <T> String collectionPrinter(char orderType, ArrayList<T> collection){
		String result = "";
		switch(orderType){
		case'0':
			result = populateResult(0, collection);
			break;
		case'1':
			result = populateResult(1, collection);
			break;
		case'S':
			result = populateResult(collection);
			break;
		default:
			result = null;
			break;
		}
		return result;
	}
	
	private static <T> String populateResult(int i, ArrayList<T> collection){
		String result = "";
		for (T current : collection){
			result += i +" -> "+ current.toString()+"\n";
			i++;
		}
		return result;
	}
	
	private static <T> String populateResult(ArrayList<T> collection){
		String result = "";
		int i =0;
		for (T current : collection){
			result += getKey(i) +" -> "+ current.toString()+"\n";
			i++;
		}
		return result;
	}

	private static String getKey(int i) {
		String[] keys = {
				"A",
				 "B"
				, "C"
				, "D"
				, "E"
				, "F"
				, "G"
				, "H"
		};
		return keys[i];
	}
	
	public static String printMap(Map<String, String[]> userQuizzes) {
		String print = "";
		for(String key : userQuizzes.keySet()){
			print += key+"\n";
			for(String element : userQuizzes.get(key)){
				print += "| "+element + "\n";
			}
		}
		return print;
	}

	public static boolean compareTwoArrays(String[] actualKeys, String[] expectedKeys) {
		int matchCounter = 0;
		for(String actual : actualKeys){
			for(String expected : expectedKeys){
				if(actual.equals(expected)){
					matchCounter++;
				}
			}
		}
		if(matchCounter == actualKeys.length){
			return true;
		}
		return false;
	}
	
	
}
