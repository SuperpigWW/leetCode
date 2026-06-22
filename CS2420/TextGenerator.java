package comprehensive;

import java.io.IOException;
import java.util.List;

/**
 * Represents a text generator that produces output based on input characteristics.
 * The first argument is the path and name of the text file. The second argument is the seed word,
 * which is used as the starting point for text generation. The third argument is the number of words
 * to generate. The fourth argument specifies the generation mode Probable, returns the k most probable words that occur
 * after the seed word. Deterministic - repeatedly selects the most probable next word starting from the current word.
 * Random - generates words based on random probability, where more used/higher probability words o
 * are more likely to occur than fewer used ones.
 * @version 4/14/26
 * @author Haoquan Wang and Alexis Uribe.
 */
public class TextGenerator {

    /**
     * Main class that takes four arguments and selects the type of text generation as described above.
     * @param args Four arguments representing input characteristics for the text generator.
     * @throws IOException If the file cannot be found or read.
     */
    public static void main(String[] args) throws IOException {

        String filePath = args[0];
        String seed     = args[1];
        int    k        = Integer.parseInt(args[2]);
        String mode     = args[3];

        MarkovChain markovModel = new MarkovChain(filePath);

        List<String> result;

        switch (mode) {
            case "probable":
                result = markovModel.getProbableNextWords(seed, k);
                break;
            case "deterministic":
                result = markovModel.generateDeterministic(seed, k);
                break;
            case "random":
                result = markovModel.generateRandom(seed, k);
                break;
            default:
                throw new IllegalArgumentException("Unknown mode: " + mode);
        }

        System.out.println(String.join(" ", result));
    }
}