package comprehensive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Words {
	
	private HashMap<String, Integer> nextWord;
	private int count;
	
	public Words() {
		nextWord = new HashMap<>();
		count = 0;
	}
	
	public void addNextWord(String word) {
		nextWord.merge(word, 1, (oldVal, newVal) -> oldVal + newVal);
        count++;
    }
	
	public List<String> getTopK(int k) {
        PriorityQueue<String> minHeap = new PriorityQueue<>(
            (a, b) -> {
                int aCount = nextWord.get(a);
                int bCount = nextWord.get(b);
                if (aCount != bCount) 
                	return aCount - bCount;
                return b.compareTo(a);
            }
        );

        for (String word : nextWord.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        PriorityQueue<String> maxHeap = new PriorityQueue<>(
            (a, b) -> {
                int ca = nextWord.get(a);
                int cb = nextWord.get(b);
                if (cb != ca) 
                	return cb - ca;
                return a.compareTo(b);
            }
        );
        maxHeap.addAll(minHeap);

        List<String> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
        }
        return result;
    }
	
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
	
	public boolean isEmpty() {
        return nextWord.isEmpty();
    }

}
