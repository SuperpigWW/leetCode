package comprehensive;

import java.io.*;
import java.util.*;

/**
 * Represents a Markov chain where each string maps to the words that follow it in a text file.
 * Each next word stores the number of times it appears after the current word.
 * Word selection is based on three categories. Most probable words. returns the k most used next words and if equal
 * then it is based upon their lexicographic comparison.
 * Deterministic are all the most probable words with the same ordering criteria as most probable words.
 * Random selects a next word using weighted probability, where higher probability words occur more often.
 * @version 4/14/26
 * @author Haoquan Wang and Alexis Uribe
 */
public class MarkovChain {

    private HashMap<String, Words> model;

    /**
     * Initializes the Markov chain by building a new HashMap and mapping each string
     * to the words that follow it.
     *
     * @param filePath the file path of the file to parse.
     * @throws IOException if the file cannot be found or read.
     */
    public MarkovChain(String filePath) throws IOException {
        model = new HashMap<>();
        buildChain(filePath);
    }

    /**
     * Builds the Markov chain where each word maps to the words that follow it.
     * @param filePath the file path of the file to build the chain from
     * @throws IOException if the file cannot be found or read
     */
    private void buildChain(String filePath) throws IOException {
        List<String> allWords = new ArrayList<>();

        Scanner file = new Scanner(new File(filePath));
        
        while (file.hasNext()) {
            String token = file.next();        
            if (!token.isEmpty()) {
                allWords.addAll(parseWords(token));
            }
        }
        
        file.close();

        for (int i = 0; i < allWords.size() - 1; i++) {
            String current = allWords.get(i);
            String next    = allWords.get(i + 1);
            Words words = model.get(current);
            if (words == null) {
                words = new Words();
                model.put(current, words);
            }
            words.addNextWord(next);
        }
    }

    /**
     * Extracts all words from a line. A word is defined as a sequence of letters or digits.
     * Words are split whenever a space or a non-alphanumeric character excluding '-' and '\' is encountered during
     * the iteration.
     * @param token String line to be parsed.
     * @return A list of extracted words.
     */
    private List<String> parseWords(String token) {
        List<String> result = new ArrayList<>();
        token = token.toLowerCase();

        StringBuilder sb = new StringBuilder();
        for (char c : token.toCharArray()) {
            if (Character.isLetterOrDigit(c) || c == '_' || c == '\'') {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    result.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        if (sb.length() > 0) {
            result.add(sb.toString());
        }
        return result;
    }

    /**
     * Returns the k most probable words that occur after the seed word.
     * @param seed The word being referenced.
     * @param k The number of words to return that follow the seed word.
     * @return The k most probable words that occur after the seed, if there are fewer than k words
     * available, returns all of them in order.
     */
    public List<String> getProbableNextWords(String seed, int k) {
        Words dist = model.get(seed);
        if (dist == null) 
        	return Collections.emptyList();
        return dist.getTopK(k);
    }

    /**
     * Repeatedly selects the most probable next word starting from the current word.
     * @param seed The current word.
     * @param k The number of words to return that follow the seed word.
     * @return the most probable words following the seed word, up to k words.
     */
    public List<String> generateDeterministic(String seed, int k) {
        List<String> result = new ArrayList<>();
        String current = seed;

        for (int i = 0; i < k; i++) {
            result.add(current);
            Words dist = model.get(current);

            if (dist == null || dist.isEmpty()) {
                current = seed;
            } else {
                current = dist.deterministicNext();
            }
        }
        return result;
    }

    /**
     * Generates k number amount of randomly selected next words starting from the seed word.
     * Each next word is chosen based on its number of occurrences. A random value is generated
     * within the total number of occurrences for the seed, and words are iterated over while
     * accumulating their probabilities until the cumulative probability exceeds the random value,
     * resulting in the selection of a word.
     *
     * @param seed The starting word.
     * @param k The number of random words to generate.
     * @return A list of k randomly selected words that follow the seed word.
     */
    public List<String> generateRandom(String seed, int k) {
        List<String> result = new ArrayList<>();
        String current = seed;

        for (int i = 0; i < k; i++) {
            result.add(current);
            Words dist = model.get(current);

            if (dist == null || dist.isEmpty()) {
                current = seed;
            } else {
                current = dist.randomNext();
            }
        }
        return result;
    }
}