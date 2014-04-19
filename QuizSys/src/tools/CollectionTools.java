package tools;

import java.util.ArrayList;
import java.util.Collections;
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
		String result;
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

    /**
     * Compares two unordered arrays for matches.
     *
     * @param actualKeys set of keys to match
     * @param expectedKeys other set of keys to match against
     * @return true if all args match, false if not.
     */
	public static boolean compareTwoArrays(String[] actualKeys, String[] expectedKeys) {
		//check if they are of different lenghts, this straight away means they are not similar.
        if(actualKeys.length!=expectedKeys.length)
            return false;
        //compare each element
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

    /**
     * Checks if a string[] contains a specified string
     * @param toCheck String to be checked.
     * @param stringArray Array to check if the requested String is inside of.
     * @return True if array contains toCheck. false otherwise.
     */
	public static boolean arrayContains(String toCheck, String[] stringArray) {
		int i = 0;
		for(String element : stringArray)
            if (element.equals(toCheck))
                i++;
		return (i>0);
	}

    /**
     * adds a single string into an existing, shorter String[] type
     * @param toAdd String to input into the array.
     * @param targetArray array to add the string to.
     * @return extended version of the targetArray with the new String added to the end.
     */
	public static String[] addElementToArray(String toAdd, String[] targetArray) {
		ArrayList<String> content = new ArrayList<>();
        Collections.addAll(content, targetArray);
		content.add(toAdd);
		targetArray = new String[content.size()];
		for(int i = 0; i<content.size();i++){
			targetArray[i] = content.get(i);
		}

		return targetArray;
	}

    /**
     * transforms a String[] into an Arraylist. (made redundant by method addALL(), but dependent)
     * @param targetArray the array to be turned into an ArrayList
     * @return resolved ArrayList from the inputed String[].
     */
	public static ArrayList<String> toArrayList(String[] targetArray) {
		ArrayList<String> result = new ArrayList<>();
        Collections.addAll(result, targetArray);
		return result;
	}

    /**
     *
     * Returns an array without the falgged string.
     *
     * @param toRemove the String to remove from the array[].
     * @param targetArray the String[] to remove a String from.
     * @return the result String[] without the flagged String
     */
	public static String[] removeElementFromArray(String toRemove,
			String[] targetArray) {
		//Error checking
        if(targetArray.length==0
                ||!arrayContains(toRemove,targetArray)){
            System.out.println("Warning: Array is empty or String to be removed is not in the array.");
            return targetArray;
        }
        //Operate through ArrayList.
		ArrayList<String> content = toArrayList(targetArray);
		content.remove(toRemove);
        targetArray = content.toArray(new String[content.size()]);

        return targetArray;
	}
}
