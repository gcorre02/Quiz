package tools;

import java.util.ArrayList;

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
			result = i +" -> "+ current.toString()+"\n";
			i++;
		}
		return result;
	}
	
	private static <T> String populateResult(ArrayList<T> collection){
		String result = "";
		int i =0;
		for (T current : collection){
			result = getKey(i) +" -> "+ current.toString()+"\n";
			i++;
		}
		return result;
	}

	private static String getKey(int i) {
		String[] keys = {
				"A"
				+ "B"
				+ "C"
				+ "D"
				+ "E"
				+ "F"
				+ "G"
				+ "H"
		};
		return keys[i];
	}
	
	
	
}
