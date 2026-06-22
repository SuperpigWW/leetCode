
package comprehensive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Represents a word in a textFile, and stores the frequency of occurrence of the word and keeps track
 * of the words that immediately follow it.
 * @version 4/14/26
 * @author Haoquan Wang and Alexis Uribe
 */
public class Words {
	
	private HashMap<String, Integer> nextWord;
	private int count;

    /**
     * Initializes the Word class with an empty HashMap to store preceding words
     * and sets the occurrence count to 0.
     */
	public Words() {
		nextWord = new HashMap<>();
		count = 0;
	}

    /**
     * Increments the occurrence count of the specified next word.
     * If the word is not already present in the HashMap, it is added with an initial count of one.
     *
     * @param word The word that follows this Word.
     */
	public void addNextWord(String word) {
		nextWord.merge(word, 1, (oldVal, newVal) -> oldVal + newVal);
        count++;
    }

    /**
     * Returns the k most probable words that occur after the current word.
     * Words are compared based on how often they appear after the current word;
     * if two words have the same count, they are compared using lexicographic order.
     *
     * @param k the number of words to return.
     * @return A list of strings containing the k most probable words in descending order.
     */
	public List<String> getTopK(int k) {
        PriorityQueue<String> maxHeap = new PriorityQueue<>(
                (a, b) -> {
                    int ca = nextWord.get(a);
                    int cb = nextWord.get(b);
                    if (cb != ca)
                        return cb - ca;
                    return a.compareTo(b);
                }
        );

        for (String word : nextWord.keySet()) {
            maxHeap.offer(word);
        }

        List<String> result = new ArrayList<>();
        for(int i = 0; i < k && !maxHeap.isEmpty(); i++){
            result.add(maxHeap.poll());
        }
        return result;
    }

    /**
     * Returns the most probable next word, ordered by number of occurrences.
     * If multiple words have the same occurrences, they are ordered lexicographically.
     *
     * @return The most probable word.
     */
	public String deterministicNext() {
        if (nextWord.isEmpty()) 
        	return null;

        String best = null;
        int bestCount = -1;

        for (Map.Entry<String, Integer> entry : nextWord.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            if (count > bestCount
                    || (count == bestCount && word.compareTo(best) < 0)) {
                best = word;
                bestCount = count;
            }
        }
        return best;
    }

    /**
     * Selects the next word based on random probability.
     * A random value is generated based on the total amount of times the word is called,
     * all words are iterated over while accumulating their counts until the cumulative value exceeds the random value.
     *
     * @return A randomly selected next word
     */
	public String randomNext() {
        if (nextWord.isEmpty()) 
        	return null;

        List<String> words = new ArrayList<>(nextWord.keySet());
        Collections.sort(words);

        int r = (int) (Math.random() * count);
        int cumulative = 0;

        for (String word : words) {
            cumulative += nextWord.get(word);
            if (r < cumulative)
            	return word;
        }

        return words.get(words.size() - 1);
    }

    /**
     * Checks whether there are any next words stored in the hashmap.
     * @return True if there are no next words, False otherwise.
     */
	public boolean isEmpty() {
        return nextWord.isEmpty();
    }

}
