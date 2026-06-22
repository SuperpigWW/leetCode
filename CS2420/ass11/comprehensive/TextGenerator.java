package comprehensive;

import java.io.IOException;
import java.util.List;

public class TextGenerator {

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