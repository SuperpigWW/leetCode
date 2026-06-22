package comprehensive;

import java.io.*;
import java.util.*;

public class MarkovChain {

    private HashMap<String, Words> model;

    public MarkovChain(String filePath) throws IOException {
        model = new HashMap<>();
        buildChain(filePath);
    }

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

    public List<String> getProbableNextWords(String seed, int k) {
        Words dist = model.get(seed);
        if (dist == null) 
        	return Collections.emptyList();
        return dist.getTopK(k);
    }

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