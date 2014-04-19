package tools;

import java.util.ArrayList;
import java.util.Map;

/**
 * Supportive collection handling methods
 */
public abstract class CollectionTools {
    /**
     * Orders the objects in an array with an index for each.
     * \nUsed for giving the user options.
     *
     *
     * @param orderType Ids the type of index as a char. S for string, 0 to start with 0, 1 to start with 1
     * @param collection The arrayList with the collection of strings or objects with overriden toString().
     * @param <T> String or objects with overriden toString(), ready to be presented.
     * @return A String with the formatted and indexed list of items in the collection, ready to be print.\nReturns null if the input is not one of the possible choices.
     */
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

    /**
     * A string of the inputted map with identation of the children arrays.
     * @param userQuizzes the map to print
     * @return the String of the structured map.
     */
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
        return matchCounter == actualKeys.length;
    }

	public static boolean arrayContains(String toCheck, String[] stringArray) {
		int i = 0;
		for(String element : stringArray){
			if(element.equals(toCheck))
				i++;
		}
		if(i>0)
			return true;
		return false;
	}

	public static String[] addElementToArray(String toAdd, String[] targetArray) {
		ArrayList<String> content = new ArrayList<>();
		for(String element : targetArray){
			content.add(element);
		}
		content.add(toAdd);
		targetArray = new String[content.size()];
		for(int i = 0; i<content.size();i++){
			targetArray[i] = content.get(i);
		}
		//debug
		//System.out.println("<<<  Add element to Array debug : >>>\n" + collectionPrinter('0', toArrayList(targetArray)));
		//end debug
		return targetArray;
	}

	public static ArrayList<String> toArrayList(String[] targetArray) {
		ArrayList<String> result = new ArrayList<>();
		for(String current : targetArray){
			result.add(current);
		}
		return result;
	}

	public static String[] removeElementFromArray(String toRemove,
			String[] targetArray) {
		
		ArrayList<String> content = new ArrayList<>();
		for(String element : targetArray){
			content.add(element);
		}
		content.remove(toRemove);
		targetArray = new String[content.size()];
		for(int i = 0; i<content.size();i++){
			targetArray[i] = content.get(i);
		}
		//debug
		System.out.println("<<<  Add element to Array debug : >>>\n" + collectionPrinter('0', toArrayList(targetArray)));
		//end debug
		return targetArray;
	}
	
	
}
