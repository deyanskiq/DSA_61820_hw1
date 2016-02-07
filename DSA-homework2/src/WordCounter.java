package bg.uni_sofia.fmi.deyan_denchev;

import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public class WordCounter {
	private static Hashtable<String, Integer> words = new Hashtable<String, Integer>();
	private static Hashtable<String, Integer> typeOfwords = new Hashtable<String, Integer>();
	private static Map.Entry<String, Integer> maxEntryValue;
	private static Map.Entry<String, Integer> maxEntryType;

	/**
	 * reads a book and finds how many times is every word used
	 * 
	 */
	public static void readBook(String book) {
		cleaning();
		String[] splittedWords = book.split("[, ]+");
		allocateToHashTables(splittedWords);

	}

	// when new book is read, erase the data for the old one
	private static void cleaning() {
		words.clear();
		typeOfwords.clear();
		maxEntryValue = null;
		maxEntryType = null;
	}

	private static void allocateToHashTables(String[] arr) {
		for (String wordWithType : arr) {

			String word = wordWithType.substring(2);
			String type = wordWithType.substring(0, 1);

			if (words.containsKey(word) && typeOfwords.containsKey(type)) {
				int value = words.get(word);
				int valueType = typeOfwords.get(type);

				words.put(word, value + 1);
				typeOfwords.put(type, valueType + 1);

			} else if (words.containsKey(word)
					&& !typeOfwords.containsKey(type)) {
				int value = words.get(word);

				words.put(word, value + 1);
				typeOfwords.put(type, 1);

			} else if (!words.containsKey(word)
					&& typeOfwords.containsKey(type)) {

				int valueType = typeOfwords.get(type);

				typeOfwords.put(type, valueType + 1);
				words.put(word, 1);

			} else {
				words.put(word, 1);
				typeOfwords.put(type, 1);
			}
		}
	}

	/**
	 * 
	 * @return how many times the word is repeated in the book
	 */
	static int wordOccurrences(String word) {
		return words.get(word);
	}

	/**
	 * @returns how many unique words are used in the book
	 */
	static int uniqueWordsCount() {
		int count = 0;
		for (Entry<String, Integer> entry : words.entrySet()) {

			if (entry.getValue() == 1) {
				count++;
			}
		}
		return count;

	}

	/**
	 * @returns the count of appearances of the mostly used word/words
	 */
	static int mostlyUsedWordAppearances() {
		for (Entry<String, Integer> entry : words.entrySet()) {
			if (maxEntryValue == null
					|| entry.getValue().compareTo(maxEntryValue.getValue()) >= 0) {
				maxEntryValue = entry;
			}
		}
		return maxEntryValue.getValue();

	}

	/**
	 * @returns what type of words is used the most (repeated words do matter)
	 *          adjectives, nouns, verbs, unknown
	 */
	static String mostlyUsedWordType() {
		String result = "";
		int temp = 0;
		for (Entry<String, Integer> entry : typeOfwords.entrySet()) {
			if (maxEntryType == null
					|| (entry.getValue().compareTo(maxEntryType.getValue()) > 0)
					&& temp < entry.getValue()) {
				maxEntryType = entry;
			} else if (entry.getValue().compareTo(maxEntryType.getValue()) == 0) {
				temp = entry.getValue();

			}
		}
		if (temp == maxEntryType.getValue()) {
			return "unknown";
		}
		String typeKey = maxEntryType.getKey();
		switch (typeKey) {
		case "a":
			result = "adjective";
			break;
		case "v":
			result = "verb";
			break;
		case "n":
			result = "noun";
			break;
		default:
			result = "unknown";
			break;

		}
		return result;
	}

	public static void print(Hashtable<String, Integer> hashTable) {
		for (Entry<String, Integer> entry : hashTable.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	public static void main(String[] args) {
		readBook("a_strange a_man v_is n_strange n_man");
		// System.out.println(wordOccurrences("strange")); // 2
		// System.out.println(wordOccurrences("man")); // 2
		// System.out.println(wordOccurrences("is")); // 1
		System.out.println(uniqueWordsCount()); // 1
		System.out.println(mostlyUsedWordAppearances()); // 2
		print(words);
		print(typeOfwords);
		System.out.println(mostlyUsedWordType()); // unknown

		// print(typeOfwords);
		// print(words);
		System.out.println("--------");
		readBook("a_woman n_hi n_play n_strange");
		// print(words);
		System.out.println(mostlyUsedWordType());
		// System.out.println(mostlyUsedWordAppearances());
		print(words);
		print(typeOfwords);
	}
}
